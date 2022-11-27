package controller.command;

/**
 * The image commands interface.
 */
public interface ImageCommands {
  /**
   * the commands in which they get operations called on the pixels.
   *
   */
  void processCommand() throws IllegalArgumentException;

  /**
   * The method which saves the edited image to the model.
   *
   * @return updated user image name
   */
  String updatedUserImageName();
}
