package controller.command;

import java.awt.Color;

import model.ImageModel;

/**
 * The class which represents the change in lighting of the image, either brighten or darken the
 * given image and input from the user which is called to the controller.
 */
public class Lighting extends AbstractPPMCommands {
  // brighten the image by the given increment to create a new image,
  // referred to henceforth by the given destination name.
  // The increment may be positive (brightening) or negative (darkening)

  private final int incrementValue;

  /**
   * Main constructor which takes the image in the ppm image and applies the brighten or darken with
   * the given integer.
   *
   * @param userImageName    current image name
   * @param newUserImageName new image name after command applied
   * @param model            the model
   * @param commandType      the command applied to the given image.
   */
  public Lighting(int incrementValue, String userImageName, String newUserImageName,
                  ImageModel model, String commandType) {
    super(userImageName, newUserImageName, model, commandType);
    if (incrementValue < 0 || incrementValue > 255) {
      throw new IllegalArgumentException("cannot have an increment value less " +
              "than 0 or more than 255");
    }
    switch (this.commandType) {
      case "brighten":
        this.incrementValue = incrementValue;
        return;
      case "darken":
        this.incrementValue = -incrementValue;
        return;
      default:
        throw new IllegalArgumentException("No such command " + super.commandType + "exists");
    }
  }

  //applies the command to the color by the increment value
  //if darken negative increment to reduce
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
        super.processedImage[row][col] = this.applyLighting(super.image[row][col]);
      }
    }
  }
}
