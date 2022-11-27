package controller;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;


import controller.command.ImageCommands;
import model.ImageModel;
import view.ImageView;

/**
 * The abstract class for the controller which holds the method to run the controller and program.
 */
public abstract class AbstractController implements ImageController {

  protected final ImageModel model;
  protected Scanner input;

  protected String imageName;

  protected ImageView view;

  protected String destination;
  protected final Map<String, Function<Scanner, ImageCommands>> inputCommands = new HashMap<>();


  /**
   * The controllers default constructor which takes in the model, view and input reader.
   *
   * @param model the model
   * @param view  the given image view
   */
  public AbstractController(ImageModel model, ImageView view) {
    this(model, view, new InputStreamReader(System.in));
  }

  public AbstractController(ImageModel model){
    this.model = model;
    Objects.requireNonNull(model);
  }

  /**
   * takes in the model, view and input which then applies the controller methods.
   *
   * @param model the given model
   * @param view  the given view
   * @param input the input commands from the user
   */
  public AbstractController(ImageModel model, ImageView view, Readable input) {
    if (model == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    if (view == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    if (input == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    this.model = model;
    this.view = view;
    this.input = new Scanner(input);
  }

  /**
   * checks the given integer for the lighting commands.
   *
   * @param s the input
   * @return the integer.
   */
  public int checkInt(Scanner s) {
    try {
      return s.nextInt();
    } catch (NoSuchElementException ie) {
      throw new IllegalStateException("no command found");
    }
  }

  /**
   * check the string for the command given to apply.
   *
   * @param s the input
   * @return the string given
   */
  public String checkString(Scanner s) {
    try {
      return s.next();
    } catch (NoSuchElementException ie) {
      throw new IllegalStateException("no command found");
    }
  }


  /**
   * takes the controller which takes in all of the user inputs and to overwrite the current file
   * and then save the file in storage.
   *
   * @throws IllegalArgumentException if the command does not exist.
   */
  @Override
  public void imageEditor() throws IllegalStateException {
    String instructions = "\t\tload image (imageName, destination)\n" +
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
            "the integer is given for the lighting commands which take the given integer and brighten or darken by the given value.";
    System.out.println(instructions);
    while (input.hasNext()) {
      String userInput;
      userInput = this.checkString(input);
      if (userInput.equals("exit")) {
        System.out.println("thank you for using the program");
        return;
      }else{
        ImageCommands commands;
        Function<Scanner, ImageCommands> imageCommand = this.inputCommands.getOrDefault(userInput,
                null);
        if (imageCommand != null) {
          try {
            commands = imageCommand.apply(input);
            commands.processCommand();
          } catch (IllegalArgumentException e) {
            System.out.println("fails");
          }
        } else {
          System.out.println("Unknown command " + userInput);
        }
      }
    }
    throw new IllegalStateException("no command found");
  }
}


