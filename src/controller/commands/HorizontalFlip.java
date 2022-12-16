package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for flipping an image horizontally.
 */
public class HorizontalFlip implements ControllerCommands {

  @Override
  public ImageModel execute(ImageModel m) {
    return m.flipHorizontal();
  }
}
