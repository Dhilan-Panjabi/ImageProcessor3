package controller;

/**
 * The controller for the model which runs the commands given.
 */
public interface ImageController {

  /**
   * Runs the controller which lets the user input commands which the controller takes in and then
   * executes either of those given commands.
   * @throws IllegalArgumentException when it fails to run a given command
   */
  void imageEditor() throws IllegalArgumentException;
}
