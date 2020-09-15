package gameslist;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

public class Form implements ActionListener{

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
	private JCheckBox chckbxDelete;
	private JCheckBox chckbxHidden;
	private JButton btnSearch;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_1;
	private DatePicker datePicker;
	private DefaultComboBoxModel<String> model;
	private DefaultComboBoxModel<String> model1;
	private ComboBoxModel<String> model2;
	private DefaultComboBoxModel<String>model3;
	private JScrollBar scrollBar;
	JButton btnNewButton;
	JMenuItem mntmOpen;
	JMenuItem mntmSave;
	XMLReader xmlReader;
	Scraper scraper = new Scraper();;
	private JTextField textField_8;
	private JButton button;
	private JLabel lblDeleted;
	private JTextArea textArea;
	private JCheckBox chckbxReallyDelete;
	private JTextField textField_6;
	private JCheckBox chckbxDescription;
	private JCheckBox chckbxImage;
	private JButton btnScrape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Start the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form window = new Form();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Form() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Create the frame
		frame = new JFrame();
		frame.setBounds(100, 100, 1070, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Create Lables, textfields and comboboxes required on the form
		 */
		
		lblNewLabel = new JLabel("Record #");
		lblNewLabel.setBounds(10, 11, 197, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Path");
		lblNewLabel_1.setBounds(10, 40, 30, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(101, 37, 535, 20);
		textField.addActionListener(this);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(10, 67, 48, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 64, 535, 20);
		textField_1.addActionListener(this);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Image");
		lblNewLabel_3.setBounds(10, 95, 48, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(101, 92, 495, 20);
		textField_2.addActionListener(this);
		frame.getContentPane().add(textField_2);
		
		btnNewButton = new JButton("...");
		btnNewButton.setBounds(606, 91, 30, 23);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(10, 126, 48, 14);
		frame.getContentPane().add(lblRating);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 123, 44, 20);
		textField_3.addActionListener(this);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblReleaseDate = new JLabel("Release Date");
		lblReleaseDate.setBounds(10, 151, 80, 14);
		frame.getContentPane().add(lblReleaseDate);
		
		textField_4 = new JTextField();
		textField_4.setBounds(100, 148, 146, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDevelop = new JLabel("Developer");
		lblDevelop.setBounds(10, 176, 80, 14);
		frame.getContentPane().add(lblDevelop);
		
		textField_5 = new JTextField();
		textField_5.setBounds(100, 173, 146, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setBounds(10, 207, 58, 14);
		frame.getContentPane().add(lblPublisher);
		
		model = new DefaultComboBoxModel<String>(new String[] {""});
		comboBox = new JComboBox<String>(model);
		comboBox.setEditable(true);
		comboBox.setBounds(100, 204, 146, 20);
		comboBox.addActionListener(this);
		frame.getContentPane().add(comboBox);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(10, 232, 48, 14);
		frame.getContentPane().add(lblGenre);
		
		//Create a combo box using the default model with the passed in strings as options
		model1 = new DefaultComboBoxModel<String>(new String[] {"", "Action", "Adventure", "Beat'em up", "Board Game", "Casino", "Fight", "Lightgun Shooter", "Pinball", "Platform", "Puzzle-Game", "Race/Driving", "Role Playing Game", "Shoot'em Up", "Shoot'em Up Horizontal", "Shoot'em Up Vertical", "Shooter 1st Person", "Shooter Run and Gun", "Simulation", "Sports", "Sports - Baseball", "Sports - Basketball", "Sports - Boxing", "Sports- Fighting", "Sports - Football", "Sports - Golf", "Sports - Soccer", "Strategy", "Various"});
		comboBox_1 = new JComboBox<String>(model1);
		comboBox_1.setBounds(100, 229, 146, 20);
		comboBox_1.addActionListener(this);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(10, 259, 48, 14);
		frame.getContentPane().add(lblPlayers);
		
		chckbxDelete = new JCheckBox("Delete");
		chckbxDelete.setBounds(100, 325, 64, 23);
		chckbxDelete.addActionListener(this);
		frame.getContentPane().add(chckbxDelete);
		
		chckbxHidden = new JCheckBox("Hidden");
		chckbxHidden.setBounds(174, 325, 72, 23);
		chckbxHidden.addActionListener(this);
		frame.getContentPane().add(chckbxHidden);
		
		JLabel lblPlayCount = new JLabel("Play Count");
		lblPlayCount.setBounds(10, 290, 73, 14);
		frame.getContentPane().add(lblPlayCount);
		
		textField_8 = new JTextField();
		textField_8.setBounds(100, 287, 30, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(253, 123, 400, 400);
		frame.getContentPane().add(lblNewLabel_4);
		
		btnNewButton_1 = new JButton("Pick Date");
		btnNewButton_1.setBounds(154, 123, 92, 23);
		btnNewButton_1.addActionListener(this);
		frame.getContentPane().add(btnNewButton_1);
		
		button = new JButton(">");
		button.setBounds(198, 255, 48, 23);
		button.addActionListener(this);
		frame.getContentPane().add(button);
		
		lblDeleted = new JLabel("");
		lblDeleted.setBounds(294, 11, 58, 14);
		frame.getContentPane().add(lblDeleted);
		
		//Create a combo box using the default model with the passed in strings as options
		model2 = new DefaultComboBoxModel<String>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }); 
		comboBox_2 = new JComboBox<String>(model2);
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(101, 255, 48, 22);
		comboBox_2.addActionListener(this);
		frame.getContentPane().add(comboBox_2);
		
		//Create a combo box using the default model with no starting options
		model3 = new DefaultComboBoxModel<String>(new String[] { "" });
		comboBox_3 = new JComboBox<String>(model3);
		comboBox_3.setBounds(101, 400, 145, 22);
		frame.getContentPane().add(comboBox_3);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setBounds(10, 404, 48, 14);
		frame.getContentPane().add(lblSystem);
		
		//Create a scrollbar in order to switch between games
		scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(10, 528, 1034, 20);
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				xmlReader.setIdx(scrollBar.getValue());
				resetbx();
				fillFields();
			}});
		frame.getContentPane().add(scrollBar);
		
		chckbxReallyDelete = new JCheckBox("Really Delete?");
		chckbxReallyDelete.setBounds(101, 463, 106, 23);
		chckbxReallyDelete.addActionListener(this);
		frame.getContentPane().add(chckbxReallyDelete);

		//Create a text area which the user can edit, including pasting
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(646, 40, 397, 449);
		frame.getContentPane().add(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(47);
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			//Handle the pasting
			@Override
			public void keyPressed(KeyEvent e) {
				//Check the user has pressed Ctrl-V (Paste)
				if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
						Toolkit tk = Toolkit.getDefaultToolkit();
						//Get contents of the clipboard
						Clipboard cb = tk.getSystemClipboard();
						try {
							//Get it as a string
							String result = (String) cb.getData(DataFlavor.stringFlavor);
							//Remove any references in square brackets
							//This helps if the text is copied from Wikipedia!
							for (int i = 0; i < 21; i++) {
								result = result.replace("[" + i + "]", "");
							}
							for (int i = 97; i < 123; i++) {
								result = result.replace("[" + (char) i + "]", "");
							}
							
							result = result.replace("[citation needed]", "");
							
							//Finally, set the required description
							xmlReader.getItem().setDesc(result);
							
							fillFields();
						} catch (UnsupportedFlavorException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!textArea.getText().equals(xmlReader.getItem().getDesc()))
						xmlReader.getItem().setDesc(textArea.getText());
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(663, 40, 381, 449);
		frame.getContentPane().add(scrollPane);
		
		textField_6 = new JTextField();
		textField_6.setBounds(663, 9, 282, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(955, 7, 89, 23);
		btnSearch.addActionListener(this);
		frame.getContentPane().add(btnSearch);
		
		chckbxDescription = new JCheckBox("Description");
		chckbxDescription.setBounds(556, 7, 97, 23);
		frame.getContentPane().add(chckbxDescription);
		
		chckbxImage = new JCheckBox("Image");
		chckbxImage.setBounds(490, 7, 64, 23);
		frame.getContentPane().add(chckbxImage);
		
		btnScrape = new JButton("Scrape");
		btnScrape.setBounds(101, 433, 89, 23);
		btnScrape.addActionListener(this);
		frame.getContentPane().add(btnScrape);
		chckbxReallyDelete.setVisible(false);
		
		//Create a menu bar with open and save options
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(this);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		mntmSave.addActionListener(this);
	}

	/**
	 * Handle all actions
	 */
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		//Checks the source of the event to ensure the correct thing is done
		
		//Get games list
		if (e.getSource() == mntmOpen) {
			//Opens a file chooser so the user can select the desired gamelist
			JFileChooser fc = new JFileChooser();
			//Filters the file chooser
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
			fc.setFileFilter(filter);
			//Sets the initially selected file
			fc.setSelectedFile(new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\"));
			
			//Displays the file chooser
			int returnVal = fc.showOpenDialog(mntmOpen);
			//Gets selected file
			File tmp = fc.getSelectedFile();
			//Get the required details from the file
			String filename = tmp.getPath();
			String file =  tmp.getAbsoluteFile().getName();
			String filepath = filename.replace(file, "");
			//Reads the selected file
			xmlReader = new XMLReader(filename, filepath);
			//Loads the publishers
			loadPublishers(xmlReader.getArray());
			//Loads all of the system options
			scraper.getSys().forEach((k, v)  -> {
				model3.addElement(v);
			});
			
			//Setup the scrollbar
			scrollBar.setMinimum(0);
			scrollBar.setMaximum(xmlReader.getArray().size() + scrollBar.getBlockIncrement() - 1);
		
		//Save games list
		} else if (e.getSource() == mntmSave) {
			//Saves the edited the gameslist using the xmlwriter
			XMLWriter xmlWriter = new XMLWriter(xmlReader.getArray());
			
			xmlWriter.copyFile();
			
			xmlWriter.writeToFile();
			
			
		} else if (e.getSource() == btnScrape) {
			//Gets all of the details of a certain game using the api
			if (!comboBox_3.getSelectedItem().toString().isBlank())  {
				scraper.scrapeGame(xmlReader.getItem(), model3.getSelectedItem().toString());
				xmlReader.setItem(scraper.returnGame());
			
				//Adds the publisher to the list
				if (!xmlReader.getItem().getPublisher().isBlank())
					model.addElement(xmlReader.getItem().getPublisher());
			}
			
		//Load image
		}  else if (e.getSource() == btnNewButton) {
			//Creates a file chooser
			JFileChooser fc = new JFileChooser();
			//Applies filters
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG files", "png");
			
			fc.setFileFilter(filter);

			//Sets initially selected file to the current image if there is one
			//Or, to a specified location for the rom
			if (!xmlReader.getItem().getImage().equals(""))
				fc.setSelectedFile(new File(xmlReader.getItem().getImage()));
			else {
				fc.setSelectedFile(new File(XMLReader.romPath + "\\downloaded_images\\" + xmlReader.getItem().getName()));
			}
			//Opens the file chooser
			int returnVal = fc.showOpenDialog(btnNewButton);
			//Gets the selected file
			File tmp = fc.getSelectedFile();
			//Gets the details of the file
			String filename = tmp.getPath();
			String file =  tmp.getAbsoluteFile().getName();
			String filepath = filename.replace(file, "");
			
			//Sets the items image to the selected image
			xmlReader.getItem().setImage(filename);
			//Adds the selected file to the relevant textfield
			textField_2.setText(xmlReader.getItem().getImage().strip());
			
		//Move to previous game
		} else if (e.getSource() == chckbxDelete) {
			//If delete is selected then set the item to be deleted
			//Display the confimation delete box
			if (chckbxDelete.isSelected()) {
				xmlReader.getItem().setDeleted("true");
				chckbxReallyDelete.setVisible(true);
			} else {
				//Else, set the item not to be deleted
				xmlReader.getItem().setDeleted("false");
				//Make sure the confirmation box is not set and no longer visible
				Item.setReallyDel("false");
				chckbxReallyDelete.setVisible(false);
				//Loops through all items
				for (Item itm : xmlReader.getArray()) {
					//If the item is deleted
					if (itm.getDeleted()) {
						//Set the confirmation delete box to true and visible
						Item.setReallyDel("true");
						chckbxReallyDelete.setVisible(true);
					}
				}
			}
			
		//Set or de-select hidden check box
		} else if (e.getSource() == chckbxHidden) {
			if (chckbxHidden.isSelected()) {
				xmlReader.getItem().setHidden("true");
			} else {
				xmlReader.getItem().setHidden("false");
			}
		//Set or de-select confirmation delete check box
		} else if (e.getSource() == chckbxReallyDelete) {
			if (chckbxReallyDelete.isSelected())
				Item.setReallyDel("true");
			else
				Item.setReallyDel("false");
			
		//Open date picker
		} else if (e.getSource() == btnNewButton_1) {
			datePicker = new DatePicker(btnNewButton_1, xmlReader.getItem().getDate());
			xmlReader.getItem().setDate(datePicker.formatDate());
			
		//Get image from clipboard -- NOT WORKING
		} else if (e.getSource() == button) {
			loadImage(resize(getImageFromClipboard()));
			
		//Control publisher combo box
		} else if (e.getSource() == comboBox) {
			String str = comboBox.getEditor().getItem().toString();
			if (!str.isBlank()) {
				if (model.getIndexOf(str) == -1) {
					model.addElement(str);
					xmlReader.getItem().setPublisher(str);
				} else {
					xmlReader.getItem().setPublisher(str);
				}
			}
			
		//Control developer combo box
		} else if (e.getSource() == comboBox_1) {
			String str = comboBox_1.getEditor().getItem().toString();
			if (!str.isBlank()) {
				if (model.getIndexOf(str) == -1) {
					model.addElement(str);
					xmlReader.getItem().setDeveloper(str);
				} else {
					xmlReader.getItem().setDeveloper(str);
				}
			}
		
		//Set an items path
		} else if (e.getSource() == textField) {
			Item itm = new Item();
			itm = xmlReader.getItem();
			
			if (!textField.getText().isBlank() && !textField.getText().equals(itm.getPath())) {
				itm.setPath(textField.getText());
			}
		//Edit name
		} else if (e.getSource() == textField_1) {
			Item itm = new Item();
			itm = xmlReader.getItem();
			
			if (!textField_1.getText().isBlank() && !textField_1.getText().equals(itm.getName())) {
				itm.setName(textField_1.getText());
			}
		//Edit rating
		} else if (e.getSource() == textField_3) {
			Item itm = new Item();
			itm = xmlReader.getItem();
			
			if (!textField_3.getText().isBlank() && Double.parseDouble(textField_3.getText()) != itm.getRating()) {
				if (Integer.parseInt(textField_3.getText()) < 10.1)
					itm.setRating(Double.parseDouble(textField_3.getText()));
				else
					textField_3.setText("0.0");
			}
			
		//Players comboBox
		} else if (e.getSource() == comboBox_2) {
			String str = comboBox_2.getEditor().getItem().toString();
			
			xmlReader.getItem().setPlayers(str);
		//Edit image textfield
		} else if (e.getSource() == textField_2) {
			Item itm = new Item();
			itm = xmlReader.getItem();
			
			if (!textField_2.getText().isBlank() && textField_2.getText() != itm.getImage()) {
				itm.setImage(textField_2.getText());
			}
		//Search for a game
		} else if (e.getSource() == btnSearch) {
			searchGame((textField_6.getText()).toLowerCase());
		}
		//Update all fields
		fillFields();
	}
	
	/**
	 * Resets all checkboxes
	 */
	void resetbx() {
		if (xmlReader.getItem().getDeleted()) {
			if (!chckbxDelete.isSelected())
				chckbxDelete.setSelected(true);
		} else if (!xmlReader.getItem().getDeleted()) {
			if (chckbxDelete.isSelected())
				chckbxDelete.setSelected(false);
		}
		
		if (xmlReader.getItem().getHidden()) {
			if (!chckbxHidden.isSelected())
				chckbxHidden.setSelected(true);
		} else if (!xmlReader.getItem().getHidden()) {
			if (chckbxHidden.isSelected())
				chckbxHidden.setSelected(false);
		}
		
		if (xmlReader.getItem().getPublisher().isBlank()) {
			if (!comboBox.getEditor().getItem().toString().isBlank()) {
				model.setSelectedItem("");
			}
		}
		if (xmlReader.getItem().getDeveloper().isBlank()) {
			if (!comboBox_1.getEditor().getItem().toString().isBlank()) {
				model1.setSelectedItem("");
			}
		}
		if (xmlReader.getItem().getPlayers() == "0") {
			if (!comboBox_1.getEditor().getItem().toString().isBlank()) {
				model2.setSelectedItem(0);
			}
			
		}
	}
	
	/**
	 * Updates the boxes and labels on the form with the required values after an action is performed
	 */
	void fillFields() {
		Item itm = new Item();
		itm = xmlReader.getItem();
		
		lblNewLabel.setText("Record # : " + (xmlReader.getIdx() + 1) + " of " + (xmlReader.getArray().size()));
		
		if (itm.getDeleted()) {
			lblDeleted.setText("DELETED");
			lblDeleted.setForeground(Color.red);
			lblDeleted.setOpaque(true);
			lblDeleted.setBackground(Color.green);
			lblDeleted.setVisible(true);
		} else {
			lblDeleted.setVisible(false);
		}
		String path = itm.getPath().replace("./", "");
		if (XMLReader.getRomExists().contains(path))
			textField.setBorder(BorderFactory.createLineBorder(Color.red));
		else
			textField.setBorder(new JTextField().getBorder());
			
		
		textField.setText(itm.getPath());
		textField_1.setText(itm.getName());
		textField_2.setText(itm.getImage());
		checkImagePath(itm.getImage());
		
		textField_3.setText(Double.toString(itm.getRating()));
		if (itm.getDate().contains("-"))
			itm.setDate(new DatePicker(null, itm.getDate()).formatDate());
		textField_4.setText(itm.getDate());
		textField_5.setText(itm.getDeveloper());
		
		model.setSelectedItem(itm.getPublisher());
		model1.setSelectedItem(itm.getGenre());
		model2.setSelectedItem(itm.getPlayers());
		
		textField_8.setText(Integer.toString(itm.getPlayCount()));
		if (itm.getDeleted())
			chckbxDelete.setSelected(true);
		if (itm.getHidden())
			chckbxHidden.setSelected(true);
		
		textArea.setText(itm.getDesc());
		
	}
	
	void searchGame(String name) {
		boolean found = false;
		//Gets current index of the item
		int tempIdx = xmlReader.getIdx();
		
		//Loops for remaining items in the list
		for (int i = xmlReader.getIdx() + 1; i < xmlReader.getArray().size() - 1; i++) {
			Item itm = xmlReader.getItem(i);
			
			boolean empStr = !name.isEmpty() && itm.getName().toLowerCase().contains(name);
			boolean imageEx = new File(itm.getImage()).exists();
			
			if (name.isBlank() || empStr) {
				if (chckbxDescription.isSelected() && chckbxImage.isSelected()) {
					if (itm.getDesc().isEmpty() && !imageEx) {
						found = true;
					}
				}
				if (chckbxDescription.isSelected() && !chckbxImage.isSelected()) {
					if (itm.getDesc().isEmpty()) {
						found = true;
					}
				}
				if (!chckbxDescription.isSelected() && chckbxImage.isSelected()) {
					if (!imageEx) {
						found = true;
					}
				}
			}
			
			if (empStr || found)  {
				xmlReader.setIdx(i);
				scrollBar.setValue(i);
				return;
			}
			
		}
		if (!found) {
			//Reset to  original index
			xmlReader.setIdx(tempIdx);
		}
	}
	
	/**
	 * Loads all publishers
	 * @param list
	 */
	void loadPublishers(ArrayList<Item> list) {
		
		for (Item itm : list) {
			if (!itm.getPublisher().isBlank() && model.getIndexOf(itm.getPublisher()) == -1) {
				model.addElement(itm.getPublisher());
			}
		}
		
	}
	
	/**
	 * Gets an image from the clipboard
	 * @return
	 */
	ImageIcon getImageFromClipboard() {
		
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable content = clip.getContents(null);
		try {
			BufferedImage img = (BufferedImage) content.getTransferData(DataFlavor.imageFlavor);
			ImageIcon imgIcon = new ImageIcon(img);
			
			System.out.println(XMLReader.romPath);
			
			xmlReader.getItem().setImage(XMLReader.romPath + "downloaded_images\\" + FilenameUtils.getBaseName(xmlReader.getItem().getPath()) + "-image.png");
			
			saveImage(imgIcon);
			
			return imgIcon;
		} catch (UnsupportedFlavorException | IOException e) {
			return null;
		}
		
	}
	
	void saveImage(ImageIcon imgIcon) {
		Image img = imgIcon.getImage();

		BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(img, 0, 0, null);
		g2.dispose();
		try {
			ImageIO.write(bi, "png", new File(xmlReader.getItem().getImage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void checkImagePath(String path) {
		
		String defaultPath = XMLReader.romPath + "downloaded_images";
		if (!path.equals("")) {
			if (path.contains(".png") || path.contains(".jpg")) 
				loadImage(resize(getImage(path)));
			else if (path.contains(defaultPath)) 
				loadImage(getImage(path));
			 
		} else 
			 if (lblNewLabel_4.getIcon() != null) 
				 lblNewLabel_4.setIcon(null);
		
	}
	
	ImageIcon getImage(String path) {
		ImageIcon image;
		if (path.contains("http"))
			try {
				image = convertImage(new URL(path));
				return image;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		image = new ImageIcon(path);
		
		return image;
	}
	
	
	ImageIcon convertImage(URL path) {
		try {
			BufferedImage bi = ImageIO.read(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			ImageIO.write(bi, "png", bos);
			byte[] data = bos.toByteArray();
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			
			BufferedImage bi2 = ImageIO.read(bis);
			ImageIcon image = new ImageIcon(bi2);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * WORKING
	 * 
	 * @param pImg
	 * @return
	 */
	ImageIcon resize(ImageIcon pImg) {
		
		if (pImg != null) {
			ImageIcon rImage = pImg;
			
			Image img = rImage.getImage();
			Image newImg;
			if (img.getWidth(null) < img.getHeight(null)) {
				newImg = img.getScaledInstance(300, 400, java.awt.Image.SCALE_SMOOTH);
			} else {
				newImg = img.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
			}
			
			rImage = new ImageIcon(newImg);
			return rImage;
		}
		return null;
	}
	
	/**
	 * 
	 * WORKING
	 * 
	 * @param image
	 */
	void loadImage(ImageIcon image) {
		if (image != null)
			System.out.println(xmlReader.getItem().getImage());
			if (xmlReader.getItem().getImage() != "") {
				lblNewLabel_4.setIcon(image);
			} else {
				if (lblNewLabel_4.getIcon() != null) {
					lblNewLabel_4.setIcon(null);
				}
			} 
	}
}
