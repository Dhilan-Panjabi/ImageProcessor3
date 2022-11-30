package controller;

import org.junit.Test;


import java.io.StringReader;

import model.ImageIOModelImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * The controller test.
 */
public class ImageControllerImplTest {

  ImageController c1;

  ImageController c2;
  StringReader in;

  StringReader in2;
  StringBuilder out;
  ImageView view;
  ImageModel model;
  ImageIOModelImpl model2;

  @Test
  public void controllerTest1() {
    try {
      this.c1 = new ImageControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("please provide values for input, view, model", e.getMessage());
    }
  }


  @Test
  public void messageTest() {
    this.model = new ImageModelImpl();
    this.out = new StringBuilder();
    this.view = new ImageViewImpl(this.model, out);
    this.in = new StringReader("load image rec/koala.ppm koala" +
            "red-component Koala.ppm rec/red-koala.ppm" +
            "quit");
    this.c1 = new ImageControllerImpl(this.model, this.view, this.in);
    this.c1.imageEditor();


    String instructions = "If you would like to upload an image " +
            "please provide type 'load IMAGE-PATH IMAGE-DESTINATION \n" +
            "Once the image is shown you can use commands such as 'brighten', 'darken', " +
            "'horizontal-flip', 'red-component', 'blue-component', 'value-component'," +
            " 'intensity-component', 'luma-component' \n" +
            "To save the edited image please enter 'save IMAGE-PATH IMAGE-DESTINATION" +
            "To quit the program please enter 'quit'";
    String[] outputArray2 = out.toString().split(System.lineSeparator());
    assertEquals("", outputArray2[0]);
  }

  @Test
  public void textTest() {
    this.in2 = new StringReader("load tiger.jpg tiger "
            + "blur tiger BlurTiger "
            + "sharpen tiger SharpenTiger "
            + "greyscale tiger GreyTiger "
            + "sepiaTone tiger SepiaTiger "
            + "save res/BlurTiger.bmp BlurTiger "
            + "save res/SharpenTiger.jpg SharpenTiger "
            + "greyscale Koala GreyKoala "
            + "sepiaTone Koala SepiaKoala "
            + "save BlurKoala.png  BlurKoala "
            + "exit");
    this.c2 = new ImageIOController(this.model2, this.view, this.in2);
    this.c2.imageEditor();

    String instructions = "If you would like to upload an image " +
            "please provide type 'load IMAGE-PATH IMAGE-DESTINATION \n" +
            "Once the image is shown you can use commands such as 'brighten', 'darken', " +
            "'horizontal-flip', 'red-component', 'blue-component', 'value-component'," +
            " 'intensity-component', 'luma-component' \n" +
            "To save the edited image please enter 'save IMAGE-PATH IMAGE-DESTINATION" +
            "To quit the program please enter 'quit'";
    String[] outputArray2 = out.toString().split(System.lineSeparator());
    assertEquals("", outputArray2[0]);

  }
}