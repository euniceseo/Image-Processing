package controller.commands;

import model.ImageModel;

/**
 * Represents the function object for converting an image to greyscale using a component.
 */
public class Component implements ControllerCommands {
  String str;

  /**
   * Creates a new Component function object with the given component.
   *
   * @param str the name of the component that the image will be greyscaled by
   */
  public Component(String str) {
    this.str = str;
  }

  @Override
  public ImageModel execute(ImageModel m) {
    return m.component(str);
  }
}
