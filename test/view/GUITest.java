package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;
import model.ImageGUIModel;
import model.ImageGUIModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.ImageGUIView;

public class GUITest {

  ImageGUIController controller;
  ImageGUIModel model;
  StringBuilder modelLog;
  StringBuilder viewInLog;
  StringBuilder viewOutLog;

  @Before
  public void setUP() {

    this.model = new ImageGUIModelImpl();
    this.controller = new ImageGUIControllerImpl(model);
    this.modelLog = new StringBuilder();
    this.viewInLog = new StringBuilder();
    this.viewOutLog = new StringBuilder();

  }

  @Test
  public void testFeaturesAndModel() {

    this.controller.updateModel();
    assertEquals("koala", this.model.getImageWhichLoaded());
    assertEquals("koala", this.controller.getImageName());
    assertEquals("", this.viewOutLog.toString());
    String currentLog =  "renderImage called with the name koala" + System.lineSeparator();
    assertEquals(currentLog, this.viewInLog.toString());

    this.controller.doInputCommands("load rec/Koala.ppm koala");
    assertEquals("koala", this.model.getImageWhichLoaded());
    assertEquals("koala", this.controller.getImageName());
    assertEquals("", this.viewOutLog.toString());
    assertEquals(currentLog, this.viewInLog.toString());

    this.controller.returnInstruction();
    assertEquals("koala", this.model.getImageWhichLoaded());
    assertEquals("koala", this.controller.getImageName());
    currentLog = currentLog + "renderMessage method: this" + System.lineSeparator();
    assertEquals(currentLog, this.viewInLog.toString());

    String instructions = ("\tWith the given instructions the given user inputs can be the following:\n" +
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
            "the integer is given for the lighting commands which take the given integer and brighten or darken by the given value.")
            + System.lineSeparator();
    assertEquals(instructions, this.viewOutLog.toString());
    assertEquals(currentLog, this.viewInLog.toString());

    try {
      this.controller.processGivenCommands();
    } catch (NullPointerException e) {
      assertEquals("koala", this.model.getImageWhichLoaded());
      assertEquals("koala", this.controller.getImageName());
      assertEquals("", this.viewOutLog.toString());
      assertEquals(currentLog, this.viewInLog.toString());
    }

  }

}
