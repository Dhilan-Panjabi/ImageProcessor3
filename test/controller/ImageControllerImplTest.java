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
  ImageIOModelImpl model3;
  StringBuilder builder;
  StringReader reader;


  @Test
  public void InvalidController1() {
    this.model = new ImageModelImpl("rec/Koala.ppm", "Koala");
    this.model2 = new ImageIOModelImpl("tiger.jpg", "tiger");
    this.model3 = new ImageIOModelImpl();
    this.out = new StringBuilder();
    this.view = new ImageViewImpl(this.model3, out);
    this.in = new StringReader("");
    this.in2 = new StringReader("exit");
    this.c1 = new ImageIOController(this.model2, this.view, this.in);
    this.c2 = new ImageIOController(this.model3, this.view, this.in);


    try {
      this.c1 = new ImageControllerImpl(null, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("please provide values for input, view, model", e.getMessage());
    }
  }

  @Test
  public void InvalidController2() {
    this.model = new ImageModelImpl("rec/Koala.ppm", "Koala");
    this.out = new StringBuilder();
    this.view = new ImageViewImpl(this.model, out);
    this.in = new StringReader("");

    try {
      this.c1 = new ImageControllerImpl(this.model, null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("please provide values for input, view, model", e.getMessage());
    }
  }

  @Test
  public void InvalidController3() {
    this.model = new ImageModelImpl("rec/Koala.ppm", "Koala");
    this.out = new StringBuilder();
    this.view = new ImageViewImpl(this.model, out);
    this.in = new StringReader("");

    try {
      this.c1 = new ImageControllerImpl(null, this.view, null);
    } catch (IllegalArgumentException e) {
      assertEquals("please provide values for input, view, model", e.getMessage());
    }
  }

  @Test
  public void InvalidController4() {
    this.model = new ImageModelImpl("rec/Koala.ppm", "Koala");
    this.out = new StringBuilder();
    this.view = new ImageViewImpl(this.model, out);
    this.in = new StringReader("");

    try {
      this.c1 = new ImageControllerImpl(null, null, this.in);
    } catch (IllegalArgumentException e) {
      assertEquals("please provide values for input, view, model", e.getMessage());
    }
  }

  @Test
  public void testMessage() {
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
  public void testScript() {
    this.in2 = new StringReader("load tiger.jpg tiger "
            + "blur tiger BlurTiger "
            + "sharpen tiger SharpenTiger "
            + "greyscale tiger GreyTiger "
            + "sepiaTone tiger SepiaTiger "
            + "save res/BlurTiger.bmp BlurTiger "
            + "save res/SharpenTiger.jpg SharpenTiger "
            + "save res/GreyTiger.png GreyTiger "
            + "save res/SepiaTiger.ppm SepiaTiger "
            + "load res/koala-vertical.png Koala "
            + " blur Koala BlurKoala "
            + "sharpen Koala SharpenKoala "
            + "greyscale Koala GreyKoala "
            + "sepiaTone Koala SepiaKoala "
            + "save BlurKoala.png  BlurKoala "
            + "save SharpenKoala.jpeg  SharpenKoala "
            + "save GreyKoala.bmp  GreyKoala "
            + "save SepiaKoala.ppm SepiaKoala "
            + "exit");
    this.c2 = new ImageIOController(this.model3, this.view, this.in2);
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