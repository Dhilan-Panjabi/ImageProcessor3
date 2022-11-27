package controller.command2;

import java.awt.Color;

import model.ImageIOModel;
import util.Pixel;

/**
 * This is the image filter IO which can blur or sharpen a bufferedImage.
 */
public class ImageFilterIO extends AbstractIOCommands {

  /**
   * the constructor which takes in the parameters to apply the blur/sharpen commands to.
   *
   * @param userImageName    the imagename
   * @param newUserImageName the new user image name
   * @param model            the model
   * @param commandType      and the type of command to apply
   */
  public ImageFilterIO(String userImageName, String newUserImageName, ImageIOModel model,
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
    switch (this.commandType) {
      case "Blur":
        this.blurFilter();
        super.saveEditedImage();
        return;
      case "Sharpen":
        this.sharpenFilter();
        super.saveEditedImage();
        return;
      default:
        throw new IllegalArgumentException("No such command " + this.commandType + " exists");
    }
  }

  private void applyFilterByMatrix(double[][] filterMatrix, int row, int col) {
    double pRed = 0.0;
    double pGreen = 0.0;
    double pBlue = 0.0;
    int matHalfLength = filterMatrix.length / 2;
    int matLength = filterMatrix.length;
    int matRow = 0;
    for (int i = -matHalfLength + row; i < matHalfLength + row; i++) {
      int matCol = 0;
      for (int j = -matHalfLength + col; j < matHalfLength + col; j++) {
        if (i >= 0 && j >= 0 && i < this.row && j < this.col) {
          Color pixel = new Color(super.image.getRGB(i, j));
          pRed += filterMatrix[matRow][matCol] * pixel.getRed();
          pGreen += filterMatrix[matRow][matCol] * pixel.getGreen();
          pBlue += filterMatrix[matRow][matCol] * pixel.getRed();

        }
        matCol++;
      }
      matRow++;
    }
    pRed = Pixel.checkValue((int) Math.round(pRed));
    pBlue = Pixel.checkValue((int) Math.round(pBlue));
    pGreen = Pixel.checkValue((int) Math.round(pGreen));
    super.processedImage.setRGB(row, col,
            new Color((int) pRed, (int) pGreen, (int) pBlue).getRGB());
  }

  private void sharpenFilter() {
    double[][] sharpenMat = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    for (int i = 0; i < this.row; i = i + 1) {
      for (int j = 0; j < this.col; j = j + 1) {
        this.applyFilterByMatrix(sharpenMat, i, j);
      }
    }
  }

  private void blurFilter() {
    double[][] blurMat = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    for (int i = 0; i < this.row; i = i + 1) {
      for (int j = 0; j < this.col; j = j + 1) {
        this.applyFilterByMatrix(blurMat, i, j);
      }
    }
  }


}
