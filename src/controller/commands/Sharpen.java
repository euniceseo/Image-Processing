package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for sharpening an image.
 */
public class Sharpen implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.sharpen();
  }
}
