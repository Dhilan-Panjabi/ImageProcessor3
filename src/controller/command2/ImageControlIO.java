package controller.command2;


import controller.command.ImageCommands;
import model.ImageModel;

/**
 * This is the image command to deal with the save and load images. This is not abstracted as this
 * loads the image and saves it which doesn't take it from the storage.
 */
public class ImageControlIO implements ImageCommands {

  private final String commandType;

  private final String imageName;

  private final String givenDestination;

  public final ImageModel model;

  /**
   * the constructor that creates a image control command.
   *
   * @param commandType      the given command type
   * @param imageName        the image name with path
   * @param givenDestination the given destination to load or save the image.
   * @param model            the given model
   */
  public ImageControlIO(String commandType, String imageName,
                        String givenDestination, ImageModel model) {
    if (commandType == null || imageName == null || givenDestination == null || model == null) {
      throw new IllegalArgumentException("must provide a command type, image name" +
              "given destination and model");
    }
    this.commandType = commandType;
    this.imageName = imageName;
    this.givenDestination = givenDestination;
    this.model = model;
  }

  /**
   * Abstract method which is called in the seperate command classes to processes the command.
   *
   * @throws IllegalArgumentException if the given command is not found in the class
   */
  @Override
  public void processCommand() throws IllegalArgumentException {
    switch (this.commandType) {
      case "load":
        this.model.processImage(imageName, givenDestination);
        return;
      case "save":
        this.model.saveImage(imageName, givenDestination);
        return;
      default:
        throw new IllegalArgumentException("No command type " + this.commandType + " exists");
    }
  }

  /**
   * Abstract method which is called in the seperate command classes to get the new image name.
   * @return the new image name
   */
  @Override
  public String updatedUserImageName() {
    return this.givenDestination;
  }
}
