package model;

import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents the ImageLibrary for the image processor.
 * It implements the ImageLibrary interface's getImage and add methods.
 */
public class ImageLibraryImpl implements ImageLibrary {
  private final Map<String, ImageModel> images;

  /**
   * Constructs the ImageLibrary for an image processor.
   */
  public ImageLibraryImpl() {
    this.images = new TreeMap<>();
  }

  @Override
  public ImageModel getImage(String imageName) {
    return this.images.get(imageName);
  }

  @Override
  public void add(String name, ImageModel image) {
    this.images.put(name, image);
  }

}
