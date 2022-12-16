package controller;

import view.View;

/**
 * This interface represents the operations offered by the image processing controller that
 * supports a GUI built by Java Swing.
 */
public interface Features {

  /**
   * Loads an image.
   */
  void load();

  /**
   * Saves the image with the given imageName to the user's computer.
   *
   * @param imageName the name of the image to be saved
   */
  void save(String imageName);

  /**
   * Start method for the Features controller.
   *
   * @param view the view object
   */
  void start(View view);

  /**
   * Stores all possible commands and acts appropriately depending on what the user clicks.
   *
   * @param imageLocalName the name of the image
   * @param newName        the new name to be given to the image
   * @param command        the command to be performed
   */
  void command(String imageLocalName, String newName, String command);
}
