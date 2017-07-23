package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorGUI extends javax.swing.JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = -5259510942170843475L;
	
	private static final int DEFAULT_WIDTH = 850;
	
	private static final int DEFAULT_HEIGHT = 650;
	
	private int width;
	
	private int height;
	
	private JTextArea textarea;
	
	private JScrollPane vertical;
	
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
		this.getContentPane().add(addContent());
		
		this.setVisible(true);
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
		vertical.setPreferredSize(new Dimension(this.getWidth()-10, this.getHeight()-10));
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(vertical);
		
		return panel;
	}
}
