package model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents the testing for the model.
 */
public class ImageModelImplTest {

  ImageModel model1;

  @Test
  public void ProcessSaveTest() {
    this.model1 = new ImageModelImpl();
    try {
      this.model1.processImage(null, "something");
    } catch (IllegalArgumentException e) {
      assertEquals("image path cannot be null", e.getMessage());
    }

    try {
      this.model1.processImage("something", null);
    } catch (IllegalArgumentException e) {
      assertEquals("the image name cannot be null", e.getMessage());
    }

    try {
      this.model1.processImage(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("image path and image name cannot be null", e.getMessage());
    }

  }

  @Test
  public void retrievePixeltest() {
    this.model1 = new ImageModelImpl("rec/Koala.ppm", "koala");
    assertEquals(101, this.model1.retrievePixel(0, 0).getRed());
    assertEquals(54, this.model1.retrievePixel(1, 4).getBlue());
    assertEquals(93, this.model1.retrievePixel(4, 4).getGreen());
  }

  @Test
  public void retrievePixeltest2() {
    this.model1 = new ImageModelImpl("rec/dog.ppm", "dogphoto");
    assertEquals(101, this.model1.retrievePixel(0, 0).getRed());
    assertEquals(54, this.model1.retrievePixel(1, 4).getBlue());
    assertEquals(93, this.model1.retrievePixel(4, 4).getGreen());
  }

  @Test
  @Before
  public void testLoad() {
    this.model1 = new ImageModelImpl("rec/Koala.ppm", "Koala"); // Square
    assertEquals(768, this.model1.getRow());
    assertEquals(1024, this.model1.getCol());
    this.model1.processImage("rec/Koala.ppm", "Koala");
  }

  @Test
  public void testSave() {
    this.model1.saveImage("rec/Koala.ppm", "Koala");
    assertEquals(101, this.model1.retrievePixel(0, 0).getRed());
    assertEquals(90, this.model1.retrievePixel(0, 0).getGreen());
    assertEquals(58, this.model1.retrievePixel(0, 0).getBlue());
  }
}