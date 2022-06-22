package Notes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Point;
import java.util.HashMap;
import java.awt.event.MouseAdapter;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Home.HomeScreen;
import Utility.EditOverlay;

public class MindMap extends MouseAdapter implements Runnable{

    private static JFrame frame;
    private static JPanel panel, mapP;
    private static JButton cloudB, squareB, circleB, arrowB, dottedArrowB, lineB;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JMenuBar menuB;
    private static JMenu fileM;
    private static JMenuItem saveI;
    private static HashMap<EditOverlay, JLabel> overlayMap;
    private static Point startPoint;

    public MindMap(boolean normalStart) throws FontFormatException, IOException {
        if(!normalStart){
            return;
        }
        // Initialize objects
        frame = new JFrame("Mind Map");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        
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
            public void actionPerformed(ActionEvent e) {
                saveToFile(e);
            }
        });

        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);

        frame.setVisible(true);

        initialize();

        Thread thread = new Thread(this);
        thread.start();
    }

    private static void initialize() throws FontFormatException, IOException {
        // create input stream for FontAwesome icons
        InputStream is = HomeScreen.class.getResourceAsStream("/FontAwesome/fontawesome-webfont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        font = font.deriveFont(Font.PLAIN, 24f);

        cloudB = new JButton("\uf0c2");
        circleB = new JButton("\uf111");
        squareB = new JButton("\uf0c8");
        arrowB = new JButton("\uf178");
        dottedArrowB = new JButton();
        lineB = new JButton("\uf068");
        mapP = new JPanel();
        overlayMap = new HashMap<>();

        cloudB.addActionListener(e -> cloudB());
        circleB.addActionListener(e -> circleB());
        squareB.addActionListener(e -> squareB());
        arrowB.addActionListener(e -> arrowB());
        dottedArrowB.addActionListener(e -> dottedArrowB());
        lineB.addActionListener(e -> lineB());

        mapP.setBackground(Color.lightGray);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(cloudB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(circleB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(squareB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(arrowB, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        panel.add(dottedArrowB, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        panel.add(lineB, gbc);
        gbc.weightx = 0.5;
        gbc.gridx = 7;
        gbc.gridy = 0;
        panel.add(new JPanel(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JPanel(), gbc);
        gbc.gridwidth = 8;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(mapP, gbc);

        frame.setVisible(true);
    }

    private static void saveToFile(ActionEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e){
        startPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        startPoint = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getComponent() instanceof JLabel){
            JLabel label = (JLabel) e.getComponent();
            Point location = e.getPoint();
            Point newLocation = label.getLocation();
            newLocation.translate((int) (location.getX() - startPoint.getX()), (int) (location.getY() - startPoint.getY()));
            label.setLocation(newLocation);
            startPoint = location;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            if(e.getComponent() instanceof JLabel){
                JLabel label = (JLabel) e.getComponent();
                EditOverlay eo = new EditOverlay(label);
                mapP.add(new EditOverlay(label));
                overlayMap.put(eo, label);
            }
        }
    }

    private static void lineB() {

    }

    private static void dottedArrowB() {

    }

    private static void arrowB() {

    }

    private static void squareB() {

    }

    private static void circleB() {

    }

    private static void cloudB() {
        try {
            ImageIcon image = new ImageIcon(MindMap.class.getResource("img/cloud.png"));
            JLabel label = new JLabel(image);
            //EditOverlay ed = new EditOverlay();
            //ed.add(label);
            mapP.add(label);
            MindMap m = new MindMap(false);
            label.addMouseMotionListener(m);
            label.addMouseListener(m);
        } catch (Exception e) {
            System.out.println(e);
        }
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FontFormatException, IOException {
        new MindMap(true);
    }

    @Override
    public void run() {
        while (true) {
            for (EditOverlay e : overlayMap.keySet()) {
                e.setLocation(overlayMap.get(e).getLocation());
            }
        }        
    }

}
