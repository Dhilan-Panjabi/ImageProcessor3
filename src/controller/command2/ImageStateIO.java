package controller.command2;

import model.ImageIOModel;

/**
 * the flip for the buffered images.
 */
public class ImageStateIO extends AbstractIOCommands {

  /**
   * constructor which takes in the flip vertical command or horizontal and applies it to the
   * buffered image.
   *
   * @param userImageName    the given image name
   * @param newUserImageName the new user image name
   * @param model            the given model
   * @param commandType      the given command type
   */
  public ImageStateIO(String userImageName, String newUserImageName,
                      ImageIOModel model, String commandType) {
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

  private void verticalFlip() {
    int width = this.col;
    int height = this.row;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        super.processedImage.setRGB(row, this.col - 1 - col, super.image.getRGB(row, col));
      }
    }
  }

  private void horizontalFlip() {
    int width = this.col;
    int height = this.row;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        super.processedImage.setRGB(this.row - 1 - row, col, super.image.getRGB(row, col));
      }
    }
  }
}
