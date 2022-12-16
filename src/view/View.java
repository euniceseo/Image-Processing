package view;

import controller.Features;
import model.ImageModel;

/**
 * This interface represents operations offered by a GUI for an image processor. This GUI is built
 * by Java Swing and shows the current image and its histogram.
 */
public interface View {
  /**
   * Makes the view visible.
   */
  void makeVisible();

  /**
   * Transmits an error message to the view if the command could not be processed correctly.
   *
   * @param error the error message
   */
  void showErrorMessage(String error);

  /**
   * Signals the view to draw itself.
   */
  void refresh();

  /**
   * Adds features and calls on them.
   *
   * @param features features to be added
   */
  void addFeatures(Features features);

  /**
   * Displays the given image.
   *
   * @param image the image to be displayed
   */
  void displayImage(ImageModel image);
}
