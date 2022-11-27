package model;

/**
 * The interface for the ImageGUIModel which has the new methods which can be called to the GUI
 * it will have more features.
 */
public interface ImageGUIModel extends ImageIOModel {

  /**
   * From the user input of the path of the image, they can load the image to the GUI.
   * @param path the path of the image
   */
  void loadToGUI(String path);

  /**
   * Gets the image which is loaded name.
   * @return the image name
   */
  String getImageWhichLoaded();

  /**
   * Overrides the current image that is rendered to the GUI and if another image wants to be
   * loaded.
   * @param image the image which is going to be loaded to the GUI.
   */
  void loadOtherImage(String image);


}

