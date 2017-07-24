package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import java.util.*;

public class EditorGUI extends javax.swing.JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = -5259510942170843475L;
	
	private static final int DEFAULT_WIDTH = 850;
	
	private static final int DEFAULT_HEIGHT = 650;
	
	private int width;
	
	private int height;
	
	private JTextArea textarea;
	
	private JScrollPane vertical;
	
	private JMenu file;
	
	private JMenuItem menuItem;
	
	/**
	 * Creates a new Editor GUI with the specified title
	 * @param title The title of the GUI super class JFrame
	 */
	public EditorGUI(String title) {
		super(title);
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
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

	/**
	 * Handles the actions performed by the user such as button clicking
	 * @param e called when action has occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
		
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_A);
		file.getAccessibleContext().setAccessibleDescription("Save");
		bar.add(file);
		
		menuItem = new JMenuItem(new AbstractAction("New"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				textarea.setText(null);
			}
		});
		file.add(menuItem);
		
		menuItem = new JMenuItem(new AbstractAction("Save"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				final JFrame frame = new JFrame();
				int returnVal = fc.showSaveDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					FileWriter fw;
					try {
						fw = new FileWriter(fc.getSelectedFile());
						String text = textarea.getText();
						fw.write(text);
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
				
			}
		});
		file.add(menuItem);
		
		menuItem = new JMenuItem(new AbstractAction("Load"){
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				final JFrame frame = new JFrame();
				int returnVal = fc.showOpenDialog(frame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					try{ 			
						FileReader reader = new FileReader(file.getAbsolutePath());
						BufferedReader br = new BufferedReader(reader);		
						String line = "";
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
		
		return bar;
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
		textarea.setLineWrap( true );
		textarea.setWrapStyleWord( true );
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
