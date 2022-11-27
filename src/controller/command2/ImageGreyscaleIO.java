package controller.command2;

import java.awt.Color;

import model.ImageIOModel;
import util.Pixel;

/**
 * the greyscale class which takes in the bufferedImage which then applies the commands to.
 */
public class ImageGreyscaleIO extends AbstractIOCommands {


  /**
   * the constructor which takes in the parameters to apply the greyscale commands to.
   *
   * @param userImageName    the imagename
   * @param newUserImageName the new user image name
   * @param model            the model
   * @param commandType      and the type of command to apply
   */
  public ImageGreyscaleIO(String userImageName, String newUserImageName, ImageIOModel model,
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
        Color pixel = new Color(super.image.getRGB(i, j));
        Color updatedPixel = this.makeColor(this.commands(pixel));
        super.processedImage.setRGB(row, col, updatedPixel.getRGB());
      }
    }
    super.saveEditedImage();
  }

  protected int commands(Color color) {
    switch (this.commandType) {
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
