package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Image Library Implementation.
 */
public class ImageLibraryImplTest {
  ImageLibrary images;
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

    this.model = new ImageModelImpl(image);
    this.images = new ImageLibraryImpl();
  }

  @Test
  public void testGetImage() {
    assertEquals(250, this.row2[0].getGreen());
    this.images.add("image", this.model.flipVertical());
    assertEquals(100, this.images.getImage("image").copy()[1][0].getGreen());
  }

  @Test
  public void testAdd() {
    this.images.add("image2", new ImageModelImpl(new Pixel[][]{this.row1, this.row2}));
    assertEquals(3, this.images.getImage("image2").getWidth());
    assertEquals(2, this.images.getImage("image2").getHeight());
  }
}
