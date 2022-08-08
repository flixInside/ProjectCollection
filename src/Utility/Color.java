package Utility;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class Color extends java.awt.Color implements Runnable {

    private static final long serialVersionUID = 1L;

    private static JFrame frame;
    private static JPanel panel, colorP;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JSlider redS, greenS, blueS;
    private static JLabel redL, greenL, blueL, redValueL, greenValueL, blueValueL;
    private static JButton copyB, selectB;
    private static Color selectedColor;
    public static boolean selected;

    public static final java.awt.Color white = new java.awt.Color(255, 255, 255);
    public static final java.awt.Color black = new java.awt.Color(0, 0, 0);
    public static final java.awt.Color red = new java.awt.Color(155, 0, 0);
    public static final java.awt.Color darkgreen = new java.awt.Color(0, 40, 0);
    public static final java.awt.Color green = new java.awt.Color(0, 100, 0);
    public static final java.awt.Color lime = new java.awt.Color(0, 150, 0);
    public static final java.awt.Color purple = new java.awt.Color(60, 0, 60);
    public static final java.awt.Color darkblue = new java.awt.Color(0, 0, 60);
    public static final java.awt.Color blue = new java.awt.Color(0, 0, 100);
    public static final java.awt.Color cyan = new java.awt.Color(0, 126, 255);
    public static final java.awt.Color yellow = new java.awt.Color(255, 255, 0);
    public static final java.awt.Color orange = new java.awt.Color(255, 125, 0);
    public static final java.awt.Color pink = new java.awt.Color(255, 0, 255);

    /**
     * Constructor to create a new color using the java color class.
     * @param color
     */
    public Color(java.awt.Color color) {
        super(color.getRGB());
    }

    /**
     * Constructor to create a new color using an rgb code.
     * @param r
     * @param g
     * @param b
     */
    public Color(int r, int g, int b) {
        super(r, g, b);
    }

    /**
     * Constructor to create a new color using an rgb code with an additional alpha channel.
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public Color(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public Color(boolean select) {
        super(0);

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

        initialize(select);

        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Sets uo the GUI. Param to display either the select(true) or the copy(false) button.
     * @param select
     */
    public static void initialize(boolean select) {
        colorP = new JPanel();
        redS = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        greenS = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        blueS = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        redL = new JLabel("Red: ");
        greenL = new JLabel("Green: ");
        blueL = new JLabel("Blue: ");
        redValueL = new JLabel(String.valueOf(redS.getValue()));
        greenValueL = new JLabel(String.valueOf(greenS.getValue()));
        blueValueL = new JLabel(String.valueOf(blueS.getValue()));
        copyB = new JButton("copy to clipboard");
        selectB = new JButton("Select Color");

        selected = false;

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        panel.add(colorP, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(redL, gbc);
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(redS, gbc);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(redValueL, gbc);
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(greenL, gbc);
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(greenS, gbc);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(greenValueL, gbc);
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(blueL, gbc);
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(blueS, gbc);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(blueValueL, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        if (select) {
            panel.add(selectB, gbc);
        } else {
            panel.add(copyB, gbc);
        }

        copyB.addActionListener(e -> copyToClipboard());
        selectB.addActionListener(e -> selectColor());

        frame.setVisible(true);
    }

    /**
     * Saves the current color for further use.
     */
    private static void selectColor() {
        selectedColor = new Color(redS.getValue(), greenS.getValue(), blueS.getValue());
        selected = true;
    }

    /**
     * Copys the rgb code of the current color to the clipboard
     */
    private static void copyToClipboard() {
        StringSelection color = new StringSelection(
                String.valueOf(redS.getValue()) + ", " + String.valueOf(greenS.getValue()) + ", "
                        + String.valueOf(blueS.getValue()));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(color, null);
    }

    /**
     * returns the color that was selected using the select button
     * @return
     */
    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * Thread method. Fills in the JLabel with the rgb code and colors the panel.
     */
    @Override
    public void run() {
        while (true) {
            colorP.setBackground(new java.awt.Color(redS.getValue(), greenS.getValue(), blueS.getValue()));
            redValueL.setText(String.valueOf(redS.getValue()));
            greenValueL.setText(String.valueOf(greenS.getValue()));
            blueValueL.setText(String.valueOf(blueS.getValue()));
        }
    }

    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Color(false);
    }

}
