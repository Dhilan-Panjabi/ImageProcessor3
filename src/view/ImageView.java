package view;

import java.io.IOException;

/**
 * the interface for the imageview which includes the toString, renderMessage and renderImage which
 * would be used to show the view of the program.
 */

public interface ImageView {

  /**
   * Overrides toString and shows some part of image to track any changes to the image.
   *
   * @return the String that represents the model.
   */
  String toString();

  /**
   * Renders a message to the view.
   *
   * @param m a message.
   * @throws IOException is thrown when the message fails to render.
   */
  void renderMessage(String m) throws IOException;

  /**
   * Represents the image with a 2D array of pixels.
   *
   * @param userImageName the given 2d array of image
   * @throws IOException is thrown when the message fails to render.
   */
  void renderImage(String userImageName) throws IOException;

}
