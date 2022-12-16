package model;

/**
 * This interface represents the operations offered by an ImageLibrary, which is a group of images.
 * Its methods allow the user to get an ImageModel object from the ImageLibrary and add an
 * ImageModel object to the ImageLibrary.
 */
public interface ImageLibrary {
  /**
   * Returns the given ImageModel from the map of images.
   *
   * @param imageName the name of the image to be returned
   * @return the image that maps to the given name
   */
  ImageModel getImage(String imageName);

  /**
   * Adds the given ImageModel to the map of images under the given name.
   *
   * @param name  the name of the image
   * @param image the ImageModel to be added to the map
   */
  void add(String name, ImageModel image);
}
