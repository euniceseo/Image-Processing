package view;

import org.junit.Test;

import controller.Features;
import controller.FeaturesController;
import model.ImageLibrary;
import model.ImageLibraryImpl;
import model.ImageModel;
import model.ImageModelImpl;
import model.MockModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the methods in the JFrameView class.
 */
public class JFrameViewTest {

  @Test
  public void testMakeVisible() {
    StringBuilder log = new StringBuilder();
    View view = new MockView(log);
    view.makeVisible();

    assertEquals("display is visible", log.toString());
  }

  @Test
  public void testShowErrorMessage() {
    StringBuilder log = new StringBuilder();
    View view = new MockView(log);
    view.showErrorMessage("wrong");

    assertEquals("error wrong", log.toString());
  }

  @Test
  public void testRefresh() {
    StringBuilder log = new StringBuilder();
    View view = new MockView(log);
    view.refresh();

    assertEquals("refresh", log.toString());
  }

  @Test
  public void testAddFeatures() {
    StringBuilder log = new StringBuilder();
    ImageLibrary library = new ImageLibraryImpl();
    View view = new MockView(log);
    Features controller = new FeaturesController(library, view);
    view.addFeatures(controller);

    assertEquals("display is visibleadd feature", log.toString());
  }

  @Test
  public void testDisplayImage() {
    StringBuilder log = new StringBuilder();
    ImageModel model = new ImageModelImpl();
    View view = new MockView(log);
    view.displayImage(model);

    assertEquals("display 0", log.toString());
  }

  @Test
  public void testOperation() {
    StringBuilder log = new StringBuilder();
    View view = new MockView(log);
    ImageLibrary library = new ImageLibraryImpl();
    ImageModel model = new MockModel(log);
    library.add("image", model);
    model.changeBrightness(40);
    Features controller = new FeaturesController(library, view);

    assertEquals("changed brightness by 40display is visible", log.toString());
  }
}
