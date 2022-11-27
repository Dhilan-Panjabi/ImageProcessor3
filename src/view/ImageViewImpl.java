package view;

import java.io.IOException;

import model.ImageModel;

/**
 * Represents the view of the editor.
 */
public class ImageViewImpl implements ImageView {

  private final ImageModel m;

  private final Appendable out;

  /**
   * The constructor which only takes in the model.
   *
   * @param m given model
   */
  public ImageViewImpl(ImageModel m) {
    this(m, System.out);
  }

  /**
   * constructor which takes in the model m and an appendable.
   *
   * @param m   given model
   * @param out the appendable output
   * @throws IllegalArgumentException if either fields are null
   */
  public ImageViewImpl(ImageModel m, Appendable out) throws IllegalArgumentException {
    if (m == null || out == null) {
      throw new IllegalArgumentException("must pass through a model and output");
    }
    this.m = m;
    this.out = out;
  }

  @Override
  public String toString() {
    StringBuilder stringImage = new StringBuilder();
    for (int row = 0; row < this.m.getRow(); row = row + 1) {
      for (int col = 0; col < this.m.getCol(); col = col + 1) {
        stringImage.append(this.m.retrievePixel(row, col).toString());
      }
      stringImage.append(System.lineSeparator());
    }
    return stringImage.toString();
  }


  @Override
  public void renderMessage(String msg) throws IOException {
    this.out.append(msg + System.lineSeparator());

  }

  @Override
  public void renderImage(String userImageName) throws IOException {
    this.out.append(this.toString());

  }
}
