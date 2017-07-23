package editorWizard;

import javax.swing.SwingUtilities;

import gui.EditorGUI;

public class EditorWizard {

	public EditorWizard() {
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new EditorGUI("Text Editor"));
	}
}
