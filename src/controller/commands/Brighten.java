package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for brightening an image.
 */
public class Brighten implements ControllerCommands {

  int incr;

  /**
   * Creates a new Brighten function object with the given increment.
   *
   * @param incr the amount the photo should be brightened or darkened by
   */
  public Brighten(int incr) {
    this.incr = incr;
  }

  @Override
  public ImageModel execute(ImageModel m) {
    return m.changeBrightness(this.incr);
  }
}
