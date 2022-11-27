package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Represents the test for the view methods.
 */
public class ImageViewImplTest {

  ImageView view;
  ImageView view2;
  ImageModel model1;
  ImageModel model2;
  Appendable appendable;

  @Before
  public void init() {
    this.appendable = new StringBuilder();
    this.model1 = new ImageModelImpl();
    this.model2 = new ImageModelImpl("rec/smallImage.ppm", "smallImage");
    this.view = new ImageViewImpl(this.model1, this.appendable);
    this.view2 = new ImageViewImpl(this.model2, this.appendable);
  }

  @Test
  public void testInvalidImageView() {
    try {
      ImageView v1 = new ImageViewImpl(null, this.appendable);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("must pass through a model and output", e.getMessage());
    }
    try {
      ImageView v2 = new ImageViewImpl(this.model1, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("must pass through a model and output", e.getMessage());
    }
    try {
      ImageView v3 = new ImageViewImpl(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("must pass through a model and output", e.getMessage());
    }
  }


  @Test
  public void testToString() {
    assertEquals("", this.view.toString());
    assertEquals("r: 255,g: 0,b: 0r: 14,g: 0,b: 255r: 255,g: 0,b: 0r: 14,g: 0,b: 255\n" +
                    "r: 14,g: 0,b: 255r: 255,g: 0,b: 0r: 14,g: 0,b: 255r: 31,g: 255,b: 0\n" +
                    "r: 255,g: 0,b: 0r: 31,g: 255,b: 0r: 31,g: 255,b: 0r: 14,g: 0,b: 255\n" +
                    "r: 14,g: 0,b: 255r: 255,g: 0,b: 0r: 14,g: 0,b: 255r: 31,g: 255,b: 0\n",
            this.view2.toString());
  }

  @Test
  public void renderImage() {
    assertEquals("", this.appendable.toString());
    try {
      this.view.renderImage("empty");
      assertEquals("", this.appendable.toString());
    } catch (IOException e) {
      fail();
    }
    this.init();
    assertEquals("", this.appendable.toString());
  }

  @Test
  public void renderMessage() {
    StringBuilder log = new StringBuilder();
    ImageView ex1 = new ImageViewImpl(new ImageModelImpl(), log);
    try {
      ex1.renderMessage("fun");
      assertEquals("fun" + System.lineSeparator(), log.toString());
    } catch (IOException e) {
      fail();
    }
  }
}