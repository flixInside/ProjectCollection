package GUIs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ErrorNote {
    
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel errorL;
    private static JButton button;
    private static GridBagLayout gbl;
    public static GridBagConstraints gbc;

    public ErrorNote(String errorNote){
        frame = new JFrame("Error");
        panel = new JPanel();
        errorL = new JLabel(errorNote);
        button = new JButton("Back");
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        frame.add(panel);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);
        errorL.setHorizontalAlignment(JLabel.CENTER);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(errorL, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button, gbc);

        button.addActionListener(e -> frame.setVisible(false));

        frame.setVisible(true);
    }

    public static void main(String[] args){
        new ErrorNote("Test");
    }

}
