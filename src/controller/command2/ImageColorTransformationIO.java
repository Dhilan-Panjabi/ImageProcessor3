package controller.command2;

import java.awt.Color;

import model.ImageIOModel;
import util.Pixel;

/**
 * Image command which takes in the color transformation and either makes it sepiatone or greyscale
 * on a bufferedImage.
 */
public class ImageColorTransformationIO extends AbstractIOCommands {
  private final double[][] transformMatrix;

  /**
   * The main constructor which depending on the command type uses a certain transform matrix which
   * then applies the matrix to the colors to change it.
   *
   * @param userImageName    given image name
   * @param newUserImageName new image name
   * @param model            the given model which is the new IOModel which takes in the
   *                         bufferedImage
   * @param commandType      the given command type.
   */
  public ImageColorTransformationIO(String userImageName, String newUserImageName,
                                    ImageIOModel model, String commandType) {
    super(userImageName, newUserImageName, model, commandType);
    switch (super.commandType) {
      case "greyscale":
        this.transformMatrix = new double[][]{
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722},
                {0.2126, 0.7152, 0.0722}};
        return;
      case "sepia":
        this.transformMatrix = new double[][]{
                {0.393, 0.769, 0.189},
                {0.349, 0.686, 0.168},
                {0.272, 0.534, 0.131}};
        return;
      default:
        throw new IllegalArgumentException("No such command " + commandType + " exists");
    }
  }

  private Color applyTransformation(Color color) {
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();
    int transformRed = (int) ((this.transformMatrix[0][0] * red) +
            (this.transformMatrix[0][1] * green) +
            (this.transformMatrix[0][2] * blue));
    int transformGreen = (int) ((this.transformMatrix[1][0] * red) +
            (this.transformMatrix[1][1] * green) +
            (this.transformMatrix[1][2] * blue));
    int transformBlue = (int) ((this.transformMatrix[2][0] * red) +
            (this.transformMatrix[2][1] * green) +
            (this.transformMatrix[2][2] * blue));
    return new Color(Pixel.checkValue(transformRed), Pixel.checkValue(transformGreen),
            Pixel.checkValue(transformBlue));
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
        Color transformPixel = this.applyTransformation(new Color(super.image.getRGB(i, j)));
        super.processedImage.setRGB(i, j, transformPixel.getRGB());
      }
    }
    super.saveEditedImage();
  }
}
