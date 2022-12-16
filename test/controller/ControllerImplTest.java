package controller;

import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import model.ImageLibrary;
import model.ImageLibraryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the methods in ControllerImpl.
 */
public class ControllerImplTest {
  ImageLibrary images = new ImageLibraryImpl();

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullArgs() {
    Controller controller = new ControllerImpl(null, images);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullArg3() {
    Readable reader = new StringReader("");
    Controller controller = new ControllerImpl(reader, null);
  }

  @Test
  public void testValidPathAndFilename() {
    Readable reader = new StringReader("load res/Image.ppm image\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(3, this.images.getImage("image").getWidth());
    assertEquals(4, this.images.getImage("image").getHeight());
    assertEquals(255, this.images.getImage("image").getMaxValue());
  }

  @Test (expected = NullPointerException.class)
  public void testPathNotFound() {
    Readable reader = new StringReader("load res/NonexistentImage.ppm image\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(this.images.getImage("image").getWidth(), 0);
    assertEquals(this.images.getImage("image").getHeight(), 1);
    assertEquals(this.images.getImage("image").getMaxValue(), 255);
  }

  @Test (expected = NullPointerException.class)
  public void testP2PPM() {
    Readable reader = new StringReader("load res/InvalidP2.ppm p2Image");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();
    assertEquals(this.images.getImage("image").getWidth(), 0);
    assertEquals(this.images.getImage("image").getHeight(), 1);
    assertEquals(this.images.getImage("image").getMaxValue(), 255);
  }

  @Test
  public void testSaveValidPathAndFilename() {
    Readable reader = new StringReader("load res/Image.ppm image\n save res/Image-test-file.ppm" +
            "image\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();
    File file = new File("res/Image-test-file.ppm");
    assertTrue(file.exists());
  }

  @Test
  public void testStartBrighten() {
    Readable reader = new StringReader("load res/Image.ppm image\n brighten 50 image" +
            "image-brighter\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(50, this.images.getImage("image-brighter").copy()[0][0].getRed());
    assertEquals(150, this.images.getImage("image-brighter").copy()[0][0].getGreen());
    assertEquals(50, this.images.getImage("image-brighter").copy()[0][0].getBlue());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-brighter")));
    assertEquals(150, this.images.getImage("image-brighter").copy()[2][0].getRed());
    assertEquals(150, this.images.getImage("image-brighter").copy()[2][0].getGreen());
    assertEquals(255, this.images.getImage("image-brighter").copy()[2][0].getBlue());
  }

  @Test
  public void testStartHorizontalFlip() {
    Readable reader = new StringReader("load res/Image.ppm image\n horizontal-flip" +
            "image image-horizontal");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(0, this.images.getImage("image-horizontal").copy()[0][0].getRed());
    assertEquals(0, this.images.getImage("image-horizontal").copy()[0][0].getGreen());
    assertEquals(240, this.images.getImage("image-horizontal").copy()[0][0].getBlue());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-horizontal")));
    assertEquals(0, this.images.getImage("image-horizontal").copy()[2][0].getRed());
    assertEquals(5, this.images.getImage("image-horizontal").copy()[2][0].getGreen());
    assertEquals(35, this.images.getImage("image-horizontal").copy()[2][0].getBlue());
  }

  @Test
  public void testStartVerticalFlip() {
    Readable reader = new StringReader("load res/Image.ppm image\n vertical-flip image" +
            "image-vertical");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(40, this.images.getImage("image-vertical").copy()[0][0].getRed());
    assertEquals(5, this.images.getImage("image-vertical").copy()[0][0].getGreen());
    assertEquals(35, this.images.getImage("image-vertical").copy()[0][0].getBlue());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-vertical")));
    assertEquals(255, this.images.getImage("image-vertical").copy()[2][0].getRed());
    assertEquals(250, this.images.getImage("image-vertical").copy()[2][0].getGreen());
    assertEquals(255, this.images.getImage("image-vertical").copy()[2][0].getBlue());
  }

  @Test
  public void testStartRedGreyscale() {
    Readable reader = new StringReader("load res/Image.ppm image\n red-component image" +
            "image-red-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(0, this.images.getImage("image-red-greyscale").copy()[0][0].getRed());
    assertEquals(0, this.images.getImage("image-red-greyscale").copy()[0][0].getGreen());
    assertEquals(0, this.images.getImage("image-red-greyscale").copy()[0][0].getBlue());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-red-greyscale")));
    assertEquals(150, this.images.getImage("image-red-greyscale").copy()[2][1].getRed());
    assertEquals(150, this.images.getImage("image-red-greyscale").copy()[2][1].getGreen());
    assertEquals(150, this.images.getImage("image-red-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testStartGreenGreyscale() {
    Readable reader = new StringReader("load res/Image.ppm image" + "\n" + "green-component" +
            "image image-green-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(100, this.images.getImage("image-green-greyscale").copy()[0][0].getRed());
    assertEquals(100, this.images.getImage("image-green-greyscale").copy()[0][0].getGreen());
    assertEquals(100, this.images.getImage("image-green-greyscale").copy()[0][0].getBlue());
    assertFalse(images.getImage("image").equals(this.images.getImage("image-green-greyscale")));
    assertEquals(50, this.images.getImage("image-green-greyscale").copy()[2][1].getRed());
    assertEquals(50, this.images.getImage("image-green-greyscale").copy()[2][1].getGreen());
    assertEquals(50, this.images.getImage("image-green-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testStartBlueGreyscale() {
    Readable reader = new StringReader("load res/Image.ppm image" + "\n" + "blue-component" +
            "image image-blue-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(0, this.images.getImage("image-blue-greyscale").copy()[0][0].getRed());
    assertEquals(0, this.images.getImage("image-blue-greyscale").copy()[0][0].getGreen());
    assertEquals(0, this.images.getImage("image-blue-greyscale").copy()[0][0].getBlue());
    assertFalse(images.getImage("image").equals(this.images.getImage("image-blue-greyscale")));
    assertEquals(5, this.images.getImage("image-blue-greyscale").copy()[2][1].getRed());
    assertEquals(5, this.images.getImage("image-blue-greyscale").copy()[2][1].getGreen());
    assertEquals(5, this.images.getImage("image-blue-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testStartValue() {
    Readable reader = new StringReader("load res/Image.ppm image" + "\n" + "value-component" +
            "image image-value-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(100, this.images.getImage("image-value-greyscale").copy()[0][0].getRed());
    assertEquals(100, this.images.getImage("image-value-greyscale").copy()[0][0].getGreen());
    assertEquals(100, this.images.getImage("image-value-greyscale").copy()[0][0].getBlue());
    assertFalse(images.getImage("image").equals(this.images.getImage("image-value-greyscale")));
    assertEquals(150, this.images.getImage("image-value-greyscale").copy()[2][1].getRed());
    assertEquals(150, this.images.getImage("image-value-greyscale").copy()[2][1].getGreen());
    assertEquals(150, this.images.getImage("image-value-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testStartIntensity() {
    Readable reader = new StringReader("load res/Image.ppm image" + "\n" + "intensity-component" +
            "image image-intensity-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(33, this.images.getImage("image-intensity-greyscale").copy()[0][0].getRed());
    assertEquals(33, this.images.getImage("image-intensity-greyscale").copy()[0][0].getGreen());
    assertEquals(33, this.images.getImage("image-intensity-greyscale").copy()[0][0].getBlue());
    assertFalse(images.getImage("image").equals(images.getImage("image-intensity-greyscale")));
    assertEquals(68, this.images.getImage("image-intensity-greyscale").copy()[2][1].getRed());
    assertEquals(68, this.images.getImage("image-intensity-greyscale").copy()[2][1].getGreen());
    assertEquals(68, this.images.getImage("image-intensity-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testStartLuma() {
    Readable reader = new StringReader("load res/Image.ppm image" + "\n" + "greyscale image" +
            "image-luma-greyscale");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(71, this.images.getImage("image-luma-greyscale").copy()[0][0].getRed());
    assertEquals(71, this.images.getImage("image-luma-greyscale").copy()[0][0].getGreen());
    assertEquals(71, this.images.getImage("image-luma-greyscale").copy()[0][0].getBlue());
    assertFalse(images.getImage("image").equals(this.images.getImage("image-luma-greyscale")));
    assertEquals(68, this.images.getImage("image-luma-greyscale").copy()[2][1].getRed());
    assertEquals(68, this.images.getImage("image-luma-greyscale").copy()[2][1].getGreen());
    assertEquals(68, this.images.getImage("image-luma-greyscale").copy()[2][1].getBlue());
  }

  @Test
  public void testLoadImage() {
    Readable reader = new StringReader("load res/landscape.jpg landscape\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(this.images.getImage("landscape").getWidth(), 256);
    assertEquals(this.images.getImage("landscape").getHeight(), 256);
    assertEquals(this.images.getImage("landscape").getMaxValue(), 255);
    assertEquals(this.images.getImage("landscape").copy().length, 256);
  }

  @Test
  public void testSaveImage() {
    Readable reader = new StringReader("load res/landscape.jpg image\n save" +
            "res/landscape-test-file.jpg image\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();
    File file = new File("res/landscape-test-file.jpg");
    assertTrue(file.exists());
  }

  @Test
  public void testStartBlur() {
    Readable reader = new StringReader("load res/landscape.jpg image\n blur image" +
            "image-blurred\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(119, this.images.getImage("image-blurred").copy()[0][0].getRed());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-blurred")));
    assertEquals(190, this.images.getImage("image-blurred").copy()[2][1].getRed());
  }

  @Test
  public void testStartSharpen() {
    Readable reader = new StringReader("load res/landscape.jpg image\n sharpen image" +
            "image-sharpen\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();

    assertEquals(242, this.images.getImage("image-sharpen").copy()[0][0].getRed());
    assertFalse(this.images.getImage("image").equals(this.images.getImage("image-sharpen")));
    assertEquals(255, this.images.getImage("image-sharpen").copy()[2][1].getRed());
  }

  @Test
  public void saveDifferentExtensions() {
    Readable reader = new StringReader("load res/Image.ppm image\n save res/ImagePNG.png image\n");
    Controller controller = new ControllerImpl(reader, images);
    controller.start();
    File file = new File("res/ImagePNG.png");
    assertTrue(file.exists());
  }
}