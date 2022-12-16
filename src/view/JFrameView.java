package view;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneLayout;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import controller.Features;
import model.Histogram;
import model.ImageLibrary;
import model.ImageModel;
import model.Pixel;

/**
 * Represents the Java Swing GUI of an image processor. Implements the View interface's methods.
 */
public class JFrameView extends JFrame implements View {

  // list of loaded images
  private final ImageLibrary images;

  // load panel and button
  private final JButton loadFileButton;

  // save panel and button
  private final JButton saveFileButton;

  // list of commands in a combobox
  private final JComboBox<String> comboBox;

  // main panel
  private final JPanel mainPanel;

  // image and histogram panel
  private JPanel imageHistogramPanel;

  /**
   * Constructs a new JFrameView.
   *
   * @param images the library of images this view can work with
   */
  public JFrameView(ImageLibrary images) {
    super();

    // list
    JPanel saveFilePanel;
    JPanel loadFilePanel;
    JPanel commandComboBox;
    JLabel commandComboBoxDisplay;
    JScrollPane mainScrollPane;

    // initializes images
    this.images = images;

    this.setTitle("Image Processing");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    // initialize main panel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setSize(200, 200);

    // load an image
    loadFilePanel = new JPanel();
    loadFilePanel.setLayout(new FlowLayout());
    loadFileButton = new JButton("Load a file");
    loadFileButton.setActionCommand("Load file");
    loadFilePanel.add(loadFileButton);
    mainPanel.add(loadFilePanel);
    mainPanel.add(loadFileButton);

    // save an image
    saveFilePanel = new JPanel();
    saveFilePanel.setLayout(new FlowLayout());
    saveFileButton = new JButton("Save a file");
    saveFileButton.setActionCommand("Save file");
    saveFilePanel.add(saveFileButton);
    mainPanel.add(saveFilePanel);
    mainPanel.add(saveFileButton);

    // command combo box
    commandComboBox = new JPanel();
    commandComboBox.setBorder(BorderFactory.createTitledBorder("Combo boxes"));
    commandComboBox.setLayout(new BoxLayout(commandComboBox, BoxLayout.PAGE_AXIS));
    mainPanel.add(commandComboBox);

    commandComboBoxDisplay = new JLabel("Image Operations");
    commandComboBox.add(commandComboBoxDisplay);
    String[] options = {"Blur", "Brighten", "Component", "Greyscale",
        "Flip horizontally", "Sepia", "Sharpen", "Flip vertically"};
    comboBox = new JComboBox<String>();
    comboBox.setActionCommand("Size options");
    for (int i = 0; i < options.length; i++) {
      comboBox.addItem(options[i]);
    }
    commandComboBox.add(comboBox);

    // scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    mainScrollPane.setLayout(new ScrollPaneLayout());
    mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    // histogram + image
    imageHistogramPanel = new JPanel();

    this.add(mainScrollPane);
    this.makeVisible();
  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.imageHistogramPanel.removeAll();
  }

  private BufferedImage parseThroughImage(ImageModel image) {
    BufferedImage helper = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel pixel = image.copy()[i][j];

        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();

        Color color = new Color(r, g, b);
        int rgb = color.getRGB();
        helper.setRGB(j, i, rgb);
      }
    }
    return helper;
  }

  @Override
  public void displayImage(ImageModel image) {

    ImageLibrary images = this.images;

    // visualizing a histogram
    JPanel histogramPanel;
    Histogram histogram;

    // visualizing an image
    JLabel imageLabel;
    JPanel imagePanel;

    BufferedImage bufferedImage = this.parseThroughImage(image);

    // image
    ImageIcon iconImage = new ImageIcon(bufferedImage);
    imageLabel = new JLabel(iconImage);
    imageLabel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(300, 300));
    imagePanel.add(imageLabel);

    // histogram
    histogram = new Histogram();
    histogram.fillHistogram(image);
    histogramPanel = new HistogramPanel(histogram);
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramPanel.repaint();
    histogramPanel.setPreferredSize(new Dimension(275, 207));

    // image histogram
    imageHistogramPanel.add(imagePanel);
    imageHistogramPanel.add(histogramPanel);
    mainPanel.add(imageHistogramPanel);

    this.makeVisible();
  }

  @Override
  public void addFeatures(Features features) {
    loadFileButton.addActionListener(evt -> features.load());
    saveFileButton.addActionListener(evt -> features.save(JOptionPane.showInputDialog(
            "Local image name to be saved: ")));
    comboBox.addActionListener(evt -> features.command(JOptionPane.showInputDialog(
            "Local image name to apply transformation to: "),
            JOptionPane.showInputDialog("New local name to save transformed image as: "),
            (String) comboBox.getSelectedItem()));
  }
}