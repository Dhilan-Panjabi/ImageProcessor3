package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.Greyscale;
import controller.command.ImageCommands;
import controller.command.Lighting;
import controller.command2.ImageColorTransformationIO;
import controller.command2.ImageControlIO;
import controller.command2.ImageFilterIO;
import controller.command2.ImageGreyscaleIO;
import controller.command2.ImageLightingIO;
import controller.command2.ImageStateIO;
import model.ImageGUIModel;
import model.ImageIOModel;
import view.ImageGUIView;

/**
 * The controller implementation which has all of the methods for the GUI view and if called upon
 * can access this controller class and run the method for the view.
 */
public class ImageGUIControllerImpl extends AbstractController implements ImageGUIController {
  private final ImageGUIModel model;

  private ImageGUIView view;


  private Scanner input;

  private String userImageName;

  private final Map<String, Function<Scanner, ImageCommands>> inputCommands = new HashMap<>();

  /**
   * The controllers default constructor which takes in the model and intializes the commands
   * that it can apply to the images.
   *
   * @param model the model
   */
  public ImageGUIControllerImpl(ImageGUIModel model) {
    super(model);
    Objects.requireNonNull(model);
    this.model = model;
    this.inputCommands.put("load", (s) -> new ImageControlIO("load",
            this.checkString(s), this.checkString(s), this.model));
    this.inputCommands.put("save", (s) -> new ImageControlIO("save",
            this.checkString(s), this.checkString(s), this.model));
    this.inputCommands.put("brighten",
            (s) -> new ImageLightingIO(this.checkInt(s), this.checkString(s),
                    this.checkString(s), this.model, "brighten"));
    this.inputCommands.put("darken", (s) -> new ImageLightingIO(this.checkInt(s), this.checkString(s),
            this.checkString(s), this.model, "darken"));
    this.inputCommands.put("vertical-flip", (s) -> new ImageStateIO("vertical-flip",
            this.checkString(s), this.model, this.checkString(s)));
    this.inputCommands.put("horizontal-flip", (s) -> new ImageStateIO("horizontal-flip",
            this.checkString(s), this.model, this.checkString(s)));
    this.inputCommands.put("value-component",
            (s) -> new Greyscale("value-component", this.checkString(s),
                    this.model, this.checkString(s)));
    this.inputCommands.put("red-component", (s) -> new ImageGreyscaleIO("red-component", this.checkString(s),
             this.model, this.checkString(s)));
    this.inputCommands.put("green-component",
            (s) -> new Greyscale("green-component", this.checkString(s),
                    this.model, this.checkString(s)));
    this.inputCommands.put("blue-component", (s) -> new ImageGreyscaleIO("blue-component", this.checkString(s),
             this.model,  this.checkString(s)));
    this.inputCommands.put("intensity-component",
            (s) -> new ImageGreyscaleIO("intensity-component", this.checkString(s),
                    this.model, this.checkString(s)));
    this.inputCommands.put("luma-component", (s) -> new ImageGreyscaleIO("luma-component", this.checkString(s),
             this.model, this.checkString(s)));
    this.inputCommands.put("Blur", (s) -> new ImageFilterIO("Blur", this.checkString(s),
             this.model, this.checkString(s)));
    this.inputCommands.put("Sharpen", (s) -> new ImageFilterIO("Sharpen",
            this.checkString(s), this.model, this.checkString(s)));
    this.inputCommands.put("greyscale", (s) -> new ImageColorTransformationIO("greyscale", this.checkString(s),
            this.model, this.checkString(s)));
    this.inputCommands.put("sepia", (s) -> new ImageColorTransformationIO("sepia", this.checkString(s),
            this.model, this.checkString(s)));
  }



  /**
   * The method which is called from the view when the user wants to run an input, it checks to see
   * if the given command the user wants to run is possible with the current list of commands.
   */
  @Override
  public void processGivenCommands() {
    String input;
    input = this.checkString(this.input);
    ImageCommands command;
    Function<Scanner, ImageCommands> commandMap = this.inputCommands.getOrDefault(input,
            null);
    if (commandMap == null) {
      System.out.println("Invalid command" + input);
    }else{
      try{
        command = commandMap.apply(this.input);
        command.processCommand();
        this.view.newUserImageName(command.updatedUserImageName());
      }catch(Exception e){
        this.view.renderErrorMessage("Invalid command " + input);
      }
      this.view.update();
    }
  }

  /**
   * Loads the image to the GUI and then renders it.
   * @param path the path where the image is taken from
   */
  @Override
  public void loadImageToGUI(String path) {
    this.model.loadToGUI(path);
  }

  /**
   * Gets the image which is loaded name.
   * @return the image name
   */
  @Override
  public String getImageName() {
    return this.userImageName;
  }

  /**
   * Overrides the current image that is rendered to the GUI and if another image wants to be loaded
   * by the user it will override the current image.
   * @param image the new image to be loaded
   */
  @Override
  public void loadOtherImage(String image) {
    this.userImageName = image;
    this.update();
  }

  /**
   * Adds the given inputs to the controller so that it can process the inputs.
   * @param input the inputs given
   */
  @Override
  public void doInputCommands(String input) {
    this.input = new Scanner(input);
  }

  /**
   * Attempts to process the inputs given by the user.
   */
  @Override
  public void process() {
    if(!this.view.getUserInput().equals("")){
      this.input = new Scanner(this.view.getUserInput());
      this.view.clearUserInput();
    }
  }

  /**
   * Renders the view of the controller.
   * @param view the view for the GUI
   */
  @Override
  public void imageToView(ImageGUIView view) {
    this.view = view;
    this.view.requestFeatures(this);
    this.view.render();
  }

  /**
   * Passes the image which is uploaded to the model.
   */
  @Override
  public void updateModel() {
    this.userImageName = this.model.getImageWhichLoaded();
    this.update();
  }

  /**
   * Updates the view with the new image.
   */
  @Override
  public void update() {
    try{
      this.model.loadOtherImage(this.userImageName);
      this.view.renderImageToGUI(this.model.retrieveIOImage(userImageName), this.userImageName);
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  /**
   * Exits the program.
   */
  @Override
  public void exitProgram() {
   System.out.println("Program exited");
   this.view.exitProgram();
  }

  /**
   * Renders the instructions of the program in the view.
   */
  @Override
  public void returnInstruction() {
    this.renderMessage("\tWith the given instructions the given user inputs can be the following:\n" +
            "\n" +
            "\t\tload image (imageName, destination)\n" +
            "\n" +
            "\t\tsave image(imageName, destination)\n" +
            "\n" +
            "\t\texit\n" +
            "\n" +
            "\t\tred-component(imageName, destination)\n" +
            "\n" +
            "\t\tgreen-component(imageName, destination)\n" +
            "\n" +
            " \t\tblue-component(imageName, destination)\n" +
            "\n" +
            "\t\tvalue-component(imageName, destination)\n" +
            "\n" +
            "\t\tintensity-component(imageName, destination)\n" +
            "\n" +
            "\t\tluma-component(imageName, destination)\n" +
            "\n" +
            "\t\tbrighten(integer, imageName, destination)\n" +
            "\n" +
            "\t\tdarken(integer, imageName, destination)\n" +
            "\n" +
            "\t\tblur(imageName, destination)\n" +
            "\n" +
            "\t\tsharpen(imageName, destination)\n" +
            "\n" +
            "\t\tgreyscale(imageName, destination)\n" +
            "\n" +
            "\t\tsepiatone(imageName, destination)\n" +
            "\n" +
            "Simple Notes about each command:\n" +
            "\n" +
            "The imageName is the given path of the image which has the imageName and the file type\n" +
            "The destination is the name of the image which is loaded and the name you want to save it by - usually the command which is applied.\n" +
            "the integer is given for the lighting commands which take the given integer and brighten or darken by the given value.");
  }

  private void renderMessage(String message){
    try{
      this.view.renderMessage(message);
    } catch (IOException e) {
      this.view.renderErrorMessage(e.getMessage());
    }
  }
}
