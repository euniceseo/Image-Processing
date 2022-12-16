package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for converting an image to greyscale using the luma component.
 */
public class Greyscale implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.greyscale();
  }
}
