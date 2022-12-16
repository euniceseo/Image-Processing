package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Component;
import controller.commands.ControllerCommands;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ImageLibrary;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

/**
 * This class represents the controller for the image processor.
 * It implements the Controller interface's load, save, and start methods.
 */
public class ControllerImpl implements Controller {

  private final ImageLibrary images;
  private Readable readable;
  private final Map<String, Function<Scanner, ControllerCommands>> knownCommands;

  /**
   * Constructs the controller for an image processor.
   *
   * @param readable the readable object to read user input
   * @param images   the library of images
   * @throws IllegalArgumentException when image or readable is null
   */
  public ControllerImpl(Readable readable, ImageLibrary images) throws IllegalArgumentException {
    if (readable == null || images == null) {
      throw new IllegalArgumentException("Model or readable is null");
    }
    this.images = images;
    this.readable = readable;
    this.knownCommands = new HashMap<>();
    this.knownCommands.put("blur", s -> new Blur());
    this.knownCommands.put("brighten", s -> new Brighten(s.nextInt()));
    this.knownCommands.put("red-component", s -> new controller.commands.Component("red"));
    this.knownCommands.put("green-component", s -> new controller.commands.Component("green"));
    this.knownCommands.put("blue-component", s -> new controller.commands.Component("blue"));
    this.knownCommands.put("value-component", s -> new controller.commands.Component("value"));
    this.knownCommands.put("intensity-component", s -> new Component("intensity"));
    this.knownCommands.put("greyscale", s -> new Greyscale());
    this.knownCommands.put("horizontal-flip", s -> new HorizontalFlip());
    this.knownCommands.put("vertical-flip", s -> new VerticalFlip());
    this.knownCommands.put("sepia", s -> new Sepia());
    this.knownCommands.put("sharpen", s -> new Sharpen());
  }

  @Override
  public void loadImage(String path, String fileName) {
    BufferedImage image;

    try {
      File pathFile = new File(path);
      image = ImageIO.read(pathFile);
    } catch (IOException e) {
      System.out.println("File " + path + " not found!");
      return;
    }

    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] localImage = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(image.getRGB(j, i));
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        Pixel pixel = new Pixel(r, g, b);
        localImage[i][j] = pixel;
      }
    }

    this.images.add(fileName, new ImageModelImpl(localImage));
  }

  @Override
  public void load(String path, String fileName) {
    Scanner sc;

    try {
      FileInputStream inputtedFile = new FileInputStream(path);
      sc = new Scanner(inputtedFile);
    } catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
      return;
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      return;
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] localImage = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new Pixel(r, g, b);
        localImage[i][j] = pixel;
      }
    }

    // add image to map
    this.images.add(fileName, new ImageModelImpl(localImage));
  }

  @Override
  public void saveImage(String path, String fileName, String extension) {
    Pixel[][] image = Objects.requireNonNull(this.images.getImage(fileName).copy());
    BufferedImage bufImage = new BufferedImage(image[0].length,
            image.length, BufferedImage.TYPE_INT_RGB);

    try {
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[i].length; j++) {
          int r = image[i][j].getRed();
          int g = image[i][j].getGreen();
          int b = image[i][j].getBlue();

          Color color = new Color(r, g, b);
          int rgb = color.getRGB();
          bufImage.setRGB(j, i, rgb);
        }
      }

      ImageIO.write(bufImage, extension, new File(path));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void save(String path, String fileName) {
    BufferedWriter bw;

    try {
      ImageModel imageModel = this.images.getImage(fileName);
      Pixel[][] image = Objects.requireNonNull(imageModel.copy());

      String p3 = "P3\n";
      String widthAndHeight = imageModel.getWidth() + " " + imageModel.getHeight() + "\n";
      String maxValue = imageModel.getMaxValue() + "\n";

      String pixels = "";
      for (int i = 0; i < image.length; i++) {
        for (int j = 0; j < image[i].length; j++) {
          pixels += image[i][j].getRed() + "\n" + image[i][j].getGreen() +
                  "\n" + image[i][j].getBlue() + "\n";
        }
      }

      File file = new File(path);

      FileWriter fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      bw.write(p3);
      bw.write(widthAndHeight);
      bw.write(maxValue);
      bw.write(pixels);
      bw.close();

    } catch (IOException e) {
      System.out.println("Could not save file");
    }
  }

  @Override
  public void start() {
    Scanner s = new Scanner(this.readable);

    while (s.hasNext()) {
      ControllerCommands c;
      String in = s.next();

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;

      } else if (in.equalsIgnoreCase("-file")) {
        String scriptFile = s.next();
        try {
          String builder = new String(Files.readAllBytes(Paths.get(scriptFile)));
          System.out.println("Script file loaded");
          this.readable = new StringReader(builder);
          this.start();
        } catch (IOException e) {
          System.out.println("Script file not found");
        }

      } else if (in.equalsIgnoreCase("load")) {
        String path = s.next();
        String fileName = s.next();
        String extension = path.substring(path.indexOf(".") + 1);

        if (extension.equals("ppm")) {
          this.load(path, fileName);
        } else {
          this.loadImage(path, fileName);
        }
        System.out.println("File " + fileName + " loaded");

      } else if (in.equalsIgnoreCase("save")) {
        String savePath = s.next();
        String saveFilename = s.next();
        String saveExtension = savePath.substring(savePath.indexOf(".") + 1);

        if (saveExtension.equals("ppm")) {
          this.save(savePath, saveFilename);
        } else {
          this.saveImage(savePath, saveFilename, saveExtension);
        }
        System.out.println("Saved " + saveFilename + " at: " + savePath);

      } else {
        Function<Scanner, ControllerCommands> cmd =
                knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          System.out.println("Invalid input");
        } else {
          c = cmd.apply(s);
          String oldName = s.next();
          String newName = s.next();
          this.images.add(newName, c.execute(this.images.getImage(oldName)));
          System.out.println("Operation applied");
          cmd = null;
        }
      }
    }
  }
}