import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewImpl;

/**
 * Runs the main method.
 */
public class Main {
  /**
   * runs the main method which calls the controller to get the inputs given which runs the main
   * method.
   * @param args the list of given arguments
   */
  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageView view = new ImageViewImpl(model);
    ImageController controller = new ImageControllerImpl(model, view);
    controller.imageEditor();
  }
}