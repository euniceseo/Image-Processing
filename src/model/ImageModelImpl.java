package model;

/**
 * This class represents the model for an image processor. It has operations to alter an image and
 * holds all images it creates in a map.
 */
public class ImageModelImpl implements ImageModel {

  private Pixel[][] image;
  private int maxValue;

  /**
   * Initializes the model with a default 2D pixel array, name, and maxValue to be updated later.
   */
  public ImageModelImpl() {
    this(new Pixel[1][0]);
  }

  /**
   * Initializes the model with the given 2D pixel array, name, and maxValue.
   *
   * @param image the current image that the model can work with
   */
  public ImageModelImpl(Pixel[][] image) {
    this.image = image;
    this.maxValue = 255;
  }

  @Override
  public void setMaxValue(int newValue) {
    this.maxValue = newValue;
  }

  @Override
  public int getWidth() {
    return this.image[0].length;
  }

  @Override
  public int getHeight() {
    return this.image.length;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public Pixel[][] copy() {
    Pixel[][] imageCopy = new Pixel[this.image.length][this.image[0].length];
    for (int i = 0; i < this.image.length; i++) {
      for (int j = 0; j < this.image[0].length; j++) {
        imageCopy[i][j] = new Pixel(this.image[i][j].getRed(),
                this.image[i][j].getGreen(), this.image[i][j].getBlue());
      }
    }
    return imageCopy;
  }

  @Override
  public ImageModel flipHorizontal() {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < (this.image.length); i++) {
      for (int j = 0; j < (this.image[i].length / 2); j++) {
        Pixel temp = imageCopy[i][imageCopy[0].length - j - 1];
        imageCopy[i][imageCopy[0].length - j - 1] = imageCopy[i][j];
        imageCopy[i][j] = temp;
      }
    }

    return new ImageModelImpl(imageCopy);
  }

  @Override
  public ImageModel flipVertical() {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < image.length / 2; i++) {
      for (int j = 0; j < image[0].length; j++) {
        Pixel temp = imageCopy[imageCopy.length - i - 1][j];
        imageCopy[imageCopy.length - i - 1][j] = imageCopy[i][j];
        imageCopy[i][j] = temp;
      }
    }

    return new ImageModelImpl(imageCopy);
  }

  @Override
  public ImageModel changeBrightness(int increment) {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < imageCopy.length; i++) {
      for (int j = 0; j < imageCopy[0].length; j++) {

        imageCopy[i][j].setRed(this.clamp(imageCopy[i][j].getRed() + increment));
        imageCopy[i][j].setBlue(this.clamp(imageCopy[i][j].getBlue() + increment));
        imageCopy[i][j].setGreen(this.clamp(imageCopy[i][j].getGreen() + increment));
      }
    }

    return new ImageModelImpl(imageCopy);
  }

  private void imageSetter(int component, int x, int y, Pixel[][] image) {
    image[x][y].setRed(component);
    image[x][y].setGreen(component);
    image[x][y].setBlue(component);
  }

  @Override
  public ImageModel component(String str) {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < imageCopy.length; i++) {
      for (int j = 0; j < imageCopy[i].length; j++) {
        int redInt = imageCopy[i][j].getRed();
        int greenInt = imageCopy[i][j].getGreen();
        int blueInt = imageCopy[i][j].getBlue();

        if (str.equalsIgnoreCase("red")) {
          this.imageSetter(redInt, i, j, imageCopy);
        }

        if (str.equalsIgnoreCase("green")) {
          this.imageSetter(greenInt, i, j, imageCopy);
        }

        if (str.equalsIgnoreCase("blue")) {
          this.imageSetter(blueInt, i, j, imageCopy);
        }

        if (str.equalsIgnoreCase("value")) {
          int maxIntHelper = Math.max(blueInt, Math.max(greenInt, redInt));
          this.imageSetter(maxIntHelper, i, j, imageCopy);
        }

        if (str.equalsIgnoreCase("intensity")) {
          int average = (blueInt + greenInt + redInt) / 3;
          this.imageSetter(average, i, j, imageCopy);
        }
      }
    }
    return new ImageModelImpl(imageCopy);
  }

  @Override
  public ImageModel greyscale() {
    Double[][] lumaMatrix = {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};

    return this.applyColorTransformation(lumaMatrix);
  }

  @Override
  public ImageModel blur() {
    Double[][] blurMatrix = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
        {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}};

    return this.applyKernelTransformation(blurMatrix);
  }

  @Override
  public ImageModel sharpen() {
    Double[][] sharpenMatrix = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};

    return this.applyKernelTransformation(sharpenMatrix);
  }

  @Override
  public ImageModel sepia() {
    Double[][] sepiaMatrix = {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    return this.applyColorTransformation(sepiaMatrix);
  }

  private ImageModel applyColorTransformation(Double[][] matrix) {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < imageCopy.length; i++) {
      for (int j = 0; j < imageCopy[i].length; j++) {
        int r = imageCopy[i][j].getRed();
        int g = imageCopy[i][j].getGreen();
        int b = imageCopy[i][j].getBlue();

        double newRed = matrix[0][0] * r + matrix[0][1] * g + matrix[0][2] * b;
        double newGreen = matrix[1][0] * r + matrix[1][1] * g + matrix[1][2] * b;
        double newBlue = matrix[2][0] * r + matrix[2][1] * g + matrix[2][2] * b;

        imageCopy[i][j].setRed(this.clamp(newRed));
        imageCopy[i][j].setGreen(this.clamp(newGreen));
        imageCopy[i][j].setBlue(this.clamp(newBlue));
      }
    }

    return new ImageModelImpl(imageCopy);
  }

  // applies the given kernel matrix to an image
  private ImageModel applyKernelTransformation(Double[][] matrix) {
    Pixel[][] imageCopy = this.copy();

    for (int i = 0; i < imageCopy.length; i++) {
      for (int j = 0; j < imageCopy[i].length; j++) {
        imageCopy[i][j] = this.applyKernel(matrix, i, j, imageCopy);
      }
    }

    return new ImageModelImpl(imageCopy);
  }

  // applies the given kernel matrix to the pixel at (x, y)
  private Pixel applyKernel(Double[][] matrix, int x, int y, Pixel[][] image) {
    double r = 0;
    double g = 0;
    double b = 0;

    for (int i = -(matrix.length - 1) / 2; i <= (matrix.length - 1) / 2; i++) {
      for (int j = -(matrix.length - 1) / 2; j <= (matrix.length - 1) / 2; j++) {
        if (this.inBounds(x + i, y + j)) {
          Pixel pixel = image[x + i][y + j];

          r += pixel.getRed() * matrix[i + (matrix.length - 1) / 2][j + (matrix.length - 1) / 2];
          g += pixel.getGreen() * matrix[i + (matrix.length - 1) / 2][j + (matrix.length - 1) / 2];
          b += pixel.getBlue() * matrix[i + (matrix.length - 1) / 2][j + (matrix.length - 1) / 2];
        }
      }
    }

    r = this.clamp(r);
    g = this.clamp(g);
    b = this.clamp(b);

    return new Pixel((int) r, (int) g, (int) b);
  }

  // determines if the given x and y is in the bounds of the image
  private boolean inBounds(int x, int y) {
    return (x < this.image.length && x >= 0) && (y < this.image[0].length && y >= 0);
  }

  // clamps the given value to 0 if it's negative or 255 if it's greater than 255
  private int clamp(double i) {
    if (i > 255) {
      return 255;
    } else if (i < 0) {
      return 0;
    } else {
      return (int) i;
    }
  }
}
