package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import util.ImageUtil;
import util.Pixel;

/**
 * the image model implementation for the bufferedImage which handles the different file types.
 */
public class ImageIOModelImpl implements ImageIOModel {

  protected String userImageName;

  protected BufferedImage bufferedImage;

  protected Map<String, BufferedImage> savedBuffImage;

  /**
   * constructor which takes in no parameters and saves the image.
   */
  public ImageIOModelImpl() {
    this.savedBuffImage = new HashMap<>();
  }

  /**
   * main constructor which takes in the imagename and the model to load and
   * save the specific image.
   *
   * @param imagePath the given image path
   * @param modelImageName the model image
   * @throws IllegalArgumentException if it does not exist
   */
  public ImageIOModelImpl(String imagePath, String modelImageName) throws IllegalArgumentException {
    if (imagePath == null || modelImageName == null) {
      throw new IllegalArgumentException("Must provide values for path and name");
    }
    this.userImageName = modelImageName;
    this.savedBuffImage = new HashMap<>();
    this.processImage(imagePath, modelImageName);
  }

  @Override
  public BufferedImage retrieveIOImage(String userImageName) throws IllegalArgumentException {
    if (userImageName == this.userImageName) {
      return this.bufferedImage;
    } else {
      for (Map.Entry<String, BufferedImage> bufferedEntry : this.savedBuffImage.entrySet()) {
        if (bufferedEntry.getKey() == userImageName) {
          return savedBuffImage.get(userImageName);
        }
      }
      throw new IllegalArgumentException("buffered Image not found or invalid image name "
              + userImageName);
    }
  }

  @Override
  public void addInStorage(String imageName, BufferedImage image) {
    this.savedBuffImage.put(imageName, image);
  }


  @Override
  public Color retrievePixel(int row, int col) {
    return new Color(this.bufferedImage.getRGB(row, col));
  }


  private String retrieveFileType(String path) throws IllegalArgumentException {
    try {
      return path.split("\\.")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException(path + "does not exist");
    }
  }

  @Override
  public void processImage(String path, String userImageName) throws IllegalArgumentException {
    BufferedImage image;
    if (path == null || userImageName == null) {
      throw new IllegalArgumentException("path or image name cannot be null");
    }
    String fileType = this.retrieveFileType(path);
    this.userImageName = userImageName;
    if(fileType.equals("ppm")){
      image = ImageUtil.ppmFileToBuffered(path);
    }else{
      try {
        image = ImageIO.read(new FileInputStream(path));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found");
      } catch (IOException e) {
        throw new IllegalArgumentException("IO Exception");
      }
    }

    this.bufferedImage = image;
    this.savedBuffImage.put(userImageName, image);
  }

  @Override
  public Color[][] retrieveImage(String userImageName) throws IllegalArgumentException {
    return ImageUtil.bufferedToPPM(this.retrieveIOImage(userImageName));
  }

  @Override
  public void saveImage(String path, String userImageName) {
    if (path == null || userImageName == null) {
      throw new IllegalArgumentException("path or image name cannot be null");
    }
    String fileType = this.retrieveFileType(path);
    if(fileType.equals("ppm")){
      Color[][] image = ImageUtil.bufferedToPPM(this.retrieveIOImage(userImageName));
      ImageUtil.PPMSave(path, image);
    }else{
      BufferedImage image = this.savedBuffImage.get(userImageName);
      try {
        ImageIO.write(image, fileType, new File(path));
        this.userImageName = userImageName;
      } catch (IOException e) {
        throw new IllegalArgumentException(userImageName + " is not a supported image");
      }
    }
  }

  @Override
  public int getRow() {
    try {
      return this.bufferedImage.getWidth();
    } catch (NullPointerException e) {
      return 0;
    }
  }

  @Override
  public int getCol() {
    try {
      return this.bufferedImage.getHeight();
    } catch (NullPointerException e) {
      return 0;
    }
  }

  @Override
  public int retrieveMaxVal(String img) {
    BufferedImage image = this.retrieveIOImage(img);
    int max = 0;
    for (int i = 0; i < image.getWidth(); i = i + 1) {
      for (int j = 0; j < image.getHeight(); j = j + 1) {
        Color pixel = new Color(image.getRGB(i, j));
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        if (max == 255) {
          return max;
        } else if (max < r || max < g || max < b) {
          max = Pixel.maxValue(pixel);
        }
      }
    }
    return max;
  }


  @Override
  public void addPPMToStorage(String image, Color[][] pixels) {
    this.savedBuffImage.put(image, ImageUtil.ppmToBuffered(pixels));
  }

}
