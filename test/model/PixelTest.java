package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the methods in Pixel.
 */
public class PixelTest {
  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;

  @Before
  public void setUp() {
    this.pixel1 = new Pixel(0, 0, 0);
    this.pixel2 = new Pixel(255, 255, 255);
    this.pixel3 = new Pixel(83, 122, 48);
  }

  @Test
  public void testGetRed() {
    assertEquals(0, this.pixel1.getRed());
    assertEquals(255, this.pixel2.getRed());
    assertEquals(83, this.pixel3.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(0, this.pixel1.getGreen());
    assertEquals(255, this.pixel2.getGreen());
    assertEquals(122, this.pixel3.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, this.pixel1.getBlue());
    assertEquals(255, this.pixel2.getBlue());
    assertEquals(48, this.pixel3.getBlue());
  }

  @Test
  public void testSetRed() {
    assertEquals(0, this.pixel1.getRed());
    assertEquals(83, this.pixel3.getRed());
    this.pixel1.setRed(38);
    this.pixel3.setRed(0);
    assertEquals(38, this.pixel1.getRed());
    assertEquals(0, this.pixel3.getRed());
  }

  @Test
  public void testSetGreen() {
    assertEquals(0, this.pixel1.getGreen());
    assertEquals(122, this.pixel3.getGreen());
    this.pixel1.setGreen(38);
    this.pixel3.setGreen(0);
    assertEquals(38, this.pixel1.getGreen());
    assertEquals(0, this.pixel3.getGreen());
  }

  @Test
  public void testSetBlue() {
    assertEquals(0, this.pixel1.getBlue());
    assertEquals(48, this.pixel3.getBlue());
    this.pixel1.setBlue(38);
    this.pixel3.setBlue(0);
    assertEquals(38, this.pixel1.getBlue());
    assertEquals(0, this.pixel3.getBlue());
  }
}
