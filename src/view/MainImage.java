package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.swing.*;

public class MainImage extends JPanel {

  private BufferedImage image;

  public MainImage() {
    super();
  }

  public void placeImageOnScreen(BufferedImage image) {
    this.image = image;
    this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
  }

  protected void paintComponent(Graphics graphic){
    super.paintComponent(graphic);
    graphic.drawImage(this.image, this.getX() / 2, this.getY() / 2, this);
  }


}
