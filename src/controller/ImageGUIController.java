package controller;

import view.ImageGUIView;

/**
 * The controller for the GUI, this is called from the view when needed the controller will then
 * provide the given methods to the view.
 */
public interface ImageGUIController {

  /**
   * The method which is called from the view when the user wants to run an input, it checks to see
   * if the given command the user wants to run is possible with the current list of commands.
   */
  void processGivenCommands();

  /**
   * Loads the image to the GUI and then renders it.
   * @param path the path where the image is taken from
   */
  void loadImageToGUI(String path);

  /**
   * Gets the image which is loaded name.
   * @return the image name
   */
  String getImageName();

  /**
   * Overrides the current image that is rendered to the GUI and if another image wants to be loaded
   * by the user it will override the current image.
   * @param image the new image to be loaded
   */
  void loadOtherImage(String image);

  /**
   * Adds the given inputs to the controller so that it can process the inputs.
   * @param input the inputs given
   */
  void doInputCommands(String input);

  /**
   * Attempts to process the inputs given by the user.
   */
  void process();

  /**
   * Renders the view of the controller.
   * @param view the view for the GUI
   */
  void imageToView(ImageGUIView view);

  /**
   * Passes the image which is uploaded to the model.
   */
  void updateModel();

  /**
   * Updates the view with the new image.
   */
  void update();

  /**
   * Exits the program.
   */
  void exitProgram();

  /**
   * Renders the instructions of the program in the view.
   */
  void returnInstruction();

}
