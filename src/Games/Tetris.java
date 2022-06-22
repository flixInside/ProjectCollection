package Games;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Utility.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris implements Runnable, KeyListener {

    private static JFrame frame;
    private static JPanel panel;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JPanel[][] panels;
    private static int currentShape;
    private Thread th;

    private static final int SQUARE = 0;
    private static final int LSHAPE = 1;
    private static final int MIRROREDLSHAPE = 2;
    private static final int PYRA = 3;
    private static final int STICK = 4;
    private static final int STAIRr = 5;
    private static final int STAIRl = 6;

    public Tetris() {
        // Initialize objects
        frame = new JFrame("Tetris");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(300, 600));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);

        initialize();

        th = new Thread(this);
        th.start();
    }

    private static void initialize() {
        panels = new JPanel[20][10];
        currentShape = -1;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                JPanel p = new JPanel();
                panels[i][j] = p;
                p.setBackground(Color.WHITE);
                panel.add(p, gbc);
            }
        }

        for (JPanel[] ps : panels) {
            for (JPanel p : ps) {
                p.setBorder(new LineBorder(Color.BLACK, 2, true));
            }
        }

        place(6);

        frame.setVisible(true);
    }

    private static void place(int i) {
        Color color = new Color(Color.BLACK);
        switch (i) {
            case 0:
                panels[0][4].setBackground(color);
                panels[0][5].setBackground(color);
                panels[1][4].setBackground(color);
                panels[1][5].setBackground(color);
                currentShape = Tetris.SQUARE;
                break;
            case 1:
                panels[0][3].setBackground(color);
                panels[0][4].setBackground(color);
                panels[0][5].setBackground(color);
                panels[0][6].setBackground(color);
                currentShape = Tetris.STICK;
                break;
            case 2:
                panels[0][4].setBackground(color);
                panels[1][3].setBackground(color);
                panels[1][4].setBackground(color);
                panels[1][5].setBackground(color);
                currentShape = Tetris.PYRA;
                break;
            case 3:
                panels[0][3].setBackground(color);
                panels[1][3].setBackground(color);
                panels[1][4].setBackground(color);
                panels[1][5].setBackground(color);
                currentShape = Tetris.MIRROREDLSHAPE;
                break;
            case 4:
                panels[0][5].setBackground(color);
                panels[1][3].setBackground(color);
                panels[1][4].setBackground(color);
                panels[1][5].setBackground(color);
                currentShape = Tetris.LSHAPE;
                break;
            case 5:
                panels[0][4].setBackground(color);
                panels[0][5].setBackground(color);
                panels[1][3].setBackground(color);
                panels[1][4].setBackground(color);
                currentShape = Tetris.STAIRr;
                break;
            case 6:
                panels[0][3].setBackground(color);
                panels[0][4].setBackground(color);
                panels[1][4].setBackground(color);
                panels[1][5].setBackground(color);
                currentShape = Tetris.STAIRl;
                break;
        default:
            break;
        }
    }

    @Override
    public void run() {
        while(true){
            synchronized(th){
                try {
                    th.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 19; j++){
                    if(panels[j][i].getBackground() != Color.WHITE){
                        panels[j + 1][i].setBackground(panels[j][i].getBackground());
                        panels[j][i].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_RIGHT:

                break;
            case KeyEvent.VK_DOWN:

                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    public static void main(String[] args) throws Exception {
        new Tetris();
    }
}
