import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.ImageController;
import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;
import controller.ImageIOController;
import model.ImageGUIModel;
import model.ImageGUIModelImpl;
import model.ImageIOModel;
import model.ImageIOModelImpl;
import model.ImageModel;
import view.ImageGUIView;
import view.ImageGUIViewImpl;
import view.ImageView;
import view.ImageViewImpl;


/**
 * runs the main method for IO controller.
 */
public class MainIO {
  /**
   * runs the main method which calls the controller for the IO method
   * to get the inputs given which runs the main method.
   *
   * @param args the list of given arguments
   */
  public static void main(String[] args) {
    if (args.length > 1 && args[0].equalsIgnoreCase("-file")) {
      file(args[1]);
    } else if ((args.length > 0) && args[0].equalsIgnoreCase("-text")) {
      runTerminal();
    } else {
      gui();
    }
  }

    private static void file(String filepath){
      try {
        ImageModel model = new ImageIOModelImpl();
        ImageView view = new ImageViewImpl(model);
        ImageController controller = new ImageIOController((ImageIOModel) model,
                view, new FileReader(filepath));
        controller.imageEditor();
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      }
    }

    private static void runTerminal(){
    ImageIOModel model = new ImageIOModelImpl();
    ImageView view = new ImageViewImpl(model);
    ImageController controller = new ImageIOController(model, view);
    controller.imageEditor();
    }

    private static void gui(){
      ImageGUIModel model = new ImageGUIModelImpl();
      ImageGUIView view = new ImageGUIViewImpl();
      ImageGUIController controller = new ImageGUIControllerImpl(model);
      controller.imageToView(view);
    }
}
