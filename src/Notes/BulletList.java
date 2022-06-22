package Notes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUIs.FileExplorer;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class BulletList implements KeyListener, DocumentListener{

    private static JFrame frame;
    private static JPanel panel;
    public static JTextArea textA;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JMenuBar menuB;
    private static JMenu fileM;
    private static JMenuItem saveI;
    private static int state = 0;

    public BulletList() {
        // Initialize objects
        frame = new JFrame("Bullet List");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        textA = new JTextArea("   - ");

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(800, 1000));
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);

        menuB = new JMenuBar();
        fileM = new JMenu("File");
        saveI = new JMenuItem("\uf0c7" + "Save as .txt file");
        frame.setJMenuBar(menuB);
        menuB.add(fileM);
        fileM.add(saveI);

        saveI.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                saveToFile(e);
            }
        });

        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);

        textA.addKeyListener(this);
        textA.getDocument().addDocumentListener(this);

        initialize();
    }

    private static void initialize() {
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(textA, gbc);

        frame.setVisible(true);
    }

    private static void saveToFile(ActionEvent e){
        new FileExplorer(FileExplorer.SAVE, textA.getText(), e);
    }

    private static boolean scannText(String input){
        char ch = Character.MIN_VALUE;
        for(Character c : input.toCharArray()){
            switch (c) {
                case '!':
                    if(state <= 1){
                        state ++;
                        ch = c;
                    }else if(state == 2){
                        state = 1;
                    }
                    break;
                default:
                    break;
            }
            if(state == 2){
                String temp = input.substring(ch, c);
            }
        }
        return state == 2;
    }
  
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_SPACE){
            //String text = textA.getText();
        }else if(e.getKeyChar() == KeyEvent.VK_ENTER){
            BulletList.textA.append("   - ");
            state = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("inserted");
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        
    }

    public static void main(String[] args) {
        new BulletList();
    }

}


