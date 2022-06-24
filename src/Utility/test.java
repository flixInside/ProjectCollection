package Utility;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Home.HomeScreen;

public class test {
    
    public test() throws FontFormatException, IOException{
        // create input stream for FontAwesome icons
        InputStream is = test.class.getResourceAsStream("../FontAwesome/fontawesome-webfont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        font = font.deriveFont(Font.PLAIN, 24f);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("\u2654");
        //label.setFont(font);
        frame.add(panel);
        panel.add(label);
        frame.setMinimumSize(new Dimension(100, 150));
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FontFormatException, IOException {
        new test();
    }

}
