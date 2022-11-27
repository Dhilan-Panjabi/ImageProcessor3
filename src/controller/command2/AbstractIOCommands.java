package controller.command2;

import java.awt.image.BufferedImage;

import controller.command.ImageCommands;
import model.ImageIOModel;

/**
 * The abstract class which is designed to reduce the duplicate code, it has all of the methods
 * needed, since we can now take in all the different types of formats it can run the commands on
 * the bufferedImage.
 */
public abstract class AbstractIOCommands implements ImageCommands {
  protected final String newUserImageName;

  protected final ImageIOModel model;

  protected final String commandType;

  protected BufferedImage image;

  protected BufferedImage processedImage;

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
  protected AbstractIOCommands(String userimageName, String newUserImageName, ImageIOModel model,
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
    this.model = model;
    this.commandType = commandType;
    BufferedImage image = this.model.retrieveIOImage(userimageName);
    this.image = image;
    this.row = image.getWidth();
    this.col = image.getHeight();
    this.processedImage = new BufferedImage(row, col, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Abstract method which is called in the seperate command classes to processes the command.
   *
   * @throws IllegalArgumentException if the given command is not found in the class
   */
  public abstract void processCommand() throws IllegalArgumentException;

  protected void saveEditedImage() {
    this.model.addInStorage(this.newUserImageName, this.processedImage);
  }

  /**
   * outputs the modified bufferedImage.
   *
   * @return output bufferedImage.
   */
  public BufferedImage imageOutput() {
    return this.processedImage;
  }

  /**
   * outputs the new user image name.
   * @return the new user image name.
   */
  public String updatedUserImageName() {
    return this.newUserImageName;
  }
}



