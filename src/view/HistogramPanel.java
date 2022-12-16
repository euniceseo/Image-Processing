package view;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Histogram;

/**
 * Represents a HistogramPanel that contains a Histogram.
 */
public class HistogramPanel extends JPanel {
  private final Histogram histogram;

  /**
   * Constructs a histogram panel.
   *
   * @param histogram the histogram in the panel
   */
  public HistogramPanel(Histogram histogram) {
    super();
    this.setBackground(Color.WHITE);
    this.histogram = histogram;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < 256; i++) {
      g2d.setColor(Color.RED);
      g2d.drawLine(i, 200 - this.histogram.getHistogramType("red").get(i) / 15, i, 200);

      g2d.setColor(Color.GREEN);
      g2d.drawLine(i, 200 - this.histogram.getHistogramType("green").get(i) / 15, i, 200);

      g2d.setColor(Color.BLUE);
      g2d.drawLine(i, 200 - this.histogram.getHistogramType("blue").get(i) / 15, i, 200);

      g2d.setColor(Color.YELLOW);
      g2d.drawLine(i, 200 - this.histogram.getHistogramType("intensity").get(i) / 15, i, 200);
    }
  }
}
