package Calculator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class QuickCalculator implements KeyListener {
    // TODO: cosmetic changes
    // FIXME: zero after comma
    // FIXME: keyinput operator don't work
    // FIXME: long numbers create zeros at the end and add nummbers at the beggining, -> maybe double length

    // Declare objects
    public static JFrame frame;
    public static JPanel panel;
    public static JLabel outL;
    public static GridBagLayout gbl;
    public static GridBagConstraints gbc;

    public static double firstNumber = 0;
    public static double secondNumber = 0;

    public static int operation, spot;
    public static boolean afterCalculation, afterComma;
    // placeholder for operations
    public static final int ADD = 0;
    public static final int SUBTRACT = 1;
    public static final int MULTIPLY = 2;
    public static final int DIVIDE = 3;
    // placeholder for spot of number (before or after operator)
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    public QuickCalculator() {
        // Initialize objects
        frame = new JFrame("Calculator");
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
    }

    public static void initialize() {
        // Create button and output label
        outL = new JLabel("Hello", SwingConstants.RIGHT);
        JButton resetB = new JButton("AC");
        JButton toogleMinusB = new JButton("-");
        JButton percentB = new JButton("%");
        JButton divideB = new JButton("/");
        JButton sevenB = new JButton("7");
        JButton eigthB = new JButton("8");
        JButton nineB = new JButton("9");
        JButton multiplyB = new JButton("*");
        JButton fourB = new JButton("4");
        JButton fiveB = new JButton("5");
        JButton sixB = new JButton("6");
        JButton subtractB = new JButton("-");
        JButton oneB = new JButton("1");
        JButton twoB = new JButton("2");
        JButton threeB = new JButton("3");
        JButton addB = new JButton("+");
        JButton zeroB = new JButton("0");
        JButton commaB = new JButton(",");
        JButton equalsB = new JButton("=");

        // position the button
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        panel.add(outL, gbc);
        gbc.gridwidth = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(resetB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(toogleMinusB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(percentB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        panel.add(divideB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(sevenB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(eigthB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(nineB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 3;
        panel.add(multiplyB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(fourB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(fiveB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        panel.add(sixB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(subtractB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(oneB, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(twoB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 5;
        panel.add(threeB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 5;
        panel.add(addB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(zeroB, gbc);
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(commaB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 6;
        panel.add(equalsB, gbc);

        zeroB.addActionListener(e -> zeroM());
        oneB.addActionListener(e -> oneM());
        twoB.addActionListener(e -> twoM());
        threeB.addActionListener(e -> threeM());
        fourB.addActionListener(e -> fourM());
        fiveB.addActionListener(e -> fiveM());
        sixB.addActionListener(e -> sixM());
        sevenB.addActionListener(e -> sevenM());
        eigthB.addActionListener(e -> eigthM());
        nineB.addActionListener(e -> nineM());
        addB.addActionListener(e -> addM());
        subtractB.addActionListener(e -> subtractM());
        multiplyB.addActionListener(e -> multiplyM());
        divideB.addActionListener(e -> divideM());
        percentB.addActionListener(e -> percentM());
        toogleMinusB.addActionListener(e -> toogleMinusM());
        commaB.addActionListener(e -> commaM());
        resetB.addActionListener(e -> resetM());
        equalsB.addActionListener(e -> equalsM());

        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_0: case KeyEvent.VK_NUMPAD0: 
                zeroM();
                break;
            case KeyEvent.VK_1: case KeyEvent.VK_NUMPAD1:
                oneM();
                break;
            case KeyEvent.VK_2: case KeyEvent.VK_NUMPAD2:
                twoM();
                break;
            case KeyEvent.VK_3: case KeyEvent.VK_NUMPAD3:
                threeM();
                break;
            case KeyEvent.VK_4: case KeyEvent.VK_NUMPAD4:
                fourM();
                break;
            case KeyEvent.VK_5: case KeyEvent.VK_NUMPAD5:
                fiveM();
                break;
            case KeyEvent.VK_6: case KeyEvent.VK_NUMPAD6:
                sixM();
                break;
            case KeyEvent.VK_7: case KeyEvent.VK_NUMPAD7:
                sevenM();
                break;
            case KeyEvent.VK_8: case KeyEvent.VK_NUMPAD8:
                eigthM();
                break;
            case KeyEvent.VK_9: case KeyEvent.VK_NUMPAD9:
                nineM();
                break;
            case KeyEvent.VK_PLUS: case KeyEvent.VK_ADD:
                addM();
                break;
            case KeyEvent.VK_MINUS: case KeyEvent.VK_SUBTRACT:
                subtractM();
                break;
            case KeyEvent.VK_NUMBER_SIGN + KeyEvent.VK_SHIFT: case KeyEvent.VK_MULTIPLY:
                multiplyM();
                break;
            case KeyEvent.VK_SLASH: case KeyEvent.VK_DIVIDE:
                divideM();
                break;
            case KeyEvent.VK_COMMA:
                commaM();
                break;
            case KeyEvent.VK_DELETE:
                resetM();
                break;
            case KeyEvent.VK_ENTER:
                equalsM();
                break;
            case KeyEvent.VK_5 + KeyEvent.VK_SHIFT:
                percentM();
                break;
            default:
                break;
        }
    }

    /**
     * calculate the result of the two numbers
     */
    private static void equalsM() {
        switch (operation) {
            case ADD:
                firstNumber += secondNumber;
                break;
            case SUBTRACT:
                firstNumber -= secondNumber;
                break;
            case MULTIPLY:
                firstNumber *= secondNumber;
                break;
            case DIVIDE:
                firstNumber /= secondNumber;
                break;
        }
        outL.setText(toString(firstNumber));
        secondNumber = 0;
        spot = FIRST;
        afterCalculation = true;
        afterComma = false;
    }

    /**
     * reset the calculator
     */
    private static void resetM() {
        firstNumber = 0;
        secondNumber = 0;
        outL.setText(toString(firstNumber));
        afterComma = false;
    }

    /**
     * adds a comma to the current number
     */
    private static void commaM() {
        afterComma = true;
    }

    /**
     * negates the number
     */
    private static void toogleMinusM() {
        if (spot == FIRST) {
            firstNumber *= -1;
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber *= -1;
            outL.setText(toString(secondNumber));
        }
    }

    /**
     * calculates the percentage of the number of hundred
     */
    private static void percentM() {
        if (spot == FIRST) {
            firstNumber /= 100;
        }
        outL.setText(toString(firstNumber));
    }

    /**
     * operator divide
     */
    private static void divideM() {
        operation = DIVIDE;
        spot = SECOND;
        afterComma = false;
    }

    /**
     * operator multiply
     */
    private static void multiplyM() {
        operation = MULTIPLY;
        spot = SECOND;
        afterComma = false;
    }

    /**
     * operator subtract
     */
    private static void subtractM() {
        operation = SUBTRACT;
        spot = SECOND;
        afterComma = false;
    }

    /**
     * operator add
     */
    private static void addM() {
        operation = ADD;
        spot = SECOND;
        afterComma = false;
    }

    /**
     * number nine
     */
    private static void nineM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 9;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 9));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 9));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number eight
     */
    private static void eigthM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 8;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 8));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 8));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number seven
     */
    private static void sevenM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 7;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 7));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 7));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number six
     */
    private static void sixM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 6;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 6));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 6));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number five
     */
    private static void fiveM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 5;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 5));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 5));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number four
     */
    private static void fourM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 4;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 4));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 4));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number three
     */
    private static void threeM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 3;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 3));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 3));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number two
     */
    private static void twoM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 2;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 2));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 2));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number one
     */
    private static void oneM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 1;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 1));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 1));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * number zero
     */
    private static void zeroM() {
        if (spot == FIRST && afterCalculation) {
            firstNumber = 0;
            afterCalculation = false;
            outL.setText(toString(firstNumber));
        } else if (spot == FIRST) {
            firstNumber = Double.valueOf(append(firstNumber, 0));
            outL.setText(toString(firstNumber));
        } else if (spot == SECOND) {
            secondNumber = Double.valueOf(append(secondNumber, 0));
            outL.setText(toString(secondNumber));
        }

    }

    /**
     * appends the secondNumber to the firstNumber
     * @param pFirstNumber
     * @param pSecondNumber
     * @return
     */
    private static String append(double pFirstNumber, int pSecondNumber) {
        int i;
        String output = "";
        String firstNumber = String.valueOf(pFirstNumber);
        for (i = 0; firstNumber.charAt(i) != '.'; i++)
            ;
        if (afterComma == false) {
            output = firstNumber.substring(0, i);
            output += pSecondNumber;
            output += firstNumber.substring(i);
        } else {
            if (firstNumber.charAt(firstNumber.length() - 1) == '0') {
                output = firstNumber.substring(0, firstNumber.length() - 1) + String.valueOf(pSecondNumber);
            } else {
                output = firstNumber + String.valueOf(pSecondNumber);
            }

        }
        //try catch
        if(Double.valueOf(output) <= Double.MAX_VALUE){
            return output;
        }else{
            return String.valueOf(pFirstNumber);
        }
        
    }

    /**
     * returns a String of the double number
     * @param number
     * @return
     */
    private static String toString(double number) {
        DecimalFormat df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String output = df.format(number);
        return output.replace('.', ',');
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
