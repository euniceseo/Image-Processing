package controller;

import java.awt.Component;
import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ControllerCommands;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.ImageLibrary;
import model.ImageModel;
import view.View;

/**
 * This class represents the controller for the image processor that supports a Java Swing GUI. It
 * implements the Features interface's load, save, start, and command methods.
 */
public class FeaturesController implements Features {

  private final ImageLibrary images;
  private final View view;
  private final Controller controller;
  private final Map<String, Function<String, ControllerCommands>> knownCommands;

  /**
   * Constructs the controller for an image processor that supports a Java Swing GUI.
   *
   * @param images the library of images
   * @param view   the view object
   */
  public FeaturesController(ImageLibrary images, View view) {
    if (images == null || view == null) {
      throw new IllegalArgumentException("images or view is null");
    }

    this.images = images;
    this.view = view;
    this.controller = new ControllerImpl(new StringReader(""), images);
    this.knownCommands = new HashMap<>();
    this.knownCommands.put("Blur", s -> new Blur());
    this.knownCommands.put("Brighten", s -> new Brighten(Integer.parseInt(
            JOptionPane.showInputDialog("Brightness increment: "))));
    this.knownCommands.put("Component", s -> new controller.commands.Component(
            JOptionPane.showInputDialog("Component: ")));
    this.knownCommands.put("Greyscale", s -> new Greyscale());
    this.knownCommands.put("Flip horizontally", s -> new HorizontalFlip());
    this.knownCommands.put("Flip vertically", s -> new VerticalFlip());
    this.knownCommands.put("Sepia", s -> new Sepia());
    this.knownCommands.put("Sharpen", s -> new Sharpen());

    this.view.makeVisible();
  }

  @Override
  public void load() {
    final JFileChooser fchooser = new JFileChooser("res");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, PNG, BPM Images", "jpg", "ppm", "png", "bpm");
    fchooser.setFileFilter(filter);

    int retvalue = fchooser.showOpenDialog((Component) this.view);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String fileName = JOptionPane.showInputDialog("Name file to: ");
      String path = f.getAbsolutePath();
      String extension = path.substring(path.indexOf(".") + 1);
      if (extension.equals("ppm")) {
        controller.load(path, fileName);
      } else {
        controller.loadImage(path, fileName);
      }

      ImageModel loadedImage = this.images.getImage(fileName);
      this.view.displayImage(loadedImage);
    }
  }

  @Override
  public void save(String imageName) {
    JFileChooser fchooser = new JFileChooser();
    int retvalue = fchooser.showSaveDialog((Component) this.view);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String filePath = f.getAbsolutePath();
      String extension = filePath.substring(filePath.indexOf(".") + 1);
      controller.saveImage(filePath, imageName, extension);
    }
  }

  @Override
  public void start(View view) {
    view.addFeatures(this);
  }

  @Override
  public void command(String imageLocalName, String newName, String command) {
    ControllerCommands c;

    Function<String, ControllerCommands> cmd =
            knownCommands.getOrDefault(command, null);
    if (cmd == null) {
      this.view.showErrorMessage("Invalid command");
    } else {
      c = cmd.apply(command);
      try {
        this.images.add(newName, c.execute(this.images.getImage(imageLocalName)));
        this.view.refresh();
        this.view.displayImage(this.images.getImage(newName));
        cmd = null;
      } catch (NullPointerException e) {
        this.view.showErrorMessage("Image not found");
      }
    }
  }
}
