package view;

import controller.Features;
import model.ImageModel;

/**
 * Test class for Mock View.
 */
public class MockView implements View {

  private StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void makeVisible() {
    log.append("display is visible");
  }

  @Override
  public void showErrorMessage(String error) {
    log.append("error " + error);
  }

  @Override
  public void refresh() {
    log.append("refresh");
  }

  @Override
  public void addFeatures(Features features) {
    log.append("add feature");
  }

  @Override
  public void displayImage(ImageModel image) {
    log.append("display " + image.getWidth());
  }
}
