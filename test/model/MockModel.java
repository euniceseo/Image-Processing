package model;

/**
 * Test class with a mock model.
 */
public class MockModel implements ImageModel {

  StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public ImageModel flipHorizontal() {
    log.append("flipped horizontally");
    return null;
  }

  @Override
  public ImageModel flipVertical() {
    log.append("flipped vertically");
    return null;
  }

  @Override
  public ImageModel changeBrightness(int increment) {
    log.append("changed brightness by " + increment);
    return null;
  }

  @Override
  public ImageModel component(String str) {
    log.append("greyscaled by " + str + "component");
    return null;
  }

  @Override
  public int getWidth() {
    log.append("get width");
    return 0;
  }

  @Override
  public int getHeight() {
    log.append("get height");
    return 0;
  }

  @Override
  public int getMaxValue() {
    log.append("get max value");
    return 0;
  }

  @Override
  public Pixel[][] copy() {
    log.append("copy");
    return new Pixel[0][];
  }

  @Override
  public void setMaxValue(int newValue) {
    log.append("set max value to " + newValue);
  }

  @Override
  public ImageModel blur() {
    log.append("blurred");
    return null;
  }

  @Override
  public ImageModel sharpen() {
    log.append("sharpened");
    return null;
  }

  @Override
  public ImageModel sepia() {
    log.append("sepia-ed");
    return null;
  }

  @Override
  public ImageModel greyscale() {
    log.append("greyscaled");
    return null;
  }
}
