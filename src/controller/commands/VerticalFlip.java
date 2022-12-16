package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for flipping an image vertically.
 */
public class VerticalFlip implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.flipVertical();
  }
}
