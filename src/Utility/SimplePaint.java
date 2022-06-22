package Utility;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class SimplePaint {

    private static JFrame frame;
    private static JPanel panel;
    private static Color currentColor; 
    private static JButton selectColorB;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    
    public SimplePaint(){
        // Initialize objects
        frame = new JFrame("Color maker");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(400, 650));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);

        initialize();
    }

    private static void initialize() {
        selectColorB = new JButton("\uf53f");

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel.add(selectColorB, gbc);

        selectColorB.addActionListener(e -> selectColor());

        frame.setVisible(true);
    }
    
    private static void selectColor() {
        Color c = new Color(true);
        while(!c.selected){}
        currentColor = c.getSelectedColor();
        selectColorB.setBackground(currentColor);
        System.out.println("check");
        
    }

    public static void main(String[] args) {
        new SimplePaint();
    }

}
