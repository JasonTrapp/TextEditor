package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
<<<<<<< HEAD
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import java.awt.color.*;
=======
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
>>>>>>> dev


public class EditorGUI extends javax.swing.JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = -5259510942170843475L;
	
	private static final int DEFAULT_WIDTH = 850;
	
	private static final int DEFAULT_HEIGHT = 650;
	
	private static final int SEARCH_HEIGHT = 150;

	private static final int SEARCH_WIDTH = 400;
	
	private static final int BUTTON_WIDTH = JButton.WIDTH;
	
	private static final int BUTTON_HEIGHT = JButton.HEIGHT;

	private int width;
	
	private int height;
	
	private JTextArea textarea;
	
	private JScrollPane vertical;
		
	private JMenuItem menuItem;
	
	private String path;
	
	private JButton find;

	private JTextField searchItem;
	
	private JFileChooser fc;

	/**
	 * Creates a new Editor GUI with the specified title
	 * @param title The title of the GUI super class JFrame
	 */
	public EditorGUI(String title) {
		super(title);
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
		path = null;
	}
	
	/**
	 * Gets the current height of the GUI
	 * @return The height of the GUI
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Sets the new height of the JFrame.
	 * @param height The height in which to make the JFrame
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * Gets the current width of the GUI
	 * @return The width of the GUI
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Sets the new width of the JFrame.
	 * @param height The width in which to make the JFrame
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	private void changeTitle(String filePath){
		this.setTitle("Jason\'s Text Editor - " + filePath);
	}

	/**
	 * Handles the actions performed by the user such as button clicking
	 * @param e called when action has occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		createGUI();
	}
	
	/**
	 * Creates the GUI, sets default variables and components for GUI and interaction.
	 */
	public void createGUI(){
		setSize(this.getWidth(), this.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(addMenu());
		this.getContentPane().add(addContent());
		
		this.setVisible(true);
	}
	
	/**
	 * Adds the menu bar which contains features such as saving and loading.
	 * @return The menubar with all submenus
	 */
	public JMenuBar addMenu(){
		JMenuBar bar = new JMenuBar();
		bar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));		
		JMenu file = new JMenu("File");
		menuItem = new JMenuItem(new AbstractAction("New"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				textarea.setText(null);
				path = null;
				changeTitle("");
			}
		});
		file.add(menuItem);
		
		menuItem = new JMenuItem(new AbstractAction("Save"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(path.equals(null)){
					final JFrame frame = new JFrame();
					int returnVal = fc.showSaveDialog(frame);
					
					if(returnVal == JFileChooser.APPROVE_OPTION){
						FileWriter fw;
						try {
							fw = new FileWriter(fc.getSelectedFile());
							String text = textarea.getText();
							fw.write(text);
							File file = fc.getSelectedFile();
							changeTitle(file.getPath());
							fw.close();
						} catch (IOException e1) {
							System.out.println("Cannot write file.");
						}
					}
				}
				else{
					FileWriter fw;
					try {
						fw = new FileWriter(fc.getSelectedFile());
						String text = textarea.getText();
						fw.write(text);
						File file = fc.getSelectedFile();
						changeTitle(file.getPath());
						fw.close();
					} catch (IOException e1) {
						System.out.println("Cannot write file.");
					}
				}
			}
		});
		file.add(menuItem);
		
		menuItem = new JMenuItem(new AbstractAction("Save as"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				final JFrame frame = new JFrame();
				int returnVal = fc.showSaveDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					FileWriter fw;
					try {
						fw = new FileWriter(fc.getSelectedFile());
						String text = textarea.getText();
						fw.write(text);
						File file = fc.getSelectedFile();
						changeTitle(file.getPath());
						fw.close();
					} catch (IOException e1) {
						System.out.println("Cannot write file.");
					}
				}
			}
		});
		file.add(menuItem);
		
		menuItem = new JMenuItem(new AbstractAction("Load"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				final JFrame frame = new JFrame();
				int returnVal = fc.showOpenDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					try{ 			
						FileReader reader = new FileReader(file.getAbsolutePath());
						BufferedReader br = new BufferedReader(reader);		
						String line = "";
						path = file.getPath();
						changeTitle(path);
						while(br.ready()){
							line = line.concat(br.readLine());	
							line = line.concat("\n");
						}
						
						textarea.setText(line);
						br.close();
						reader.close();
					}catch(FileNotFoundException ex){
						System.out.println("File not found");
					} catch (IOException ex) {
						System.out.println("There was an error loading the file.");
					}
				}
			}
		});
		
		file.add(menuItem);
		file.addSeparator();
		
		menuItem = new JMenuItem(new AbstractAction("Close"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		file.add(menuItem);
		bar.add(file);
		file = new JMenu("Utilities");
		menuItem = new JMenuItem(new AbstractAction("Search"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame search = new JFrame("Search");
				search.setSize(SEARCH_WIDTH, SEARCH_HEIGHT);
				search.setLayout(new BoxLayout(search.getContentPane(), BoxLayout.Y_AXIS));

				search.getContentPane().add(addSearchBar());
				search.getContentPane().add(addFindButton());
				search.setVisible(true);
<<<<<<< HEAD
				find.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(searchItem.getText().compareTo("") != 0){
							Highlighter highlighter = textarea.getHighlighter();
							HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
							
							String item = searchItem.getText();
							
							int lastIndex = 0;
							int count = 0;
							while(lastIndex != -1){
								lastIndex = textarea.getText().indexOf(item, lastIndex);
								if(lastIndex != -1){
									count++;
									lastIndex += item.length();
								}
							}
							System.out.println("There were " + count + " matches.");
							
=======
				Highlighter highlighter = textarea.getHighlighter();

				search.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent windowEvent){
						highlighter.removeAllHighlights();
					}
				});
				
				find.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
						
						if(searchItem.getText().compareTo("") != 0){
							
							String item = searchItem.getText();
							int lastIndex = 0;
							int count = 0;
							
							while(lastIndex != -1){
								lastIndex = textarea.getText().indexOf(item, lastIndex);
								int p1 = lastIndex + item.length();
								
								if(lastIndex != -1){
									try {
										highlighter.addHighlight(lastIndex, p1, painter);
									} catch (BadLocationException e) {
										System.out.println("failed");
									}
									count++;
									if((lastIndex + item.length()) > textarea.getText().length())
										break;
									lastIndex += item.length();
								}
							}							
							search.getContentPane().add(results(count));
							search.getContentPane().validate();
						}
						else{
							highlighter.removeAllHighlights();
>>>>>>> dev
						}
					}
				});
			}
		});
		file.add(menuItem);
		bar.add(file);
		return bar;
	}
	
	private JPanel addFindButton(){
		JPanel pnl = new JPanel();
		setSize(this.getWidth(), this.getHeight());

		pnl.add(findButton());
		return pnl;
	}
	
	private JPanel addSearchBar(){
		JPanel pnl = new JPanel();
		setSize(this.getWidth(), this.getHeight());
		pnl.add(searchBar());
		return pnl;
	}
	
	private JTextField searchBar(){
		searchItem = new JTextField(20);
		
		return searchItem;
	}
	
	private JButton findButton(){
		find = new JButton("Search");
		Dimension dim = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
		find.setMaximumSize(dim);
		find.setMinimumSize(dim);
		find.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		return find;
	}
	
	private JLabel results(int count){
		JLabel holder = new JLabel();
		
		holder.setText("There were " + count + " matches");
		
		return holder;
	}
	
	/**
	 * Adds textarea and scrollpane when needed.
	 * @return A JPanel that contains the needed content of that panel
	 */
	private JPanel addContent(){
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Dimension dim = new Dimension(this.getWidth(), this.getHeight());
		panel.setPreferredSize(dim);
		
		textarea = new JTextArea();
		textarea.setSize(dim);
		textarea.setEditable(true);
		panel.add(textarea);
		
		vertical = new JScrollPane(textarea);
		vertical.setPreferredSize(new Dimension(this.getWidth()-10, this.getHeight()-30));
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(vertical);
		
		return panel;
	}
}
