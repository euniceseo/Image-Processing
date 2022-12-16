package controller.commands;

import model.ImageModel;

/**
 * Represents a set of image operations to be executed on an ImageModel object.
 */
public interface ControllerCommands {
  /**
   * Executes a set of operations on the given ImageModel object.
   *
   * @param m the ImageModel object
   * @return a new ImageModel object with the operations performed
   */
  ImageModel execute(ImageModel m);
}
