package util;

import java.awt.Color;

/**
 * A class which represents a single pixel which has the red, green, blue component of the pixel.
 */
public class Pixel {

  /**
   * the maximum value of the three components for each pixel.
   */
  public static int maxValue(Color color) {
    return Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
  }

  /**
   * the average of the three components for each pixel.
   *
   * @param color color image
   * @return the intensity
   */
  public static int intensity(Color color) {
    return ((color.getRed() + color.getGreen() + color.getBlue()) / 3);
  }

  /**
   * the luma component for each pixel.
   *
   * @param color color pixel
   * @return the new luma color
   */
  public static int luma(Color color) {
    return (int) ((0.2126 * (color.getRed())) +
            (0.7152 * (color.getGreen())) +
            (0.0722 * (color.getBlue())));
  }

  /**
   * converts the color to the string.
   *
   * @param color the given color to output
   * @return return the string
   */
  public static String toString(Color color) {
    return "r: " + color.getRed() + ",g: " + color.getGreen() + ",b: " + color.getBlue();
  }

  /**
   * check the value given for each component to ensure it is in range.
   *
   * @param val given value for the rgb component
   * @return min value
   */
  public static int checkValue(int val) {
    if (val < 0) {
      return 0;
    }
    return Math.min(val, 255);
  }
}

