package Notes;

//import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import GUIs.FileExplorer;
import Utility.Color;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class TODOList implements KeyListener {

    private static JFrame frame;
    private static JPanel panel;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JMenuBar menuB;
    private static JMenu fileM;
    private static JMenuItem saveI;
    private static HashMap<JCheckBox, JTextField> hashMap;
    private static int gridy;
    private static JCheckBox temp;

    public TODOList() {
        // Initialize objects
        frame = new JFrame("TODO List");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        hashMap = new HashMap<>();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(800, 1000));
        frame.pack();
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

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

        initialize();

        hashMap.get(temp).addKeyListener(this);
    }

    private static void initialize() {
        gridy = 0;

        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JCheckBox jc = new JCheckBox();
        jc.addActionListener(a -> checked(jc));
        hashMap.put(jc, new JTextField());
        panel.add(jc, gbc);
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(hashMap.get(jc), gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = gridy + 1;
        gbc.weighty = 1;
        panel.add(new JPanel(), gbc);
        gridy++;

        temp = jc;

        System.out.println(hashMap.get(jc).getFont());

        frame.setVisible(true);
    }

    private static void saveToFile(ActionEvent e) {
        new FileExplorer(FileExplorer.SAVE, TODOListToString(), e);
    }

    private static String TODOListToString() {
        String output = "";
        for (JCheckBox jc : hashMap.keySet()) {
            if (jc.isSelected()) {
                output += "x ";
            } else {
                output += "  ";
            }
            output += hashMap.get(jc).getText() + "\n";
        }
        return output;
    }

    private static void checked(JCheckBox jc) {
        if (jc.isSelected()) {
            hashMap.get(jc).setForeground(Color.lightGray);
        } else {
            hashMap.get(jc).setForeground(Color.black);
        }
    }

    private static boolean edittext(String text, JTextField jTF) {
        boolean edited = false;
        System.out.println("called");
        if (text.length() >= 1) {
            System.out.println(text.charAt(0) + "4");
            System.out.println(text + "4");
            switch (text.charAt(0)) {
                case '#':
                    if (text.charAt(1) == '#') {
                        if (text.charAt(2) == '#') {
                            jTF.setFont(new Font(jTF.getFont().getName(), jTF.getFont().getStyle(), 18));
                        } else {
                            jTF.setFont(new Font(jTF.getFont().getName(), jTF.getFont().getStyle(), 24));
                        }
                    } else {
                        jTF.setFont(new Font(jTF.getFont().getName(), jTF.getFont().getStyle(), 30));
                    }
                    jTF.setText(text.replace('#', Character.MIN_VALUE));
                    edited = true;
                    break;
                case '!':
                    System.out.println("!");
                    jTF.setFont(new Font(jTF.getFont().getName(), Font.BOLD, jTF.getFont().getSize()));
                    jTF.setText(text.replace('!', Character.MIN_VALUE));
                    edited = true;
                    break;
                case '?':
                    jTF.setFont(new Font(jTF.getFont().getName(), Font.ITALIC, jTF.getFont().getSize()));
                    jTF.setText(text.replace('?', Character.MIN_VALUE));
                    edited = true;
                    break;
                case '>':
                    JPopupMenu jpm = new JPopupMenu();
                    JMenuItem i = new JMenuItem("Select other Color");
                    i.addActionListener(e -> new Color(true));
                    jpm.add(i);
                    jTF.add(jpm);
                default:
                    System.out.println("default");
                    break;
            }
        }
        frame.setVisible(true);
        return edited;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ENTER:
                for (Component c : panel.getComponents()) {
                    if (c instanceof JPanel) {
                        panel.remove(c);
                    }
                }
                gbc.weightx = 0;
                gbc.weighty = 0;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = gridy;
                JCheckBox jc = new JCheckBox();
                jc.addActionListener(a -> checked(jc));
                hashMap.put(jc, new JTextField());
                panel.add(jc, gbc);
                gbc.weightx = 1;
                gbc.gridx = 1;
                gbc.gridy = gridy;
                panel.add(hashMap.get(jc), gbc);
                gbc.gridwidth = 2;
                gbc.gridx = 0;
                gbc.gridy = gridy + 1;
                gbc.weighty = 1;
                panel.add(new JPanel(), gbc);
                gridy++;

                hashMap.get(jc).addKeyListener(this);
                frame.setVisible(true);
                break;
            case KeyEvent.VK_SPACE:
                if (e.getComponent() instanceof JTextField) {
                    JTextField temp = (JTextField) e.getComponent();
                    if(edittext(temp.getText(), temp)){
                        temp.setText(temp.getText().replace(' ', Character.MIN_VALUE));
                    } 
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static void main(String[] args) {
        new TODOList();
    }

}
