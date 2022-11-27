package controller.command;

import java.awt.Color;

import model.ImageModel;
import util.Pixel;

// Create a greyscale image with the red-component of the image with the given name,
// and refer to it henceforth in the program by the given destination name. Similar commands
// for green, blue, value, luma, intensity components should be supported.

/**
 * Has the commands which takes a pixel in the image and converts it into either one of the
 * component requested by the user.
 */
public class Greyscale extends AbstractPPMCommands {

  /**
   * Main Constructor for the greyscale class which takes in the name, new name, ppm model and type
   * of command which it wants to apply.
   *
   * @param userImageName    the name of the file
   * @param newUserImageName new name it wants to save as
   * @param model            the model of the image
   * @param commandType      the type of command it wants to apply in greyscale
   */
  public Greyscale(String userImageName, String newUserImageName, ImageModel model,
                   String commandType) {
    super(userImageName, newUserImageName, model, commandType);
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
        super.processedImage[i][j] = this.makeColor(this.commands(super.image[i][j]));
      }
    }
    super.saveEditedImage();
  }

  protected int commands(Color color) {
    switch (super.commandType) {
      case "red-component":
        return color.getRed();
      case "green-component":
        return color.getGreen();
      case "blue-component":
        return color.getBlue();
      case "value-component":
        return Pixel.maxValue(color);
      case "intensity-component":
        return Pixel.intensity(color);
      case "luma-component":
        return Pixel.luma(color);
      default:
        throw new IllegalArgumentException("No such command" + commandType + "exists");
    }
  }

  protected Color makeColor(int val) {
    return new Color(val, val, val);
  }
}
