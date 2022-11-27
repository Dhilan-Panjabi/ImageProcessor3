package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import util.Pixel;

import static org.junit.Assert.assertEquals;



/**
 * Represents the class for the pixel test.
 */
public class PixelTest {

  Pixel pixel1;

  Pixel pixel2;

  Pixel pixel3;

  Pixel pixel4;

  Pixel pixel5;

  Color color1;

  Color color2;

  Color color3;

  @Before
  public void init() {

    this.color1 = new Color(0, 0, 0);

    this.color2 = new Color(234, 45, 8);

    this.color3 = new Color(200, 78, 98);

  }


  @Test
  public void maxValue() {
    assertEquals(0, Pixel.maxValue(color1));
    assertEquals(234, Pixel.maxValue(color2));
    assertEquals(200, Pixel.maxValue(color3));
  }

  @Test
  public void intensity() {
    assertEquals(0, Pixel.intensity(color1));
    assertEquals(95, Pixel.intensity(color2));
    assertEquals(125, Pixel.intensity(color3));
  }

  @Test
  public void luma() {
    assertEquals(0, Pixel.luma(color1));
    assertEquals(82, Pixel.luma(color2));
    assertEquals(105, Pixel.luma(color3));
  }

  @Test
  public void testToString() {
    assertEquals("r: 0,g: 0,b: 0", this.pixel1.toString());
    assertEquals("r: 239,g: 31,b: 31", this.pixel2.toString());
    assertEquals("r: 255,g: 255,b: 255", this.pixel5.toString());
  }

}