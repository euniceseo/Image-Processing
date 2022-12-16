package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a histogram for an image.
 */
public class Histogram {
  private final Map<Integer, Integer> red;
  private final Map<Integer, Integer> green;
  private final Map<Integer, Integer> blue;
  private final Map<Integer, Integer> intensity;

  /**
   * Constructs a histogram.
   */
  public Histogram() {
    this.red = new HashMap<>();
    this.green = new HashMap<>();
    this.blue = new HashMap<>();
    this.intensity = new HashMap<>();

    for (int i = 0; i < 256; i++) {
      this.red.put(i, 0);
      this.green.put(i, 0);
      this.blue.put(i, 0);
      this.intensity.put(i, 0);
    }
  }

  /**
   * Fills a histogram with the RGB and intensity data from a given ImageModel.
   *
   * @param model the model to take the component data from
   */
  public void fillHistogram(ImageModel model) {
    Pixel[][] image = model.copy();

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        Pixel pixel = image[i][j];
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int intensity = (r + g + b) / 3;

        this.red.put(r, this.red.get(r) + 1);
        this.green.put(g, this.green.get(g) + 1);
        this.blue.put(b, this.blue.get(b) + 1);
        this.intensity.put(intensity, this.intensity.get(intensity) + 1);
      }
    }
  }

  /**
   * Returns the histogram that corresponds to the given component.
   *
   * @param component the type of histogram. Can be "red", "green", "blue", or "intensity"
   * @return the histogram that corresponds to the given component
   */
  public Map<Integer, Integer> getHistogramType(String component) {
    if (component.equalsIgnoreCase("red")) {
      return this.red;
    } else if (component.equalsIgnoreCase("green")) {
      return this.green;
    } else if (component.equalsIgnoreCase("blue")) {
      return this.blue;
    } else if (component.equalsIgnoreCase("intensity")) {
      return this.intensity;
    } else {
      return null;
    }
  }
}
