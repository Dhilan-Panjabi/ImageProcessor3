package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.Graphics;

/**
 * The class which renders the histogram of the RGB values of the image.
 */
public class ImageRGBHistogram extends JPanel {

  private int[][] colorValues;

  private int maxNum;

  private int minNum;

  private double maxNumScale;

  private int height;

  private int width = (256 * barWidth) + 6;

  private static final int barHeight = 1;

  private static final int barWidth = 2;

  /**
   * The constructor for the RGB histogram.
   */
  public ImageRGBHistogram() {
    super();
  }


  public void imageSetter(BufferedImage image) {
    Color pixel;
    for(int i = 0; i < image.getWidth(); i++){
      for(int j = 0; j < image.getHeight(); j++){
        pixel = new Color(image.getRGB(i,j));

        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        int intensity = (red + green + blue) / 3;

        this.colorValues[0][red] += 1;
        this.colorValues[1][green] += 1;
        this.colorValues[2][blue] += 1;
        this.colorValues[3][intensity] += 1;


          }
        }

        this.minNum = Integer.MAX_VALUE;
        this.maxNum = 0;
        for(int[] nums : this.colorValues){
          for(int value = 0; value<256; value++){
            this.minNum = Math.min(this.minNum, nums[value]);
            this.maxNum = Math.max(this.maxNum, nums[value]);

      }
    }

    this.maxNumScale = this.maxNum / 256;

    this.height = (this.colorValues.length * barHeight * 256) + (3 * 2);
    this.setSize(new Dimension(this.width, this.height * this.colorValues.length));
    this.setPreferredSize(new Dimension(this.width, this.height * this.colorValues.length));


  }

  private Color numColorConverstion(int number) {

    switch (number) {
      case 0:
        return new Color(250, 0, 0, 150);
      case 1:
        return new Color(0, 250, 0, 150);
      case 2:
        return new Color(0, 0, 250, 150);
      case 3:
        return new Color(250, 250, 250, 150);
      default:
        throw new IllegalArgumentException("No color for " + number);
    }
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    if (this.colorValues != null) {

      Graphics2D graphics2D = (Graphics2D) graphics.create();
      int afterBoxY = this.height + 15;
      int nextLineY = 11;
      graphics2D.drawString(" = Red Component", 3, afterBoxY + nextLineY);
      graphics2D.drawString(" = Green Component", 3, afterBoxY + (nextLineY * 2));
      graphics2D.drawString(" = Blue Component", 3, afterBoxY + (nextLineY * 3));
      graphics2D.drawString(" = Intensity", 3, afterBoxY + (nextLineY * 4));
      for (int color = 0; color < this.colorValues.length; color++) {
        Rectangle2D square = new Rectangle2D.Float(3,
                afterBoxY + 2 + (nextLineY * color), 10, 10);
        graphics2D.setColor(this.numColorConverstion(color));
        graphics2D.fill(square);
        graphics2D.draw(square);
      }

      int posnX;
      int posnY;
      int barHeightValue;
      int currentHeight = 3;
      graphics2D.setColor(Color.black);
      graphics2D.drawRect(3, 3, width, this.height);
      for (int component = 0; component < this.colorValues.length; component++) {
        posnX = 6;
        int[] compValues = this.colorValues[component];
        Color color = this.numColorConverstion(component);
        currentHeight += 256;
        graphics2D.setColor(color);
        for (int pValue = 0; pValue < 256; pValue++) {
          int newCount = (int) ((compValues[pValue] - minNum) / this.maxNumScale);
          barHeightValue = (3 * 2) + (newCount * barHeight);
          posnY = currentHeight + (3 * 2) - barHeightValue;
          Rectangle2D bar = new Rectangle2D.Double(posnX, posnY, barWidth, barHeightValue);
          graphics2D.fill(bar);
          graphics2D.draw(bar);
          posnX += barWidth;
        }
      }
    }
  }


}


