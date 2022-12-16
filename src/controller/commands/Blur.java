package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for blurring an image.
 */
public class Blur implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.blur();
  }
}
