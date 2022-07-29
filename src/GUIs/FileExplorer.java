package GUIs;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FileExplorer implements ActionListener {

    private static int operation;

    public static final int SAVE = 0;
    public static final int OPEN = 1;

    private String text;

    /**
     * Constructor with the params to set the operation(save/open file) and the content to be saved in the file. 
     * @param operation
     * @param text
     * @param e
     */
    public FileExplorer(int operation, String text, ActionEvent e) {
        FileExplorer.operation = operation;
        this.text = text;

        actionPerformed(e);
    }

    /**
     * Acctually creates the JFileChooser GUI.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(".")); // sets current directory

        int response = 0;

        switch(operation){
            case 0:
                response = fileChooser.showSaveDialog(null); //select file to save
                break;
            case 1:
                response = fileChooser.showOpenDialog(null); // select file to open
                break;
            default:
                break;
        }

        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                try (FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt")) {
                    fw.write(text);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
