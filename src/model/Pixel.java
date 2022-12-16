package model;

/**
 * This class represents a pixel, which has red, green, and blue components to make up a color.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Constructs a new pixel with the given values for each component.
   *
   * @param red   the value of the red component
   * @param green the value of the green component
   * @param blue  the value of the blue component
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Returns the value of the red component of this pixel.
   *
   * @return the value of the red component
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the value of the green component of this pixel.
   *
   * @return the value of the green component
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the value of the blue component of this pixel.
   *
   * @return the value of the blue component
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Changes the value of the red component of this pixel to the given value.
   *
   * @param value the new value the red component should have
   */
  public void setRed(int value) {
    this.red = value;
  }

  /**
   * Changes the value of the green component of this pixel to the given value.
   *
   * @param value the new value the green component should have
   */
  public void setGreen(int value) {
    this.green = value;
  }

  /**
   * Changes the value of the blue component of this pixel to the given value.
   *
   * @param value the new value the blue component should have
   */
  public void setBlue(int value) {
    this.blue = value;
  }
}
