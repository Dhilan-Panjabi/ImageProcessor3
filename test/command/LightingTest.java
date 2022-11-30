package command;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import controller.command.Lighting;
import model.ImageIOModelImpl;
import model.ImageModelImpl;
import controller.command2.ImageLightingIO;

import static org.junit.Assert.assertEquals;


/**
 * Represents the lighting test.
 */
public class LightingTest {

  ImageModelImpl ppmModel;

  ImageIOModelImpl pngModel1;

  ImageIOModelImpl jpgModel2;

  ImageIOModelImpl bmpModel3;

  //ppm
  Lighting brighten1;
  Lighting darken1;
  //png
  ImageLightingIO brighten2;
  ImageLightingIO darken2;
  //jpg
  ImageLightingIO brighten3;
  ImageLightingIO darken3;
  //bmp
  ImageLightingIO brighten4;
  ImageLightingIO darken4;

  @Before
  public void init() {
    this.ppmModel = new ImageModelImpl("rec/Koala.ppm", "KoalaPPM");
    this.pngModel1 = new ImageIOModelImpl("koala-vertical.png", "KoalaPNG");
    this.jpgModel2 = new ImageIOModelImpl("tiger.jpg", "tigerJPG");
    this.bmpModel3 = new ImageIOModelImpl("snail.bmp", "snailBMP");

    this.brighten1 = new Lighting(5, "KoalaPPM",
            "KoalaPPMinc5", this.ppmModel, "brighten");
    this.darken1 = new Lighting(5, "KoalaPPM",
            "KoalaPPMdar5", this.ppmModel, "darken");
    this.brighten2 = new ImageLightingIO(10, "KoalaPNG",
            "KoalaPNGinc10", this.pngModel1, "brighten");
    this.darken2 = new ImageLightingIO(10, "KoalaPNG",
            "KoalaPNGdar10", this.pngModel1, "darken");
    this.brighten3 = new ImageLightingIO(15, "tigerJPG",
            "tigerJPGinc15", this.jpgModel2, "brighten");
    this.darken3 = new ImageLightingIO(15, "tigerJPG",
            "tigerJPGdar15", this.jpgModel2, "darken");
    this.brighten4 = new ImageLightingIO(20, "snailBMP",
            "snailBMPinc20", this.bmpModel3, "brighten");
    this.darken4 = new ImageLightingIO(20, "snailBMP",
            "snailBMPdar20", this.bmpModel3, "darken");

    this.brighten1.processCommand();
    this.brighten2.processCommand();
    this.brighten3.processCommand();
    this.brighten4.processCommand();
    this.darken1.processCommand();
    this.darken2.processCommand();
    this.darken3.processCommand();
    this.darken4.processCommand();

  }

  @Test
  public void lighting() {

    BufferedImage bmp1 = this.brighten4.imageOutput();
    assertEquals(new Color(18, 19, 20), new Color(bmp1.getRGB(0, 0)));
    assertEquals(new Color(21, 22, 23), new Color(bmp1.getRGB(0, 1)));
    assertEquals(new Color(24, 25, 26), new Color(bmp1.getRGB(0, 2)));

    Color[][] ppm1 = this.brighten1.imageOutput();
    assertEquals(new Color(12, 13, 14), ppm1[0][0]);
    assertEquals(new Color(15, 16, 17), ppm1[0][1]);
    assertEquals(new Color(18, 19, 20), ppm1[1][0]);

    BufferedImage jpg1 = this.brighten3.imageOutput();
    assertEquals(new Color(18, 19, 21), new Color(jpg1.getRGB(0, 0)));
    assertEquals(new Color(21, 22, 24), new Color(jpg1.getRGB(0, 1)));
    assertEquals(new Color(23, 24, 26), new Color(jpg1.getRGB(0, 2)));

    BufferedImage png1 = this.brighten2.imageOutput();
    assertEquals(new Color(14, 15, 16), new Color(png1.getRGB(0, 0)));
    assertEquals(new Color(17, 18, 19), new Color(png1.getRGB(0, 1)));
    assertEquals(new Color(20, 21, 22), new Color(png1.getRGB(1, 0)));

  }

  @Test
  public void darken() {

    BufferedImage bmp1 = this.darken4.imageOutput();
    assertEquals(new Color(1, 2, 3), new Color(bmp1.getRGB(0, 0)));
    assertEquals(new Color(4, 5, 6), new Color(bmp1.getRGB(0, 1)));
    assertEquals(new Color(7, 8, 9), new Color(bmp1.getRGB(0, 2)));

    BufferedImage png1 = this.darken2.imageOutput();
    assertEquals(new Color(1, 2, 3), new Color(png1.getRGB(0, 0)));
    assertEquals(new Color(4, 5, 6), new Color(png1.getRGB(0, 1)));
    assertEquals(new Color(7, 8, 9), new Color(png1.getRGB(1, 0)));

    BufferedImage jpg1 = this.darken3.imageOutput();
    assertEquals(new Color(3, 4, 6), new Color(jpg1.getRGB(0, 0)));
    assertEquals(new Color(6, 7, 9), new Color(jpg1.getRGB(0, 1)));
    assertEquals(new Color(8, 9, 11), new Color(jpg1.getRGB(0, 2)));

    Color[][] ppm1 = this.darken1.imageOutput();
    assertEquals(new Color(1, 2, 3), ppm1[0][0]);
    assertEquals(new Color(4, 5, 6), ppm1[0][1]);
    assertEquals(new Color(7, 8, 9), ppm1[1][0]);
  }

}
