package model;

/**
 * This interface represents the operations offered by the image processing model.
 */
public interface ImageModel {
  /**
   * Flips an image by the y-axis.
   *
   * @return the image, flipped horizontally
   */
  ImageModel flipHorizontal();

  /**
   * Flips an image by the x-axis.
   *
   * @return the image, flipped vertically
   */
  ImageModel flipVertical();

  /**
   * Changes the brightness of an image by the given amount.
   *
   * @param increment the amount to brighten/darken the image
   * @return the image, brightened/darkened by the given increment
   */
  ImageModel changeBrightness(int increment);

  /**
   * Converts an image to greyscale based on the given component.
   *
   * @param str the component to greyscale with
   * @return the image, greyscaled using the given component
   */
  ImageModel component(String str);

  /**
   * Returns the width of an image.
   *
   * @return how many pixels wide an image is
   */
  int getWidth();

  /**
   * Returns the height of an image.
   *
   * @return how many pixels tall an image is
   */
  int getHeight();

  /**
   * Returns the maximum possible value a component can have for this representation.
   *
   * @return the maximum possible value of a component
   */
  int getMaxValue();

  /**
   * Makes a copy of this image, represented by a 2D pixel array.
   *
   * @return a duplicate 2D pixel array
   */
  Pixel[][] copy();

  /**
   * Resets the max value of the model.
   *
   * @param newValue the maxValue of the given image
   */
  void setMaxValue(int newValue);

  /**
   * Blurs an image.
   *
   * @return the image, blurred
   */
  ImageModel blur();

  /**
   * Sharpens an image.
   *
   * @return the image, sharpened
   */
  ImageModel sharpen();

  /**
   * Filters the image with a sepia tone.
   *
   * @return the image, filtered with a sepia tone
   */
  ImageModel sepia();

  /**
   * Converts an image to greyscale using the luma representation.
   *
   * @return the image, filtered to greyscale using the luma representation
   */
  ImageModel greyscale();
}
