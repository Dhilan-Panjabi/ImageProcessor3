package controller.command2;

import java.awt.Color;

import model.ImageIOModel;

/**
 * the image lighting class for bufferedImages which can brighten/darken the image.
 */
public class ImageLightingIO extends AbstractIOCommands {
  // brighten the image by the given increment to create a new image,
  // referred to henceforth by the given destination name.
  // The increment may be positive (brightening) or negative (darkening)

  private final int incrementValue;


  /**
   * takes in the image lighting commands checks the increment value to increase/decrease the image
   * brightness.
   *
   * @param incrementValue   the given increment value
   * @param userimageName    the given image name
   * @param newUserImageName the new user image name
   * @param model            the given model
   * @param commandType      the given command type
   */
  public ImageLightingIO(int incrementValue, String userimageName, String newUserImageName,
                         ImageIOModel model, String commandType) {
    super(userimageName, newUserImageName, model, commandType);
    if (incrementValue < 0 || incrementValue > 255) {
      throw new IllegalArgumentException("cannot have an increment value less " +
              "than 0 or more than 255");
    }
    switch (this.commandType) {
      case "brighten":
        this.incrementValue = incrementValue;
        return;
      case "darken":
        this.incrementValue = incrementValue * -1;
        return;
      default:
        throw new IllegalArgumentException("No such command " + super.commandType + "exists");
    }
  }

  private Color applyLighting(Color color) {
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();

    if ((red + this.incrementValue) >= 0 && (red + this.incrementValue) < 255) {
      red += this.incrementValue;
    }
    if ((green + this.incrementValue) >= 0 && (green + this.incrementValue) < 255) {
      green += this.incrementValue;
    }
    if ((blue + this.incrementValue) >= 0 && (blue + this.incrementValue) < 255) {
      blue += this.incrementValue;
    }
    return new Color(red, green, blue);
  }

  /**
   * Abstract method which is called in the seperate command classes to processes the command.
   *
   * @throws IllegalArgumentException if the given command is not found in the class
   */
  @Override
  public void processCommand() throws IllegalArgumentException {
    for (int i = 0; i < this.row; i = i + 1) {
      for (int j = 0; j < this.col; j = j + 1) {
        Color pixel = new Color(super.image.getRGB(i, j));
        super.processedImage.setRGB(i, j, this.applyLighting(pixel).getRGB());
      }
    }
    super.saveEditedImage();
  }
}
