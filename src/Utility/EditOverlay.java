package Utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class EditOverlay extends JPanel implements MouseMotionListener{

    private static JLabel parentLabel;

    public EditOverlay(JLabel label){
        super();
        parentLabel = label; 
        this.setBackground(new Color(255, 255, 255, 0));
        this.setBorder(new LineBorder(new Color(0, 126, 218), 2));
        this.setSize(label.getWidth() + 10, label.getHeight() + 10);
        this.setVisible(true);
        System.out.println("show");
    }

    public static void showOverlay(EditOverlay panel){
        panel.setBorder(new LineBorder(new Color(0, 126, 218), 2));
    }

    public static JLabel getParentLabel(){
        return parentLabel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

}
