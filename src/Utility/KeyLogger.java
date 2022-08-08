package Utility;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class KeyLogger implements KeyListener, Runnable {

    private static JFrame frame;
    private static JPanel panel;
    private static JTextArea textA;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;

    private static long time;
    private static int called;

    private static ArrayList<Key> keys;

    public KeyLogger() {
        // Initialize objects
        frame = new JFrame("Key Logger");
        panel = new JPanel();
        textA = new JTextArea();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(750, 350));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(1, 1, 1, 1);

        frame.addKeyListener(this);
        textA.addKeyListener(this);

        Thread thread = new Thread(this);
        thread.start();

        initialize();
    }

    private static void initialize() {
        keys = new ArrayList<>();

        // Number keys
        Key zeroKey = new Key(KeyEvent.VK_0, "0");
        Key oneKey = new Key(KeyEvent.VK_1, "1");
        Key twoKey = new Key(KeyEvent.VK_2, "2");
        Key threeKey = new Key(KeyEvent.VK_3, "3");
        Key fourKey = new Key(KeyEvent.VK_4, "4");
        Key fiveKey = new Key(KeyEvent.VK_5, "5");
        Key sixKey = new Key(KeyEvent.VK_6, "6");
        Key sevenKey = new Key(KeyEvent.VK_7, "7");
        Key eightKey = new Key(KeyEvent.VK_8, "8");
        Key nineKey = new Key(KeyEvent.VK_9, "9");

        // Letter keys
        Key aKey = new Key(KeyEvent.VK_A, "a");
        Key bKey = new Key(KeyEvent.VK_B, "b");
        Key cKey = new Key(KeyEvent.VK_C, "c");
        Key dKey = new Key(KeyEvent.VK_D, "d");
        Key eKey = new Key(KeyEvent.VK_E, "e");
        Key fKey = new Key(KeyEvent.VK_F, "f");
        Key gKey = new Key(KeyEvent.VK_G, "g");
        Key hKey = new Key(KeyEvent.VK_H, "h");
        Key iKey = new Key(KeyEvent.VK_I, "i");
        Key jKey = new Key(KeyEvent.VK_J, "j");
        Key kKey = new Key(KeyEvent.VK_K, "k");
        Key lKey = new Key(KeyEvent.VK_L, "l");
        Key mKey = new Key(KeyEvent.VK_M, "m");
        Key nKey = new Key(KeyEvent.VK_N, "n");
        Key oKey = new Key(KeyEvent.VK_O, "o");
        Key pKey = new Key(KeyEvent.VK_P, "p");
        Key qKey = new Key(KeyEvent.VK_Q, "q");
        Key rKey = new Key(KeyEvent.VK_R, "r");
        Key sKey = new Key(KeyEvent.VK_S, "s");
        Key tKey = new Key(KeyEvent.VK_T, "t");
        Key uKey = new Key(KeyEvent.VK_U, "u");
        Key vKey = new Key(KeyEvent.VK_V, "v");
        Key wKey = new Key(KeyEvent.VK_W, "w");
        Key xKey = new Key(KeyEvent.VK_X, "x");
        Key yKey = new Key(KeyEvent.VK_Y, "y");
        Key zKey = new Key(KeyEvent.VK_Z, "z");
        Key öKey = new Key("ö");
        Key üKey = new Key("ü");
        Key äKey = new Key("ä");
        Key ßKey = new Key(KeyEvent.VK_BACK_SLASH, "ß");

        // Control keys
        Key tabKey = new Key(KeyEvent.VK_TAB, "TAB");
        Key capsLockKey = new Key(KeyEvent.VK_CAPS_LOCK, "CAPS");
        Key lShiftKey = new Key(KeyEvent.VK_SHIFT, KeyEvent.KEY_LOCATION_LEFT, "SHIFT");
        Key lControlKey = new Key(KeyEvent.VK_CONTROL, KeyEvent.KEY_LOCATION_LEFT, "CTRL");
        Key windowsKey = new Key(KeyEvent.VK_WINDOWS, "WIN");
        Key altKey = new Key(KeyEvent.VK_ALT, "ALT");
        Key spaceKey = new Key(KeyEvent.VK_SPACE, "SPACE", " ");
        Key altGraphKey = new Key(KeyEvent.VK_ALT_GRAPH, "ALT GR");
        Key enterKey = new Key(KeyEvent.VK_ENTER, "ENTER", "\n");
        Key deleteKey = new Key(KeyEvent.VK_DELETE, "DELETE");
        Key rShiftKey = new Key(KeyEvent.VK_SHIFT, KeyEvent.KEY_LOCATION_RIGHT, "SHIFT");
        Key rControlKey = new Key(KeyEvent.VK_CONTROL, KeyEvent.KEY_LOCATION_RIGHT, "CTRL");

        // Other keys
        Key dotKey = new Key(KeyEvent.VK_PERIOD, ".");
        Key commaKey = new Key(KeyEvent.VK_COMMA, ",");
        Key minusKey = new Key(KeyEvent.VK_MINUS, "-");
        Key nummberSignKey = new Key(KeyEvent.VK_NUMBER_SIGN, "#");
        Key plusKey = new Key(KeyEvent.VK_PLUS, "+");
        Key CircumflexKey = new Key(KeyEvent.VK_CIRCUMFLEX, "^");
        Key lessKey = new Key(KeyEvent.VK_LESS, "<");
        Key acuteKey = new Key(KeyEvent.VK_DEAD_ACUTE, "´");

        // first row
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(CircumflexKey, gbc);
        gbc.gridx = 1;
        panel.add(oneKey, gbc);
        gbc.gridx = 2;
        panel.add(twoKey, gbc);
        gbc.gridx = 3;
        panel.add(threeKey, gbc);
        gbc.gridx = 4;
        panel.add(fourKey, gbc);
        gbc.gridx = 5;
        panel.add(fiveKey, gbc);
        gbc.gridx = 6;
        panel.add(sixKey, gbc);
        gbc.gridx = 7;
        panel.add(sevenKey, gbc);
        gbc.gridx = 8;
        panel.add(eightKey, gbc);
        gbc.gridx = 9;
        panel.add(nineKey, gbc);
        gbc.gridx = 10;
        panel.add(zeroKey, gbc);
        gbc.gridx = 11;
        panel.add(ßKey, gbc);
        gbc.gridx = 12;
        panel.add(acuteKey, gbc);
        gbc.gridx = 13;
        gbc.gridwidth = 2;
        panel.add(deleteKey, gbc);

        // second row
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tabKey, gbc);
        gbc.gridx = 1;
        panel.add(qKey, gbc);
        gbc.gridx = 2;
        panel.add(wKey, gbc);
        gbc.gridx = 3;
        panel.add(eKey, gbc);
        gbc.gridx = 4;
        panel.add(rKey, gbc);
        gbc.gridx = 5;
        panel.add(tKey, gbc);
        gbc.gridx = 6;
        panel.add(zKey, gbc);
        gbc.gridx = 7;
        panel.add(uKey, gbc);
        gbc.gridx = 8;
        panel.add(iKey, gbc);
        gbc.gridx = 9;
        panel.add(oKey, gbc);
        gbc.gridx = 10;
        panel.add(pKey, gbc);
        gbc.gridx = 11;
        panel.add(üKey, gbc);
        gbc.gridx = 12;
        panel.add(plusKey, gbc);
        gbc.gridx = 13;
        gbc.gridwidth = 2;
        panel.add(enterKey, gbc);

        // third row
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(capsLockKey, gbc);
        gbc.gridx = 1;
        panel.add(aKey, gbc);
        gbc.gridx = 2;
        panel.add(sKey, gbc);
        gbc.gridx = 3;
        panel.add(dKey, gbc);
        gbc.gridx = 4;
        panel.add(fKey, gbc);
        gbc.gridx = 5;
        panel.add(gKey, gbc);
        gbc.gridx = 6;
        panel.add(hKey, gbc);
        gbc.gridx = 7;
        panel.add(jKey, gbc);
        gbc.gridx = 8;
        panel.add(kKey, gbc);
        gbc.gridx = 9;
        panel.add(lKey, gbc);
        gbc.gridx = 10;
        panel.add(öKey, gbc);
        gbc.gridx = 11;
        panel.add(äKey, gbc);
        gbc.gridx = 12;
        panel.add(nummberSignKey, gbc);

        // fourth row
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lShiftKey, gbc);
        gbc.gridx = 1;
        panel.add(lessKey, gbc);
        gbc.gridx = 2;
        panel.add(yKey, gbc);
        gbc.gridx = 3;
        panel.add(xKey, gbc);
        gbc.gridx = 4;
        panel.add(cKey, gbc);
        gbc.gridx = 5;
        panel.add(vKey, gbc);
        gbc.gridx = 6;
        panel.add(bKey, gbc);
        gbc.gridx = 7;
        panel.add(nKey, gbc);
        gbc.gridx = 8;
        panel.add(mKey, gbc);
        gbc.gridx = 9;
        panel.add(commaKey, gbc);
        gbc.gridx = 10;
        panel.add(dotKey, gbc);
        gbc.gridx = 11;
        panel.add(minusKey, gbc);
        gbc.gridx = 12;
        gbc.gridwidth = 3;
        panel.add(rShiftKey, gbc);

        // second row
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lControlKey, gbc);
        gbc.gridx = 1;
        panel.add(windowsKey, gbc);
        gbc.gridx = 2;
        panel.add(altKey, gbc);
        gbc.gridx = 3;
        gbc.gridwidth = 10;
        panel.add(spaceKey, gbc);
        gbc.gridx = 13;
        gbc.gridwidth = 1;
        panel.add(altGraphKey, gbc);
        gbc.gridx = 14;
        panel.add(rControlKey, gbc);

        // text area
        gbc.gridheight = 5;
        gbc.gridwidth = 15;
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(textA, gbc);

        keys.add(zeroKey);
        keys.add(oneKey);
        keys.add(twoKey);
        keys.add(threeKey);
        keys.add(fourKey);
        keys.add(fiveKey);
        keys.add(sixKey);
        keys.add(sevenKey);
        keys.add(eightKey);
        keys.add(nineKey);
        keys.add(aKey);
        keys.add(bKey);
        keys.add(cKey);
        keys.add(dKey);
        keys.add(eKey);
        keys.add(fKey);
        keys.add(gKey);
        keys.add(hKey);
        keys.add(iKey);
        keys.add(jKey);
        keys.add(kKey);
        keys.add(lKey);
        keys.add(mKey);
        keys.add(nKey);
        keys.add(oKey);
        keys.add(pKey);
        keys.add(qKey);
        keys.add(rKey);
        keys.add(sKey);
        keys.add(tKey);
        keys.add(uKey);
        keys.add(vKey);
        keys.add(wKey);
        keys.add(xKey);
        keys.add(yKey);
        keys.add(zKey);
        keys.add(ßKey);
        keys.add(tabKey);
        keys.add(capsLockKey);
        keys.add(lShiftKey);
        keys.add(lControlKey);
        keys.add(windowsKey);
        keys.add(altKey);
        keys.add(spaceKey);
        keys.add(altGraphKey);
        keys.add(enterKey);
        keys.add(deleteKey);
        keys.add(rShiftKey);
        keys.add(rControlKey);
        keys.add(dotKey);
        keys.add(commaKey);
        keys.add(minusKey);
        keys.add(nummberSignKey);
        keys.add(plusKey);
        keys.add(CircumflexKey);
        keys.add(lessKey);
        keys.add(acuteKey);

        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        called++;
        for (Key k : keys) {
            if (e.getKeyCode() == k.getKeyCode()
                    && (k.getKeyLocation() == -1 || k.getKeyLocation() == e.getKeyLocation())) {
                k.setBackground(Color.green);
                //textA.setText(textA.getText() + k.getText());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Key k : keys) {
            k.setBackground(null);
        }
    }

    @Override
    public void run() {
        time = System.currentTimeMillis();
        while (true) {
            if (time + 1000 <= System.currentTimeMillis()) {
                System.out.println(called + " Keys pressed per second.");
                called = 0;
                time = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new KeyLogger();
    }

}

class Key extends JLabel {

    int keyCode, keyLocation;
    String displayText;

    public Key(String text) {
        keyLocation = -1;
        this.setText(text);
        this.setBorder(new LineBorder(Color.black, 2));
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
    }

    public Key(int keyCode, String text, String displayText) {
        keyLocation = -1;
        this.keyCode = keyCode;
        this.setText(text);
        this.setBorder(new LineBorder(Color.black, 2));
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
    }

    public Key(int keyCode, int keyLocation, String text, String displayText) {
        this.keyCode = keyCode;
        this.keyLocation = keyLocation;
        this.setText(text);
        this.setBorder(new LineBorder(Color.black, 2));
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
    }

    public Key(int keyCode, String text) {
        keyLocation = -1;
        this.keyCode = keyCode;
        this.setText(text);
        this.setBorder(new LineBorder(Color.black, 2));
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
    }

    public Key(int keyCode, int keyLocation, String text) {
        this.keyCode = keyCode;
        this.keyLocation = keyLocation;
        this.setText(text);
        this.setBorder(new LineBorder(Color.black, 2));
        this.setOpaque(true);
        this.setHorizontalAlignment(CENTER);
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getKeyLocation() {
        return keyLocation;
    }

    

}
