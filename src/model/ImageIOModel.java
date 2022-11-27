package model;

import java.awt.image.BufferedImage;

/**
 * The ImageIOModel which takes in the image which is in a different format to ppm such as jpeg, jpg
 * bmp and then applies the different methods for those file types. It can take in a ppm image and
 * change them into bufferedImages.
 */
public interface ImageIOModel extends ImageModel {

  /**
   * retrieves the io image.
   *
   * @param userImageName the given name
   * @return return the image from the image name
   * @throws IllegalArgumentException if it does not exist
   */
  BufferedImage retrieveIOImage(String userImageName) throws IllegalArgumentException;

  /**
   * add the image to the storage.
   *
   * @param imageName the given image name
   * @param image     the buffered image
   */
  void addInStorage(String imageName, BufferedImage image);

}
