package controller;

/**
 * This interface represents the operations offered by the image processing controller.
 * Its start method allows the user to load and save a PPM image and start the program.
 */
public interface Controller {

  /**
   * Loads a non-PPM image from the given path and names it the given fileName.
   *
   * @param path     the path of the image to load in
   * @param fileName the name of the image
   */
  void loadImage(String path, String fileName);


  /**
   * Loads a PPM image from the given path and names it the given fileName.
   *
   * @param path     the path of the PPM image to load in
   * @param fileName the name of the image
   */
  void load(String path, String fileName);


  /**
   * Saves a non-PPM image with the given fileName to the given path with the given extension.
   *
   * @param path      the path to save the image to
   * @param fileName  the name of the image to save
   * @param extension the extension the image will have
   */
  void saveImage(String path, String fileName, String extension);

  /**
   * Saves a PPM image with the given fileName to the given path.
   *
   * @param path     the path to save the PPM image to
   * @param fileName the name of the image to save
   */
  void save(String path, String fileName);

  /**
   * Starts the image processing program and supports operations offered by the image processor,
   * including loading and saving a PPM image and changing the appearance of an image.
   */
  void start();
}
