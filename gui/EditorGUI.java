package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EditorGUI extends javax.swing.JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = -5259510942170843475L;
	
	private static final int DEFAULT_WIDTH = 850;
	
	private static final int DEFAULT_HEIGHT = 650;
	
	private int width;
	
	private int height;
	
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
		
		this.setVisible(true);
	}
}
