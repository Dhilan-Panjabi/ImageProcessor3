package util;
import Color.Pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }


  /**
   * read using the image type and then return the color of those images.
   *
   * @param type the given file type
   * @throws FileNotFoundException if the file does not exist.
   */
  public static void readUsingImageIO(String type) throws FileNotFoundException {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new FileInputStream(type));
    } catch (FileNotFoundException e) {
      System.out.println("file" + type + "was not found");
    } catch (IOException e) {
      System.out.println("file" + type + "is not supported");
    }
    for (int row = 0; row < image.getWidth(); row++) {
      for (int col = 0; col < image.getHeight(); col++) {
        Color pixel = new Color(image.getRGB(row, col));

        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();

        System.out.println("Pixel color - (" + row + "," + col + " ) " +
                "-> " + red + " ," + green + " ," + blue);

      }
    }
  }

  /**
   * converts the ppm file to a buffered.
   *
   * @param ppmImage given color array of the ppm image
   * @return a buffered image from the ppm image
   */
  public static BufferedImage ppmToBuffered(Color[][] ppmImage) {

    int width = ppmImage.length;
    int height = ppmImage[0].length;

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        image.setRGB(row, col, ppmImage[row][col].getRGB());
      }
    }

    return image;

  }

  /**
   * takes in a buffered image and changes it into a ppm format.
   *
   * @param image the given buffered image image
   * @return the ppm image
   */
  public static Color[][] bufferedToPPM(BufferedImage image) {

    int width = image.getWidth();
    int height = image.getHeight();

    Color[][] ppmImage = new Color[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        ppmImage[row][col] = new Color(image.getRGB(row, col));
      }
    }
    return ppmImage;
  }

  /**
   * converts the ppm file to a buffered file.
   *
   * @param filename the given file name
   * @return the buffered image
   */
  public static BufferedImage ppmFileToBuffered(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    sc.nextInt();

    Color[][] ppmImage = new Color[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {

        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();

        ppmImage[row][col] = new Color(red, green, blue);
      }
    }
    return ppmToBuffered(ppmImage);
  }

  public static void PPMSave(String path, Color[][] color){
    int imageWidth = color.length;
    int imageHeight = color[0].length;
    int val = 0;

    StringBuilder sb = new StringBuilder();
    for(Color[] imgColor: color){
      for(Color p: imgColor){
        int r = p.getRed();
        int g = p.getGreen();
        int b = p.getBlue();
        if(val < 255 && (r > 255 || g > 255 || b > 255)){
          val = Pixel.maxValue(p);
        }
        sb.append(r + " " + g + " " + b + " ");
      }
      sb.append(System.lineSeparator());

    }
    try{
      DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(path));
      String data = "P3" + System.lineSeparator() + imageWidth + " " +
              imageHeight + System.lineSeparator() + val + System.lineSeparator();
      outputStream.write(data.getBytes(StandardCharsets.UTF_8));
      outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
      outputStream.close();
    }catch(IOException e){
      throw new IllegalArgumentException("cannot save file");
    }

  }

  /**
   * required javadoc.
   *
   * @param args given args
   */
  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

