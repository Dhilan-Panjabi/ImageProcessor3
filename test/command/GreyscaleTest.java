package command;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import controller.command.Greyscale;
import model.ImageIOModelImpl;
import model.ImageModelImpl;
import controller.command2.ImageGreyscaleIO;

import static org.junit.Assert.assertEquals;


/**
 * Represents the greyscale test.
 */
public class GreyscaleTest {

  ImageModelImpl ppmModel;

  ImageIOModelImpl pngModel1;

  ImageIOModelImpl jpgModel2;

  ImageIOModelImpl bmpModel4;


  //ppm
  Greyscale valueGrey1;
  Greyscale intensityGrey1;
  Greyscale lumaGrey1;
  Greyscale redGrey1;
  Greyscale greenGrey1;
  Greyscale blueGrey1;
  //png
  ImageGreyscaleIO valueGrey2;
  ImageGreyscaleIO intensityGrey2;
  ImageGreyscaleIO lumaGrey2;
  ImageGreyscaleIO redGrey2;
  ImageGreyscaleIO greenGrey2;
  ImageGreyscaleIO blueGrey2;
  //jpg
  ImageGreyscaleIO valueGrey3;
  ImageGreyscaleIO intensityGrey3;
  ImageGreyscaleIO lumaGrey3;
  ImageGreyscaleIO redGrey3;
  ImageGreyscaleIO greenGrey3;
  ImageGreyscaleIO blueGrey3;
  //bmp
  ImageGreyscaleIO valueGrey4;
  ImageGreyscaleIO intensityGrey4;
  ImageGreyscaleIO lumaGrey4;
  ImageGreyscaleIO redGrey4;
  ImageGreyscaleIO greenGrey4;
  ImageGreyscaleIO blueGrey4;


  @Before
  public void init() {

    this.ppmModel = new ImageModelImpl("rec/Koala.ppm", "KoalaPPM");
    this.pngModel1 = new ImageIOModelImpl("koala-vertical.png", "KoalaPNG");
    this.jpgModel2 = new ImageIOModelImpl("tiger.jpg", "tigerJPG");
    this.bmpModel4 = new ImageIOModelImpl("snail.bmp", "snailBMP");


    this.valueGrey1 = new Greyscale("KoalaPPM", "KoalaPPMgrey1",
            this.ppmModel, "value-component");
    this.intensityGrey1 = new Greyscale("KoalaPPM", "KoalaPPMi1",
            this.ppmModel, "intensity-component");
    this.lumaGrey1 = new Greyscale("KoalaPPM", "KoalaPPMl1",
            this.ppmModel, "luma-component");
    this.redGrey1 = new Greyscale("KoalaPPM", "KoalaPPMr1",
            this.ppmModel, "red-component");
    this.greenGrey1 = new Greyscale("KoalaPPM", "KoalaPPMg1",
            this.ppmModel, "green-component");
    this.blueGrey1 = new Greyscale("KoalaPPM", "KoalaPPMb1",
            this.ppmModel, "blue-component");
    this.valueGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGv2",
            this.pngModel1, "value-component");
    this.intensityGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGi2",
            this.pngModel1, "intensity-component");
    this.lumaGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGl2",
            this.pngModel1, "luma-component");
    this.redGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGr2",
            this.pngModel1, "red-component");
    this.greenGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGg2",
            this.pngModel1, "green-component");
    this.blueGrey2 = new ImageGreyscaleIO("KoalaPNG", "KoalaPNGb2",
            this.pngModel1, "blue-component");
    this.valueGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGv3",
            this.jpgModel2, "value-component");
    this.intensityGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGi3",
            this.jpgModel2, "intensity-component");
    this.lumaGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGl3",
            this.jpgModel2, "luma-component");
    this.redGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGr3",
            this.jpgModel2, "red-component");
    this.greenGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGg3",
            this.jpgModel2, "green-component");
    this.blueGrey3 = new ImageGreyscaleIO("tigerJPG", "tigerJPGb3",
            this.jpgModel2, "blue-component");
    this.valueGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPv4",
            this.bmpModel4, "value-component");
    this.intensityGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPg4",
            this.bmpModel4, "grey-component");
    this.lumaGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPl4",
            this.bmpModel4, "luma-component");
    this.redGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPr4",
            this.bmpModel4, "red-component");
    this.greenGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPg4",
            this.bmpModel4, "green-component");
    this.blueGrey4 = new ImageGreyscaleIO("snailBMP", "snailBMPb4",
            this.bmpModel4, "blue-component");

    this.valueGrey1.processCommand();
    this.intensityGrey1.processCommand();
    this.lumaGrey1.processCommand();
    this.redGrey1.processCommand();
    this.greenGrey1.processCommand();
    this.blueGrey1.processCommand();
    this.valueGrey2.processCommand();
    this.intensityGrey2.processCommand();
    this.lumaGrey2.processCommand();
    this.redGrey2.processCommand();
    this.greenGrey2.processCommand();
    this.blueGrey2.processCommand();
    this.valueGrey3.processCommand();
    this.intensityGrey3.processCommand();
    this.lumaGrey3.processCommand();
    this.redGrey3.processCommand();
    this.greenGrey3.processCommand();
    this.blueGrey3.processCommand();
    this.valueGrey4.processCommand();
    this.intensityGrey4.processCommand();
    this.lumaGrey4.processCommand();
    this.redGrey4.processCommand();
    this.greenGrey4.processCommand();
    this.blueGrey4.processCommand();
  }

  @Test
  public void greyscaleValue() {
    Color[][] ppm1 = this.valueGrey1.imageOutput();
    assertEquals(new Color(3, 3, 3), ppm1[0][0]);
    assertEquals(new Color(6, 6, 6), ppm1[0][1]);
    assertEquals(new Color(9, 9, 9), ppm1[1][0]);
    assertEquals(new Color(12, 12, 12), ppm1[1][1]);

    BufferedImage bmp1 = this.valueGrey4.imageOutput();
    assertEquals(new Color(3, 3, 3), new Color(bmp1.getRGB(0, 0)));
    assertEquals(new Color(6, 6, 6), new Color(bmp1.getRGB(0, 1)));
    assertEquals(new Color(9, 9, 9), new Color(bmp1.getRGB(1, 0)));
    assertEquals(new Color(12, 12, 12), new Color(bmp1.getRGB(1, 1)));

  }

  @Test
  public void intensityValue() {

    Color[][] ppm1 = this.intensityGrey1.imageOutput();
    assertEquals(new Color(2, 2, 2), ppm1[0][0]);
    assertEquals(new Color(5, 5, 5), ppm1[0][1]);
    assertEquals(new Color(8, 8, 8), ppm1[1][0]);
    assertEquals(new Color(11, 11, 11), ppm1[1][1]);

    BufferedImage img3 = this.intensityGrey4.imageOutput();
    assertEquals(new Color(2, 2, 2), new Color(img3.getRGB(0, 0)));
    assertEquals(new Color(5, 5, 5), new Color(img3.getRGB(0, 1)));
    assertEquals(new Color(8, 8, 8), new Color(img3.getRGB(1, 0)));
    assertEquals(new Color(11, 11, 11), new Color(img3.getRGB(1, 1)));
  }

  @Test
  public void lumaValue() {
    Color[][] ppm1 = this.lumaGrey1.imageOutput();
    assertEquals(new Color(1, 1, 1), ppm1[0][0]);
    assertEquals(new Color(4, 4, 4), ppm1[0][1]);
    assertEquals(new Color(7, 7, 7), ppm1[1][0]);
    assertEquals(new Color(10, 10, 10), ppm1[1][1]);

    BufferedImage jpg1 = this.lumaGrey3.imageOutput();
    assertEquals(new Color(3, 3, 3), new Color(jpg1.getRGB(0, 0)));
    assertEquals(new Color(6, 6, 6), new Color(jpg1.getRGB(0, 1)));
    assertEquals(new Color(8, 8, 8), new Color(jpg1.getRGB(0, 2)));
    assertEquals(new Color(9, 9, 9), new Color(jpg1.getRGB(1, 0)));
    assertEquals(new Color(11, 11, 11), new Color(jpg1.getRGB(1, 1)));
    assertEquals(new Color(14, 14, 14), new Color(jpg1.getRGB(1, 2)));
  }

  @Test
  public void redValue() {

    Color[][] ppm1 = this.redGrey1.imageOutput();
    assertEquals(new Color(1, 1, 1), ppm1[0][0]);
    assertEquals(new Color(4, 4, 4), ppm1[0][1]);
    assertEquals(new Color(7, 7, 7), ppm1[1][0]);
    assertEquals(new Color(10, 10, 10), ppm1[1][1]);

    BufferedImage jpg1 = this.redGrey3.imageOutput();
    assertEquals(new Color(3, 3, 3), new Color(jpg1.getRGB(0, 0)));
    assertEquals(new Color(6, 6, 6), new Color(jpg1.getRGB(0, 1)));
    assertEquals(new Color(8, 8, 8), new Color(jpg1.getRGB(0, 2)));
    assertEquals(new Color(9, 9, 9), new Color(jpg1.getRGB(1, 0)));
    assertEquals(new Color(11, 11, 11), new Color(jpg1.getRGB(1, 1)));
    assertEquals(new Color(14, 14, 14), new Color(jpg1.getRGB(1, 2)));
  }

  @Test
  public void greenValue() {
    Color[][] ppm1 = this.greenGrey1.imageOutput();
    assertEquals(new Color(2, 2, 2), ppm1[0][0]);
    assertEquals(new Color(5, 5, 5), ppm1[0][1]);
    assertEquals(new Color(8, 8, 8), ppm1[1][0]);
    assertEquals(new Color(11, 11, 11), ppm1[1][1]);

    BufferedImage png1 = this.greenGrey2.imageOutput();
    assertEquals(new Color(2, 2, 2), new Color(png1.getRGB(0, 0)));
    assertEquals(new Color(5, 5, 5), new Color(png1.getRGB(0, 1)));
    assertEquals(new Color(8, 8, 8), new Color(png1.getRGB(0, 2)));
    assertEquals(new Color(11, 11, 11), new Color(png1.getRGB(0, 3)));
    assertEquals(new Color(14, 14, 14), new Color(png1.getRGB(1, 0)));
    assertEquals(new Color(17, 17, 17), new Color(png1.getRGB(1, 1)));
    assertEquals(new Color(20, 20, 20), new Color(png1.getRGB(1, 2)));
    assertEquals(new Color(23, 23, 23), new Color(png1.getRGB(1, 3)));

  }

  @Test
  public void blueValue() {

    Color[][] ppm1 = this.blueGrey1.imageOutput();
    assertEquals(new Color(3, 3, 3), ppm1[0][0]);
    assertEquals(new Color(6, 6, 6), ppm1[0][1]);
    assertEquals(new Color(9, 9, 9), ppm1[1][0]);
    assertEquals(new Color(12, 12, 12), ppm1[1][1]);

    BufferedImage png1 = this.blueGrey2.imageOutput();
    assertEquals(new Color(3, 3, 3), new Color(png1.getRGB(0, 0)));
    assertEquals(new Color(6, 6, 6), new Color(png1.getRGB(0, 1)));
    assertEquals(new Color(9, 9, 9), new Color(png1.getRGB(0, 2)));
    assertEquals(new Color(12, 12, 12), new Color(png1.getRGB(0, 3)));
    assertEquals(new Color(15, 15, 15), new Color(png1.getRGB(1, 0)));
    assertEquals(new Color(18, 18, 18), new Color(png1.getRGB(1, 1)));
    assertEquals(new Color(21, 21, 21), new Color(png1.getRGB(1, 2)));
    assertEquals(new Color(24, 24, 24), new Color(png1.getRGB(1, 3)));
  }


}