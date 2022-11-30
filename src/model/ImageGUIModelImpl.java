package model;

/**
 * The implementation of the ImageGUIModel interface which has the new methods which can be called.
 */
public class ImageGUIModelImpl extends ImageIOModelImpl implements ImageGUIModel {

  /**
   * Constructor for the ImageGUIModelImpl which takes in the model.
   */
  public ImageGUIModelImpl() {
    super();
  }

  /**
   * Constructor for the ImageGUIModelImpl which takes in the model and the path of the image.
   * @param path the path of the image
   * @param imgName the name of the image
   */
  public ImageGUIModelImpl(String path, String imgName) {
    super(path, imgName);
  }

  /**
   * From the user input of the path of the image, they can load the image to the GUI.
   * @param path the path of the image
   */
  @Override
  public void loadToGUI(String path){
    String[] pathNames = path.split("\\\\");
    String userImageName = pathNames[pathNames.length - 1].split("\\.")[0];
    this.processImage(path, userImageName);
  }

  /**
   * Gets the image which is loaded name.
   * @return the image name
   */
  @Override
  public String getImageWhichLoaded() {
    return this.userImageName;
  }

  /**
   * Overrides the current image that is rendered to the GUI and if another image wants to be
   * loaded.
   * @param image the image which is going to be loaded to the GUI.
   */
  @Override
  public void loadOtherImage(String image) {
    super.userImageName = image;
    super.bufferedImage = super.savedBuffImage.get(image);

  }
}

