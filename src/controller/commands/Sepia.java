package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for filtering an image with a sepia tone.
 */
public class Sepia implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.sepia();
  }
}
