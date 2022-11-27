package view;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import controller.ImageGUIController;

/**
 * The interface for a GUI View which has all the methods that the GUI View will have.
 */
public interface ImageGUIView extends ImageView {

  /**
   * updates the GUI view.
   */
  void update();

  /**
   * Renders the error message.
   *
   * @param message the given message which caused the error
   */
  void renderErrorMessage(String message);

  /**
   * user inputs which is given in the GUI by the user.
   */
  String getUserInput();

  /**
   * clears the users input from the textbox.
   */
  void clearUserInput();

  /**
   * Has all the features from the GUIController which has listeners for the buttons and then runs
   * the commands within the GUI.
   * @param features the image gui controller methods called in the GUI.
   */
  void requestFeatures(ImageGUIController features);

  /**
   * Allows the image to be seen in the GUI.
   */
  void render();

  /**
   * Renders the image to the GUI.
   *
   * @param image the image which is going to be rendered
   * @param name the name of the image
   */
  void renderImageToGUI(BufferedImage image, String name);

  /**
   * Exits the program when the button is clicked
   */
  void exitProgram();

  /**
   * Gets the image name which is going to be saved.
   *
   * @return the image name
   */
  void newUserImageName(String name);
}

