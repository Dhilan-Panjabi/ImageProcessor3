package controller;


import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.Greyscale;
import controller.command.ImageCommands;
import controller.command.ImageStateImpl;
import controller.command.Lighting;
import controller.command2.ImageControlIO;
import model.ImageModel;
import view.ImageView;

/**
 * The controller implementation which takes in the commands from the user and runs them within the
 * program or quit the program.
 */
public class ImageControllerImpl extends AbstractController {

  /**
   * The controllers default constructor which takes in the model, view and input reader.
   *
   * @param model the model
   * @param view  the given image view
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {
    super(model, view, new InputStreamReader(System.in));
  }


  /**
   * Constructor which takes in the input, model and view and adds the comands for the ppm files.
   *
   * @param model model
   * @param view  view
   * @param input given input
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable input) {
    super(model, view, input);
    this.inputCommands.put("load", (s) -> {
      return new ImageControlIO("load", this.checkString(s),
              this.checkString(s), this.model);
    });
    this.inputCommands.put("save", (s) -> {
      return new ImageControlIO("save", this.checkString(s),
              this.checkString(s), this.model);
    });
    this.inputCommands.put("brighten", (s) -> {
      return new Lighting(this.checkInt(s), this.checkString(s), this.checkString(s), this.model,
              "brighten");
    });
    this.inputCommands.put("darken", (s) -> {
      return new Lighting(this.checkInt(s), this.checkString(s), this.checkString(s), this.model,
              "darken");
    });
    this.inputCommands.put("horizontal-flip", (s) -> {
      return new ImageStateImpl(this.checkString(s), this.checkString(s), this.model,
              "horizontal-flip");
    });
    this.inputCommands.put("vertical-flip", (s) -> {
      return new ImageStateImpl(this.checkString(s), this.checkString(s), this.model,
              "vertical-flip");
    });
    this.inputCommands.put("red-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "red-component");
    });
    this.inputCommands.put("blue-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "blue-component");
    });
    this.inputCommands.put("green-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "green-component");
    });
    this.inputCommands.put("value-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "value-component");
    });
    this.inputCommands.put("intensity-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "intensity-component");
    });
    this.inputCommands.put("luma-component", (s) -> {
      return new Greyscale(this.checkString(s), this.checkString(s), this.model,
              "luma-component");
    });
  }
  @Override
  public void imageEditor(){
    String userInput;
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
      ImageCommands commands;
      userInput = this.checkString(input);
      if (userInput.equals("exit")) {
        System.out.println("thank you for using the program");
        return;
      }
      Function<Scanner, ImageCommands> imageCommand =
              this.inputCommands.getOrDefault(userInput, null);
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
    throw new IllegalStateException("no command found");
    }
  }





