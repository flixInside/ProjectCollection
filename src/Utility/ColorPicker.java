package Utility;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPicker implements Runnable, KeyListener {

    private static JFrame frame;
    private static JPanel panel, colorP;
    private static JLabel infoL, rgbL, hexL;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static boolean stopped;

    public ColorPicker() {
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

        frame.addKeyListener(this);

        initialize();

        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Sets up the GUI
     */
    private static void initialize() {
        colorP = new JPanel();
        rgbL = new JLabel("r: 0, g: 0, b: 0");
        hexL = new JLabel("#000000");
        infoL = new JLabel("Press 'alt' + 'x' to toogle the color picker.");

        colorP.setBackground(Color.black);

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(colorP, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(rgbL, gbc);
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(hexL, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(infoL, gbc);

        frame.setVisible(true);
    }

    /**
     * Thread method. Detects the color of the pixel below the mouse cursor using a
     * robot (java class).
     * Fills in the JLabel with rgb/hex code.
     */
    @Override
    public void run() {
        while (true) {
            System.out.println(); // is required !don't remove!
            if (!stopped) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                Color color = new Color(0, 0, 0);
                try {
                    Robot robot = new Robot();
                    java.awt.Color c = robot.getPixelColor((int) p.getX(), (int) p.getY());
                    color = new Color(c);
                } catch (AWTException e) {

                }
                colorP.setBackground(color);
                rgbL.setText("r: " + color.getRed() + ", g: " + color.getGreen() + ", b: " + color.getBlue());
                hexL.setText("#" + Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen())
                        + Integer.toHexString(color.getBlue()));
            }
        }

    }

    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        new ColorPicker();
    }

    /**
     * stopps the color detection
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_X) {
            if (e.isAltDown()) {
                stopped = !stopped;
            }
        }
    }

    /**
     * unused methods
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * unused methods
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

}
