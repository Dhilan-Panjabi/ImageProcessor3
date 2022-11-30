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
    this.view = new ImageViewImpl(null, this.appendable);
    this.view2 = new ImageViewImpl(this.model2, this.appendable);
  }

  @Test
  public void testInvalidImageView() {
    try {
      this.view = new ImageViewImpl(null, this.appendable);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("must pass through a model and output", e.getMessage());
    }
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
    ImageView example = new ImageViewImpl(new ImageModelImpl(), log);
    try {
      example.renderMessage("kevin");
      assertEquals("kevin" + System.lineSeparator(), log.toString());
    } catch (IOException e) {
      fail();
    }
  }
}