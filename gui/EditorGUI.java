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
	
	public EditorGUI(String title) {
		// TODO Auto-generated constructor stub
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		createGUI();
	}
	
	public void createGUI(){
		setSize(this.getWidth(), this.getHeight());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		this.setVisible(true);
		
	}

}
