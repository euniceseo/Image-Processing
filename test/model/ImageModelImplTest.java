package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the methods in ImageModelImpl.
 */
public class ImageModelImplTest {
  Pixel one;
  Pixel two;
  Pixel three;
  Pixel four;
  Pixel five;
  Pixel six;
  Pixel seven;
  Pixel eight;
  Pixel nine;
  Pixel ten;
  Pixel ele;
  Pixel twe;
  Pixel[] row1;
  Pixel[] row2;
  Pixel[] row3;
  Pixel[] row4;
  Pixel[][] image;
  ImageModel model;

  @Before
  public void setUp() {
    one = new Pixel(0, 100, 0);
    two = new Pixel(100, 0, 0);
    three = new Pixel(0, 0, 240);
    four = new Pixel(255, 250, 255);
    five = new Pixel(0, 10, 0);
    six = new Pixel(0, 0, 0);
    seven = new Pixel(100, 100, 255);
    eight = new Pixel(150, 50, 5);
    nine = new Pixel(0, 5, 35);
    ten = new Pixel(40, 5, 35);
    ele = new Pixel(0, 0, 35);
    twe = new Pixel(40, 40, 40);
    row1 = new Pixel[]{one, two, three};
    row2 = new Pixel[]{four, five, six};
    row3 = new Pixel[]{seven, eight, nine};
    row4 = new Pixel[]{ten, ele, twe};
    image = new Pixel[][]{row1, row2, row3, row4};

    model = new ImageModelImpl(image);
  }

  @Test
  public void testValidConstructor() {
    assertEquals(this.model.getWidth(), 3);
    assertEquals(this.model.getHeight(), 4);
  }

  @Test
  public void testSetMaxValue() {
    assertEquals(255, this.model.getMaxValue());
    this.model.setMaxValue(100);
    assertEquals(100, this.model.getMaxValue());
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, this.model.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(4, this.model.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.model.getMaxValue());
  }

  @Test
  public void testCopy() {
    Pixel[][] copy = this.model.copy();

    assertEquals(0, copy[0][0].getRed());
    assertEquals(40, copy[3][2].getGreen());
    assertEquals(5, copy[2][1].getBlue());
  }

  @Test
  public void testFlipHorizontal() {
    // before flipping
    assertEquals(0, this.row1[0].getRed());
    assertEquals(0, this.row1[1].getGreen());
    assertEquals(240, this.row1[2].getBlue());

    assertEquals(255, this.row2[0].getRed());
    assertEquals(10, this.row2[1].getGreen());
    assertEquals(0, this.row2[2].getBlue());

    assertEquals(100, this.row3[0].getRed());
    assertEquals(50, this.row3[1].getGreen());
    assertEquals(35, this.row3[2].getBlue());

    Pixel[][] image = model.flipHorizontal().copy();
    assertEquals(image[0][0].getRed(), 0);
    assertEquals(image[0][0].getGreen(), 0);
    assertEquals(image[0][0].getBlue(), 240);
    assertEquals(image[1][1].getRed(), 0);
    assertEquals(image[1][1].getGreen(), 10);
    assertEquals(image[1][1].getBlue(), 0);
    assertEquals(image[3][2].getRed(), 40);
    assertEquals(image[3][2].getGreen(), 5);
    assertEquals(image[3][2].getBlue(), 35);
  }


  @Test
  public void testFlipVertical() {
    // before flipping
    assertEquals(0, this.row1[0].getRed());
    assertEquals(0, this.row1[1].getGreen());
    assertEquals(240, this.row1[2].getBlue());

    assertEquals(255, this.row2[0].getRed());
    assertEquals(10, this.row2[1].getGreen());
    assertEquals(0, this.row2[2].getBlue());

    assertEquals(100, this.row3[0].getRed());
    assertEquals(50, this.row3[1].getGreen());
    assertEquals(35, this.row3[2].getBlue());

    Pixel[][] image = model.flipVertical().copy();

    assertEquals(40, image[0][0].getRed());
    assertEquals(5, image[0][0].getGreen());
    assertEquals(35, image[0][0].getBlue());
    assertEquals(100, image[1][0].getRed());
    assertEquals(100, image[1][0].getGreen());
    assertEquals(255, image[1][0].getBlue());
    assertEquals(0, image[3][2].getRed());
    assertEquals(0, image[3][2].getGreen());
    assertEquals(240, image[3][2].getBlue());
  }

  @Test
  public void testChangeBrightness() {
    Pixel[][] image = model.changeBrightness(10).copy();

    assertEquals(10, image[0][2].getRed());
    assertEquals(10, image[0][2].getGreen());
    assertEquals(250, image[0][2].getBlue());
    assertEquals(255, image[1][0].getRed());
    assertEquals(255, image[1][0].getGreen());
    assertEquals(255, image[1][0].getBlue());

    Pixel[][] image2 = this.model.changeBrightness(-50).copy();
    assertEquals(100, image2[2][1].getRed());
    assertEquals(0, image2[2][1].getGreen());
    assertEquals(0, image2[2][1].getBlue());
  }

  @Test
  public void testGreyscaleRed() {
    Pixel[][] image = this.model.component("red").copy();
    assertEquals(0, image[0][0].getRed());
    assertEquals(0, image[0][0].getGreen());
    assertEquals(0, image[0][0].getBlue());
    assertEquals(100, image[0][1].getRed());
    assertEquals(100, image[0][1].getGreen());
    assertEquals(100, image[0][1].getBlue());
    assertEquals(0, image[0][2].getRed());
    assertEquals(0, image[0][2].getGreen());
    assertEquals(0, image[0][2].getBlue());
  }

  @Test
  public void testGreyscaleGreen() {
    Pixel[][] image = this.model.component("green").copy();
    assertEquals(100, image[0][0].getRed());
    assertEquals(100, image[0][0].getGreen());
    assertEquals(100, image[0][0].getBlue());
    assertEquals(50, image[2][1].getRed());
    assertEquals(50, image[2][1].getGreen());
    assertEquals(50, image[2][1].getBlue());
  }

  @Test
  public void testGreyscaleBlue() {
    assertEquals(0, image[0][0].getBlue());
    Pixel[][] image = this.model.component("blue").copy();
    assertEquals(0, image[0][0].getRed());
    assertEquals(0, image[0][0].getGreen());
    assertEquals(0, image[0][0].getBlue());
  }

  @Test
  public void testGreyscaleValue() {
    Pixel[][] image = this.model.component("value").copy();
    assertEquals(100, image[0][0].getRed());
    assertEquals(100, image[0][0].getGreen());
    assertEquals(100, image[0][0].getBlue());
    assertEquals(150, image[2][1].getRed());
    assertEquals(150, image[2][1].getGreen());
    assertEquals(150, image[2][1].getBlue());
  }

  @Test
  public void testGreyscaleIntensity() {
    Pixel[][] image = this.model.component("intensity").copy();
    assertEquals(33, image[0][0].getRed());
    assertEquals(33, image[0][0].getGreen());
    assertEquals(33, image[0][0].getBlue());
    assertEquals(68, image[2][1].getRed());
    assertEquals(68, image[2][1].getGreen());
    assertEquals(68, image[2][1].getBlue());
  }

  @Test
  public void testBrightenThenFlip() {
    // before brighten
    assertEquals(0, this.row1[0].getRed());
    assertEquals(0, this.row1[1].getGreen());
    assertEquals(240, this.row1[2].getBlue());

    assertEquals(255, this.row2[0].getRed());
    assertEquals(10, this.row2[1].getGreen());
    assertEquals(0, this.row2[2].getBlue());

    assertEquals(100, this.row3[0].getRed());
    assertEquals(50, this.row3[1].getGreen());
    assertEquals(35, this.row3[2].getBlue());

    ImageLibrary images = new ImageLibraryImpl();
    images.add("brighter", this.model.changeBrightness(10));

    Pixel[][] brighter = images.getImage("brighter").copy();
    assertEquals(10, brighter[0][2].getRed());
    assertEquals(10, brighter[0][2].getGreen());
    assertEquals(250, brighter[0][2].getBlue());
    assertEquals(255, brighter[1][0].getRed());
    assertEquals(255, brighter[1][0].getGreen());
    assertEquals(255, brighter[1][0].getBlue());

    images.add("brighterVertical", images.getImage("brighter").flipVertical());
    Pixel[][] brighterVertical = images.getImage("brighterVertical").copy();
    assertEquals(50, brighterVertical[0][0].getRed());
    assertEquals(15, brighterVertical[0][0].getGreen());
    assertEquals(45, brighterVertical[0][0].getBlue());
    assertEquals(110, brighterVertical[1][0].getRed());
    assertEquals(110, brighterVertical[1][0].getGreen());
    assertEquals(255, brighterVertical[1][0].getBlue());
    assertEquals(10, brighterVertical[3][2].getRed());
    assertEquals(10, brighterVertical[3][2].getGreen());
    assertEquals(250, brighterVertical[3][2].getBlue());
  }

  @Test
  public void testBlur() {
    Pixel[][] image = model.blur().copy();
    assertEquals(44, image[0][0].getRed());
    assertEquals(56, image[0][0].getGreen());
    assertEquals(31, image[0][0].getBlue());
    assertEquals(63, image[2][1].getRed());
    assertEquals(31, image[2][1].getGreen());
    assertEquals(38, image[2][1].getBlue());
  }

  @Test
  public void testSharpen() {
    Pixel[][] image = model.sharpen().copy();
    assertEquals(57, image[0][0].getRed());
    assertEquals(145, image[0][0].getGreen());
    assertEquals(0, image[0][0].getBlue());
    assertEquals(255, image[2][1].getRed());
    assertEquals(186, image[2][1].getGreen());
    assertEquals(194, image[2][1].getBlue());
  }

  @Test
  public void testSepia() {
    Pixel[][] image = model.sepia().copy();
    assertEquals(76, image[0][0].getRed());
    assertEquals(68, image[0][0].getGreen());
    assertEquals(53, image[0][0].getBlue());
    assertEquals(98, image[2][1].getRed());
    assertEquals(87, image[2][1].getGreen());
    assertEquals(68, image[2][1].getBlue());
  }

  @Test
  public void testGreyscale() {
    Pixel[][] image = model.greyscale().copy();
    assertEquals(71, image[0][0].getRed());
    assertEquals(71, image[0][0].getGreen());
    assertEquals(71, image[0][0].getBlue());
    assertEquals(68, image[2][1].getRed());
    assertEquals(68, image[2][1].getGreen());
    assertEquals(68, image[2][1].getBlue());
  }
}