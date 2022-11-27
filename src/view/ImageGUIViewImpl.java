package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImageGUIController;

public class ImageGUIViewImpl extends JFrame implements ImageGUIView {

  private final JButton commands;

  private final JButton instructions;

  private final JButton newImageMessage;

  private boolean message;

  private final JButton terminateProgram;


  private final JButton saveImageButton;

  private final JButton loadButton;

  private final JList<String> imageNameList;

  private final DefaultListModel<String> imageName;

  private final MainImage panel;

  private final JScrollPane scroll;

  private final ImageRGBHistogram rgbHistogram;

  private final JScrollPane rgbHistogramScroll;

  private final JTextField userInput;

  private final JButton savePathButton;

  private final JLabel savePath;

  private final JTextField saveName;

  private final JComboBox<String> givenCommands;

  private final JComboBox<Integer> RBGincrement;

  private final JComboBox<String> fileType;

  public ImageGUIViewImpl(){
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(false);
    this.setMinimumSize(new Dimension(1000, 1000));
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new MainImage();
    this.scroll = new JScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.scroll.setPreferredSize(this.panel.getPreferredSize());
    this.scroll.setViewportView(this.panel);
    this.add(this.scroll, BorderLayout.CENTER);

    this.rgbHistogram = new ImageRGBHistogram();
    this.rgbHistogramScroll = new JScrollPane(this.rgbHistogram,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.rgbHistogram.setPreferredSize(new Dimension(500, 500));
    this.rgbHistogramScroll.setPreferredSize(rgbHistogram.getPreferredSize());
    this.rgbHistogramScroll.setViewportView(this.rgbHistogram);
    this.add(this.rgbHistogramScroll, BorderLayout.LINE_END);

  //
    JPanel listOfImages = new JPanel();
    listOfImages.setPreferredSize(new Dimension(200, 200));
    listOfImages.setBorder(BorderFactory.createTitledBorder("List of Images"));
    JScrollPane scroll = new JScrollPane(listOfImages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setViewportView(listOfImages);

    //
    this.imageName = new DefaultListModel<>();
    this.imageNameList = new JList<>(this.imageName);
    this.imageNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfImages.add(this.imageNameList);
    this.add(scroll, BorderLayout.LINE_START);

    //
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(2, 1, 60, 0));
    JPanel import1 = new JPanel();
    this.loadButton = new JButton("Load Image");
    this.loadButton.setActionCommand("Load Image");
    import1.add(this.loadButton);
    import1.setSize(new Dimension(40, 10));
    import1.add(this.loadButton);
    this.add(import1, BorderLayout.PAGE_START);
    panel1.add(import1);

    //
    JPanel save1 = new JPanel();

    this.savePath = new JLabel();
    save1.add(this.savePath);

    this.savePathButton = new JButton("Save Path");
    this.savePathButton.setActionCommand("Save Path");
    save1.add(this.savePathButton);

    this.saveImageButton = new JButton("Save Image");
    this.saveImageButton.setActionCommand("Save Image");
    save1.add(this.saveImageButton);

    this.saveName = new JTextField();
    save1.add(this.saveName);

    this.fileType = new JComboBox<>();
    this.fileType.addItem("ppm");
    this.fileType.addItem("jpeg");
    this.fileType.addItem("png");
    this.fileType.addItem("bmp");
    save1.add(this.fileType);
    panel1.add(save1);

    this.givenCommands = new JComboBox<>();
    this.givenCommands.setActionCommand("Given Commands");
    this.givenCommands.addItem("Blur");
    this.givenCommands.addItem("Sharpen");
    this.givenCommands.addItem("Sepia");
    this.givenCommands.addItem("Greyscale");
    this.givenCommands.addItem("vertical flip");
    this.givenCommands.addItem("horizontal flip");
    this.givenCommands.addItem("value component");
    this.givenCommands.addItem("luma component");
    this.givenCommands.addItem("red component");
    this.givenCommands.addItem("green component");
    this.givenCommands.addItem("blue component");
    this.givenCommands.addItem("intensity component");
    panel1.add(this.givenCommands);
    this.add(panel1, BorderLayout.PAGE_END);


    this.RBGincrement = new JComboBox<>();
    this.RBGincrement.setActionCommand("RBG increment");
    for(int i = 0; i < 256; i = i+1){
      this.RBGincrement.addItem(i);
    }
    givenCommands.add(this.RBGincrement);
    panel1.add(givenCommands);
    this.add(panel1, BorderLayout.PAGE_START);

    JPanel actionButton = new JPanel();
    actionButton.setLayout(new FlowLayout());
    this.add(actionButton, BorderLayout.SOUTH);

    this.userInput = new JTextField();
    actionButton.add(this.userInput);

    this.commands = new JButton("Run commands");
    this.commands.setActionCommand("Run commands");
    actionButton.add(this.commands);

    this.instructions = new JButton("Instructions");
    actionButton.add(this.instructions);


    this.newImageMessage = new JButton("New message");
    this.newImageMessage.setActionCommand("New message");
    this.message = true;
    actionButton.add(this.newImageMessage);

    this.terminateProgram = new JButton("Exit Program");
    actionButton.add(this.terminateProgram);
    this.getRootPane().setDefaultButton(this.commands);


    this.pack();
    this.setVisible(true);
  }
  @Override
  public void update() {
    this.panel.repaint();
    this.rgbHistogram.repaint();
    this.imageNameList.repaint();
    this.repaint();
  }

  @Override
  public void renderErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public String getUserInput() {
    String[] inputs = this.userInput.getText().split(" ");
    if(inputs.length > 2){
      return this.inputTextField(this.userInput);
    }else{
      return "";
    }
  }

  private String inputTextField(JTextField field){
    String input = field.getText();
    field.setText("");
    return input;
  }

  @Override
  public void clearUserInput() {
  }


  @Override
  public void requestFeatures(ImageGUIController control) {
    this.newImageMessage.addActionListener((event) -> this.message = !this.message);
    this.instructions.addActionListener((event) -> control.returnInstruction());
    this.terminateProgram.addActionListener((event) -> {
      control.exitProgram();
      System.exit(0);
    });

    this.commands.addActionListener((evt) -> {
      if (this.userInput.getText().split(" ").length > 2) {
        control.doInputCommands(this.inputTextField(this.userInput));
        control.processGivenCommands();
      }
    });

    this.loadButton.addActionListener((evt) -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, PNG, BMP, PPM", "jpg", "png", "bmp", "ppm");
      fileChooser.setFileFilter(filter);
      int value = fileChooser.showOpenDialog(this);
      if (value == JFileChooser.APPROVE_OPTION) {
        control.loadImageToGUI(this.path(fileChooser));
        control.updateModel(); //
        this.update();
      }
    });

    this.savePathButton.addActionListener((evt) -> {
      final JFileChooser dirChooser = new JFileChooser(".");
      dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int option = dirChooser.showOpenDialog(this);
      if (option == JFileChooser.APPROVE_OPTION) {
        this.savePath.setText(this.path(dirChooser));
      }
    });

    this.saveImageButton.addActionListener((evt) -> {
      String image = this.getSaveName();
      String currentImage = control.getImageName();
      if (image.equals("")) {
        image = currentImage;
      }
      String path = this.savePath.getText() + "\\";
      if (path.equals("\\")) {
        path = " ";
      }
      String filetype = (String) this.fileType.getSelectedItem();
      String filePath = path + image + filetype;
      control.doInputCommands("save " + filePath + " " + currentImage);
      control.processGivenCommands();
      this.renderMessage(
              this.savePath.getText() + "\\" + image + filetype + " has been saved!");
    });

    this.givenCommands.addItemListener((evt) -> {
      if (this.givenCommands.getSelectedIndex() > 0) {
        String image = control.getImageName();
        String fullCommand;
        if (image != null) {
          String command = (String) this.givenCommands.getSelectedItem();
          switch (command) {
            case "brighten ":
            case "darken ":
              command = command + " "
                      + this.RBGincrement.getSelectedItem();
              break;
            default:
          }
          fullCommand = command + " " + image + " " + image;
          control.doInputCommands(fullCommand);
          control.processGivenCommands();
          this.givenCommands.setSelectedIndex(0);
        }
      }
    });

    this.imageNameList.addListSelectionListener((evt) -> {
      try {
        String selected = this.imageNameList.getSelectedValue();
        if (!selected.equals(control.getImageName())) {
          control.loadOtherImage(selected);
        }
      } catch (NullPointerException ex) {
        control.updateModel();
      }
    });

  }

  private String path(JFileChooser fileChooser) {
    File tempFile = new File("");
    File f = fileChooser.getSelectedFile();
    String removePath = tempFile.getAbsolutePath();
    String changePath = f.getAbsolutePath();
    if (removePath.length() > changePath.length()) {
      return removePath;
    } else {
      return changePath.substring(removePath.length() + 1);
    }
  }

  private String getSaveName() {
    return this.inputTextField(this.saveName);
  }



  @Override
  public void render() {
    this.setVisible(true);
  }

  @Override
  public void renderImageToGUI(BufferedImage image, String name) {
    this.newUserImageName(name);

    this.panel.placeImageOnScreen(image);
    this.panel.setPreferredSize(new Dimension(this.panel.getWidth(), this.panel.getHeight()));

    this.rgbHistogram.imageSetter(image);
    this.rgbHistogramScroll.setPreferredSize(new Dimension(this.rgbHistogram.getWidth(),
            this.rgbHistogram.getHeight()));

    this.update();

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void newUserImageName(String name) {
    if(!this.imageName.contains(name)){
      this.imageName.addElement(name);
      this.imageNameList.repaint();
      if(message){
        this.renderMessage("New image added to the list");
      }
    }
  }

  @Override
  public void renderMessage(String m) {
    JOptionPane.showMessageDialog(this, m, "", JOptionPane.INFORMATION_MESSAGE);

  }

  @Override
  public void renderImage(String userImageName) throws IOException {
    // TODO Auto-generated method stub
  }
}
