package controller.command;


import java.awt.Color;

import model.ImageModel;

/**
 * The abstractPPM commands which represents all the commands for a ppm file and also
 * saves the edited image, it reduces the duplicate code.
 */
public abstract class AbstractPPMCommands implements ImageCommands {
  protected final String newUserImageName;

  protected final String userImageName;

  protected final ImageModel model;

  protected final String commandType;

  protected Color[][] image;

  protected Color[][] processedImage;

  protected final int row;
  protected final int col;

  /**
   * Main constructor which takes the image in the ppm format to a buffered image to make it one
   * data type to apply the command to.
   *
   * @param userimageName    current image name
   * @param newUserImageName new image name after command applied
   * @param model            the model
   * @param commandType      the command applied to the given image.
   */
  protected AbstractPPMCommands(String userimageName, String newUserImageName, ImageModel model,
                                String commandType) {
    if (model == null) {
      throw new IllegalArgumentException("must pass through a model");
    }
    if (userimageName == null) {
      throw new IllegalArgumentException("must give the image name");
    }
    if (newUserImageName == null) {
      throw new IllegalArgumentException("must give the new user image name");
    }
    if (commandType == null) {
      throw new IllegalArgumentException("must give a valid command");
    }

    this.newUserImageName = newUserImageName;
    this.userImageName = userimageName;
    this.model = model;
    Color[][] image = this.model.retrieveImage(userimageName);
    this.row = image.length;
    this.col = image[0].length;
    this.processedImage = new Color[this.row][this.col];
    this.commandType = commandType;
    this.image = image;
  }

  /**
   * Abstract method which is called in the seperate command classes to processes the command.
   *
   * @throws IllegalArgumentException if the given command is not found in the class
   */
  public abstract void processCommand() throws IllegalArgumentException;

  protected void saveEditedImage() {
    this.model.addPPMToStorage(this.newUserImageName, this.processedImage);
  }

  /**
   * outputs the edited image.
   *
   * @return an array of colors given from the edited image which has been outputted.
   */
  public Color[][] imageOutput() {
    return this.processedImage;
  }

  /**
   * Gets the new image name.
   *
   * @return the new image name
   */
  public String updatedUserImageName() {
    return this.newUserImageName;
  }
}





