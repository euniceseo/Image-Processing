import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import controller.Features;
import controller.FeaturesController;
import model.ImageLibrary;
import model.ImageLibraryImpl;
import view.JFrameView;
import view.View;

/**
 * This class allows the user to input arguments and run the image processor with a GUI supported
 * view class.
 */
public class CommandView {
  /**
   * This main method allows the image processor to run.
   *
   * @param args the inputs for the processor
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      if (args[0].equals("-file") || args[0].equals("-text")) {
        Readable rd = new InputStreamReader(System.in);
        ImageLibrary images = new ImageLibraryImpl();
        Controller controller = new ControllerImpl(rd, images);
        controller.start();
      }
    } else {
      ImageLibrary images = new ImageLibraryImpl();
      View view = new JFrameView(images);
      Features featuresController = new FeaturesController(images, view);
      featuresController.start(view);
    }
  }
}
