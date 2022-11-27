package controller;

import java.io.InputStreamReader;

import controller.command2.ImageColorTransformationIO;
import controller.command2.ImageControlIO;
import controller.command2.ImageFilterIO;
import controller.command2.ImageGreyscaleIO;
import controller.command2.ImageLightingIO;
import controller.command2.ImageStateIO;
import model.ImageIOModel;
import view.ImageView;

/**
 * The controller implementation which takes in the bufferedImage commands
 * from the user and runs them within the program or quit the program.
 */
public class ImageIOController extends AbstractController {

  /**
   * The controllers default constructor which takes in the model, view and input reader.
   *
   * @param model the model
   * @param view  the given image view
   */
  public ImageIOController(ImageIOModel model, ImageView view) {
    super(model, view, new InputStreamReader(System.in));
  }

  /**
   * Constructor which takes in the input, model and view and adds the commands for the
   * bufferedFiles files.
   *
   * @param model model
   * @param view  view
   * @param input given input
   */
  public ImageIOController(ImageIOModel model, ImageView view, Readable input) {
    super(model, view, input);
    if (model == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    if (view == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    if (input == null) {
      throw new IllegalArgumentException("please provide values for input, view, model");
    }
    this.inputCommands.put("load", (s) -> new ImageControlIO("load",
            this.checkString(s),
            this.checkString(s), this.model));
    this.inputCommands.put("save", (s) -> new ImageControlIO("save",
            this.checkString(s),
            this.checkString(s), this.model));
    this.inputCommands.put("brighten", (s) -> new ImageLightingIO(
            this.checkInt(s), this.checkString(s), this.checkString(s), (ImageIOModel) this.model,
        "brighten"));
    this.inputCommands.put("darken", (s) -> new ImageLightingIO(this.checkInt(s),
            this.checkString(s), this.checkString(s), (ImageIOModel) this.model,
            "darken"));
    this.inputCommands.put("horizontal-flip", (s) -> new ImageStateIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model,
            "horizontal-flip"));
    this.inputCommands.put("vertical-flip", (s) -> new ImageStateIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "vertical-flip"));
    this.inputCommands.put("red-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "red-component"));
    this.inputCommands.put("blue-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "blue-component"));
    this.inputCommands.put("green-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s),
            (ImageIOModel) this.model,
            "green-component"));
    this.inputCommands.put("value-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "value-component"));
    this.inputCommands.put("intensity-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "intensity-component"));
    this.inputCommands.put("luma-component", (s) -> new ImageGreyscaleIO(this.checkString(s),
            this.checkString(s), (ImageIOModel) this.model, "luma-component"));
    this.inputCommands.put("blur", (s) -> new ImageFilterIO(this.checkString(s),
            this.checkString(s),
            (ImageIOModel) this.model, "blur"));
    this.inputCommands.put("sharpen", (s) -> new ImageFilterIO(this.checkString(s),
            this.checkString(s),
            (ImageIOModel) this.model, "sharpen"));
    this.inputCommands.put("greyscale", (s) -> new ImageColorTransformationIO(
            this.checkString(s), this.checkString(s), (ImageIOModel) this.model,
        "greyscale"));
    this.inputCommands.put("sepiatone", (s) -> new ImageColorTransformationIO(
            this.checkString(s), this.checkString(s), (ImageIOModel) this.model,
        "sepiatone"));
  }
}

