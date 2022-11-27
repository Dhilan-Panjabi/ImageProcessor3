package controller.command;

import model.ImageModel;

/**
 * The class which represents the orientation of the image, this holds the commands of horizontal-
 * flip, vertical-flip and implements them if called.
 */
public class ImageStateImpl extends AbstractPPMCommands {

  /**
   * The constructor for the image state which controls the flip commands, it takes in the image
   * checks the command type and applies it to the given image.
   *
   * @param userImageName    given image name
   * @param newUserImageName new image name.
   * @param model            the model
   * @param commandType      the given command type either horizontal or vertical
   */
  public ImageStateImpl(String userImageName, String newUserImageName,
                        ImageModel model, String commandType) {
    super(userImageName, newUserImageName, model, commandType);
  }

  /**
   * Abstract method which is called in the seperate command classes to processes the command.
   *
   * @throws IllegalArgumentException if the given command is not found in the class
   */
  @Override
  public void processCommand() throws IllegalArgumentException {
    switch (this.commandType) {
      case "flip-vertically":
        this.verticalFlip();
        super.saveEditedImage();
        return;
      case "flip-horizontally":
        this.horizontalFlip();
        this.saveEditedImage();
        return;
      default:
        throw new IllegalArgumentException("No such command" + commandType + "exists");
    }
  }


  //controls the vertical flip to be called
  private void verticalFlip() {
    int width = this.col;
    int height = this.row;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        super.processedImage[row][this.col - 1 - col] = super.image[row][col];

      }
    }
  }

  //controls the horizontal flip to be called
  private void horizontalFlip() {
    int width = this.col;
    int height = this.row;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        super.processedImage[this.row - 1 - row][col] = super.image[row][col];
      }
    }
  }
}