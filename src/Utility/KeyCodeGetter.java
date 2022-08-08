package Utility;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.JFrame;



public class KeyCodeGetter implements KeyListener{

    public KeyCodeGetter(){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 500));
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar() + ": " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public static void main(String[] args) {
        new KeyCodeGetter();
    }
    
}
