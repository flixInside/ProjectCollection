package Games;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import GUIs.ErrorNote;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

public class MasterMind {
    public static JFrame frame;
    public static JPanel panel, oneOneP, oneTwoP, oneThreeP, oneFourP, twoOneP, twoTwoP, twoThreeP, twoFourP, threeOneP,
            threeTwoP, threeThreeP, threeFourP, fourOneP, fourTwoP, fourThreeP, fourFourP, fiveOneP, fiveTwoP,
            fiveThreeP, fiveFourP,
            sixOneP, sixTwoP, sixThreeP, sixFourP, sevenOneP, sevenTwoP, sevenThreeP, sevenFourP, eightOneP, eightTwoP,
            eightThreeP,
            eightFourP, nineOneP, nineTwoP, nineThreeP, nineFourP, tenOneP, tenTwoP, tenThreeP, tenFourP, oneOP1,
            twoOP1, threeOP1,
            fourOP1, oneOP2, twoOP2, threeOP2, fourOP2, oneOP3, twoOP3, threeOP3, fourOP3, oneOP4, twoOP4, threeOP4,
            fourOP4, oneOP5,
            twoOP5, threeOP5, fourOP5, oneOP6, twoOP6, threeOP6, fourOP6, oneOP7, twoOP7, threeOP7, fourOP7, oneOP8,
            twoOP8, threeOP8,
            fourOP8, oneOP9, twoOP9, threeOP9, fourOP9, oneOP10, twoOP10, threeOP10, fourOP10;
    private static JButton checkB, resetB;
    private static JLabel oneL, twoL, threeL, fourL, fiveL, sixL, sevenL, eightL, nineL, tenL, timeL, stopwatchL;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static JMenuBar bar;
    private static JPanel[][] panels, checkPanels;
    private static GameColor[] currentColor, goalColors;
    private static int round, completeRight, halfRight;
    private static ArrayList<JLabel> labels;

    public MasterMind() {
        // Initialize objects
        frame = new JFrame("Master Mind");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        bar = new JMenuBar();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(400, 900));
        frame.pack();
        frame.setFocusable(true);
        frame.setJMenuBar(bar);
        panel.setBackground(Color.lightGray);
        panel.setLayout(gbl);
        frame.setLocationRelativeTo(null);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(5, 5, 5, 5);

        initialize();
    }

    public static void initialize() {
        // initilazie objects
        panels = new JPanel[10][4];
        checkPanels = new JPanel[10][4];
        goalColors = new GameColor[4];
        currentColor = new GameColor[4];
        labels = new ArrayList<>();

        timeL = new JLabel();
        stopwatchL = new JLabel();
        tenL = new JLabel("10");
        nineL = new JLabel("9");
        eightL = new JLabel("8");
        sevenL = new JLabel("7");
        sixL = new JLabel("6");
        fiveL = new JLabel("5");
        fourL = new JLabel("4");
        threeL = new JLabel("3");
        twoL = new JLabel("2");
        oneL = new JLabel("1 ");

        threeOneP = new JPanel();
        threeTwoP = new JPanel();
        threeThreeP = new JPanel();
        threeFourP = new JPanel();
        fourOneP = new JPanel();
        fourTwoP = new JPanel();
        fourThreeP = new JPanel();
        fourFourP = new JPanel();
        fiveOneP = new JPanel();
        fiveTwoP = new JPanel();
        fiveThreeP = new JPanel();
        fiveFourP = new JPanel();
        sixOneP = new JPanel();
        sixTwoP = new JPanel();
        sixThreeP = new JPanel();
        sixFourP = new JPanel();
        sevenOneP = new JPanel();
        sevenTwoP = new JPanel();
        sevenThreeP = new JPanel();
        sevenFourP = new JPanel();
        eightOneP = new JPanel();
        eightTwoP = new JPanel();
        eightThreeP = new JPanel();
        eightFourP = new JPanel();
        tenOneP = new JPanel();
        tenTwoP = new JPanel();
        tenThreeP = new JPanel();
        tenFourP = new JPanel();
        nineOneP = new JPanel();
        nineThreeP = new JPanel();
        nineTwoP = new JPanel();
        nineFourP = new JPanel();
        twoOneP = new JPanel();
        twoTwoP = new JPanel();
        twoThreeP = new JPanel();
        twoFourP = new JPanel();
        oneOneP = new JPanel();
        oneTwoP = new JPanel();
        oneThreeP = new JPanel();
        oneFourP = new JPanel();
        oneOP1 = new JPanel();
        threeOP1 = new JPanel();
        twoOP1 = new JPanel();
        fourOP1 = new JPanel();
        threeOP2 = new JPanel();
        twoOP2 = new JPanel();
        fourOP2 = new JPanel();
        oneOP2 = new JPanel();
        fourOP3 = new JPanel();
        oneOP3 = new JPanel();
        twoOP3 = new JPanel();
        threeOP3 = new JPanel();
        threeOP5 = new JPanel();
        oneOP6 = new JPanel();
        twoOP6 = new JPanel();
        fourOP7 = new JPanel();
        fourOP5 = new JPanel();
        fourOP4 = new JPanel();
        twoOP4 = new JPanel();
        twoOP5 = new JPanel();
        fourOP6 = new JPanel();
        twoOP7 = new JPanel();
        threeOP7 = new JPanel();
        oneOP4 = new JPanel();
        threeOP4 = new JPanel();
        threeOP6 = new JPanel();
        oneOP7 = new JPanel();
        oneOP5 = new JPanel();
        fourOP8 = new JPanel();
        twoOP8 = new JPanel();
        threeOP8 = new JPanel();
        oneOP8 = new JPanel();
        threeOP9 = new JPanel();
        fourOP9 = new JPanel();
        oneOP9 = new JPanel();
        twoOP9 = new JPanel();
        threeOP10 = new JPanel();
        oneOP10 = new JPanel();
        twoOP10 = new JPanel();
        fourOP10 = new JPanel();

        resetB = new JButton("New Game");
        checkB = new JButton("Check");

        // ten
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(tenL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(tenOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(tenTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        panel.add(tenThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 0;
        panel.add(tenFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 0;
        panel.add(threeOP10, gbc);
        gbc.gridx = 10;
        gbc.gridy = 1;
        panel.add(oneOP10, gbc);
        gbc.gridx = 11;
        gbc.gridy = 0;
        panel.add(fourOP10, gbc);
        gbc.gridx = 11;
        gbc.gridy = 1;
        panel.add(twoOP10, gbc);
        // nine
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nineL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(nineOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        panel.add(nineTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 2;
        panel.add(nineThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 2;
        panel.add(nineFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 2;
        panel.add(threeOP9, gbc);
        gbc.gridx = 10;
        gbc.gridy = 3;
        panel.add(oneOP9, gbc);
        gbc.gridx = 11;
        gbc.gridy = 2;
        panel.add(fourOP9, gbc);
        gbc.gridx = 11;
        gbc.gridy = 3;
        panel.add(twoOP9, gbc);
        // eight
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(eightL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(eightOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(eightTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 4;
        panel.add(eightThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 4;
        panel.add(eightFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 4;
        panel.add(threeOP8, gbc);
        gbc.gridx = 10;
        gbc.gridy = 5;
        panel.add(oneOP8, gbc);
        gbc.gridx = 11;
        gbc.gridy = 4;
        panel.add(fourOP8, gbc);
        gbc.gridx = 11;
        gbc.gridy = 5;
        panel.add(twoOP8, gbc);
        // seven
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(sevenL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(sevenOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 6;
        panel.add(sevenTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 6;
        panel.add(sevenThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 6;
        panel.add(sevenFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 6;
        panel.add(threeOP7, gbc);
        gbc.gridx = 10;
        gbc.gridy = 7;
        panel.add(oneOP7, gbc);
        gbc.gridx = 11;
        gbc.gridy = 6;
        panel.add(fourOP7, gbc);
        gbc.gridx = 11;
        gbc.gridy = 7;
        panel.add(twoOP7, gbc);
        // six
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(sixL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(sixOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 8;
        panel.add(sixTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 8;
        panel.add(sixThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 8;
        panel.add(sixFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 8;
        panel.add(threeOP6, gbc);
        gbc.gridx = 10;
        gbc.gridy = 9;
        panel.add(oneOP6, gbc);
        gbc.gridx = 11;
        gbc.gridy = 8;
        panel.add(fourOP6, gbc);
        gbc.gridx = 11;
        gbc.gridy = 9;
        panel.add(twoOP6, gbc);
        // five
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(fiveL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 10;
        panel.add(fiveOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 10;
        panel.add(fiveTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 10;
        panel.add(fiveThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 10;
        panel.add(fiveFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 10;
        panel.add(threeOP5, gbc);
        gbc.gridx = 10;
        gbc.gridy = 11;
        panel.add(oneOP5, gbc);
        gbc.gridx = 11;
        gbc.gridy = 10;
        panel.add(fourOP5, gbc);
        gbc.gridx = 11;
        gbc.gridy = 11;
        panel.add(twoOP5, gbc);
        // four
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(fourL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 12;
        panel.add(fourOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 12;
        panel.add(fourTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 12;
        panel.add(fourThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 12;
        panel.add(fourFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 12;
        panel.add(threeOP4, gbc);
        gbc.gridx = 10;
        gbc.gridy = 13;
        panel.add(oneOP4, gbc);
        gbc.gridx = 11;
        gbc.gridy = 12;
        panel.add(fourOP4, gbc);
        gbc.gridx = 11;
        gbc.gridy = 13;
        panel.add(twoOP4, gbc);
        // three
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 14;
        panel.add(threeL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 14;
        panel.add(threeOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 14;
        panel.add(threeTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 14;
        panel.add(threeThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 14;
        panel.add(threeFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 14;
        panel.add(threeOP3, gbc);
        gbc.gridx = 10;
        gbc.gridy = 15;
        panel.add(oneOP3, gbc);
        gbc.gridx = 11;
        gbc.gridy = 14;
        panel.add(fourOP3, gbc);
        gbc.gridx = 11;
        gbc.gridy = 15;
        panel.add(twoOP3, gbc);
        // two
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 16;
        panel.add(twoL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 16;
        panel.add(twoOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 16;
        panel.add(twoTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 16;
        panel.add(twoThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 16;
        panel.add(twoFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 16;
        panel.add(threeOP2, gbc);
        gbc.gridx = 10;
        gbc.gridy = 17;
        panel.add(oneOP2, gbc);
        gbc.gridx = 11;
        gbc.gridy = 16;
        panel.add(fourOP2, gbc);
        gbc.gridx = 11;
        gbc.gridy = 17;
        panel.add(twoOP2, gbc);
        // one
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 18;
        panel.add(oneL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 18;
        panel.add(oneOneP, gbc);
        gbc.gridx = 4;
        gbc.gridy = 18;
        panel.add(oneTwoP, gbc);
        gbc.gridx = 6;
        gbc.gridy = 18;
        panel.add(oneThreeP, gbc);
        gbc.gridx = 8;
        gbc.gridy = 18;
        panel.add(oneFourP, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 10;
        gbc.gridy = 18;
        panel.add(threeOP1, gbc);
        gbc.gridx = 10;
        gbc.gridy = 19;
        panel.add(oneOP1, gbc);
        gbc.gridx = 11;
        gbc.gridy = 18;
        panel.add(fourOP1, gbc);
        gbc.gridx = 11;
        gbc.gridy = 19;
        panel.add(twoOP1, gbc);
        // button
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 20;
        panel.add(resetB, gbc);
        gbc.gridx = 4;
        gbc.gridy = 20;
        panel.add(checkB, gbc);

        // Initialize Menu items
        JMenu firstColor = new JMenu("Slot 1");
        JMenu secondColor = new JMenu("Slot 2");
        JMenu threeColor = new JMenu("Slot 3");
        JMenu fouthColor = new JMenu("Slot 4");

        JMenuItem whiteOne = new JMenuItem("White");
        JMenuItem greenOne = new JMenuItem("Green");
        JMenuItem blueOne = new JMenuItem("Blue");
        JMenuItem redOne = new JMenuItem("Red");
        JMenuItem magentaOne = new JMenuItem("Magenta");

        JMenuItem whiteTwo = new JMenuItem("White");
        JMenuItem greenTwo = new JMenuItem("Green");
        JMenuItem blueTwo = new JMenuItem("Blue");
        JMenuItem redTwo = new JMenuItem("Red");
        JMenuItem magentaTwo = new JMenuItem("Magenta");

        JMenuItem whiteThree = new JMenuItem("White");
        JMenuItem greenThree = new JMenuItem("Green");
        JMenuItem blueThree = new JMenuItem("Blue");
        JMenuItem redThree = new JMenuItem("Red");
        JMenuItem magentaThree = new JMenuItem("Magenta");

        JMenuItem whiteFour = new JMenuItem("White");
        JMenuItem greenFour = new JMenuItem("Green");
        JMenuItem blueFour = new JMenuItem("Blue");
        JMenuItem redFour = new JMenuItem("Red");
        JMenuItem magentaFour = new JMenuItem("Magenta");

        JMenuItem whiteFive = new JMenuItem("White");
        JMenuItem greenFive = new JMenuItem("Green");
        JMenuItem blueFive = new JMenuItem("Blue");
        JMenuItem redFive = new JMenuItem("Red");
        JMenuItem magentaFive = new JMenuItem("Magenta");

        // Adding Menu items
        bar.add(firstColor);
        bar.add(secondColor);
        bar.add(threeColor);
        bar.add(fouthColor);

        firstColor.add(whiteOne);
        firstColor.add(greenOne);
        firstColor.add(blueOne);
        firstColor.add(redOne);
        firstColor.add(magentaOne);

        secondColor.add(whiteTwo);
        secondColor.add(greenTwo);
        secondColor.add(blueTwo);
        secondColor.add(redTwo);
        secondColor.add(magentaTwo);

        threeColor.add(whiteThree);
        threeColor.add(greenThree);
        threeColor.add(blueThree);
        threeColor.add(redThree);
        threeColor.add(magentaThree);

        fouthColor.add(whiteFour);
        fouthColor.add(greenFour);
        fouthColor.add(blueFour);
        fouthColor.add(redFour);
        fouthColor.add(magentaFour);

        resetB.addActionListener(e -> reset());
        checkB.addActionListener(e -> check());

        // change the font color of the labels to light gray
        labels.add(oneL);
        labels.add(twoL);
        labels.add(threeL);
        labels.add(fourL);
        labels.add(fiveL);
        labels.add(sixL);
        labels.add(sevenL);
        labels.add(eightL);
        labels.add(nineL);
        labels.add(tenL);
        labels.add(timeL);
        labels.add(stopwatchL);

        for (JLabel l : labels) {
            l.setHorizontalAlignment(JLabel.RIGHT);
        }

        // add Panels to twodimensional Array
        panels[0][0] = oneOneP;
        panels[0][1] = oneTwoP;
        panels[0][2] = oneThreeP;
        panels[0][3] = oneFourP;
        panels[1][0] = twoOneP;
        panels[1][1] = twoTwoP;
        panels[1][2] = twoThreeP;
        panels[1][3] = twoFourP;
        panels[2][0] = threeOneP;
        panels[2][1] = threeTwoP;
        panels[2][2] = threeThreeP;
        panels[2][3] = threeFourP;
        panels[3][0] = fourOneP;
        panels[3][1] = fourTwoP;
        panels[3][2] = fourThreeP;
        panels[3][3] = fourFourP;
        panels[4][0] = fiveOneP;
        panels[4][1] = fiveTwoP;
        panels[4][2] = fiveThreeP;
        panels[4][3] = fiveFourP;
        panels[5][0] = sixOneP;
        panels[5][1] = sixTwoP;
        panels[5][2] = sixThreeP;
        panels[5][3] = sixFourP;
        panels[6][0] = sevenOneP;
        panels[6][1] = sevenTwoP;
        panels[6][2] = sevenThreeP;
        panels[6][3] = sevenFourP;
        panels[7][0] = eightOneP;
        panels[7][1] = eightTwoP;
        panels[7][2] = eightThreeP;
        panels[7][3] = eightFourP;
        panels[8][0] = nineOneP;
        panels[8][1] = nineTwoP;
        panels[8][2] = nineThreeP;
        panels[8][3] = nineFourP;
        panels[9][0] = tenOneP;
        panels[9][1] = tenTwoP;
        panels[9][2] = tenThreeP;
        panels[9][3] = tenFourP;

        // add checkPanels to twodimensionalArray
        checkPanels[0][0] = oneOP1;
        checkPanels[0][1] = twoOP1;
        checkPanels[0][2] = threeOP1;
        checkPanels[0][3] = fourOP1;
        checkPanels[1][0] = oneOP2;
        checkPanels[1][1] = twoOP2;
        checkPanels[1][2] = threeOP2;
        checkPanels[1][3] = fourOP2;
        checkPanels[2][0] = oneOP3;
        checkPanels[2][1] = twoOP3;
        checkPanels[2][2] = threeOP3;
        checkPanels[2][3] = fourOP3;
        checkPanels[3][0] = oneOP4;
        checkPanels[3][1] = twoOP4;
        checkPanels[3][2] = threeOP4;
        checkPanels[3][3] = fourOP4;
        checkPanels[4][0] = oneOP5;
        checkPanels[4][1] = twoOP5;
        checkPanels[4][2] = threeOP5;
        checkPanels[4][3] = fourOP5;
        checkPanels[5][0] = oneOP6;
        checkPanels[5][1] = twoOP6;
        checkPanels[5][2] = threeOP6;
        checkPanels[5][3] = fourOP6;
        checkPanels[6][0] = oneOP7;
        checkPanels[6][1] = twoOP7;
        checkPanels[6][2] = threeOP7;
        checkPanels[6][3] = fourOP7;
        checkPanels[7][0] = oneOP8;
        checkPanels[7][1] = twoOP8;
        checkPanels[7][2] = threeOP8;
        checkPanels[7][3] = fourOP8;
        checkPanels[8][0] = oneOP9;
        checkPanels[8][1] = twoOP9;
        checkPanels[8][2] = threeOP9;
        checkPanels[8][3] = fourOP9;
        checkPanels[9][0] = oneOP10;
        checkPanels[9][1] = twoOP10;
        checkPanels[9][2] = threeOP10;
        checkPanels[9][3] = fourOP10;

        // changing Color of checkboxes
        for (JPanel[] ps : checkPanels) {
            for (JPanel p : ps) {
                p.setBackground(Color.gray);
            }
        }

        // adding ActionListeners
        whiteOne.addActionListener(e -> whiteOne());
        greenOne.addActionListener(e -> greenOne());
        blueOne.addActionListener(e -> blueOne());
        redOne.addActionListener(e -> redOne());
        magentaOne.addActionListener(e -> magentaOne());

        whiteTwo.addActionListener(e -> whiteTwo());
        greenTwo.addActionListener(e -> greenTwo());
        blueTwo.addActionListener(e -> blueTwo());
        redTwo.addActionListener(e -> redTwo());
        magentaTwo.addActionListener(e -> magentaTwo());

        whiteThree.addActionListener(e -> whiteThree());
        greenThree.addActionListener(e -> greenThree());
        blueThree.addActionListener(e -> blueThree());
        redThree.addActionListener(e -> redThree());
        magentaThree.addActionListener(e -> magentaThree());

        whiteFour.addActionListener(e -> whiteFour());
        greenFour.addActionListener(e -> greenFour());
        blueFour.addActionListener(e -> blueFour());
        redFour.addActionListener(e -> redFour());
        magentaFour.addActionListener(e -> magentaFour());

        whiteFive.addActionListener(e -> whiteFive());
        greenFive.addActionListener(e -> greenFive());
        blueFive.addActionListener(e -> blueFive());
        redFive.addActionListener(e -> redFive());
        magentaFive.addActionListener(e -> magentaFive());

        // reset Method
        reset();

        // showing the frame
        frame.setVisible(true);
    }

    public static void whiteOne() {
        panels[round][0].setBackground(Color.white);
        currentColor[0] = new GameColor(Color.white);
    }

    public static void greenOne() {
        panels[round][0].setBackground(Color.green);
        currentColor[0] = new GameColor(Color.green);
    }

    public static void blueOne() {
        panels[round][0].setBackground(Color.blue);
        currentColor[0] = new GameColor(Color.blue);
    }

    public static void redOne() {
        panels[round][0].setBackground(Color.red);
        currentColor[0] = new GameColor(Color.red);
    }

    public static void magentaOne() {
        panels[round][0].setBackground(Color.magenta);
        currentColor[0] = new GameColor(Color.magenta);
    }

    public static void whiteTwo() {
        panels[round][1].setBackground(Color.white);
        currentColor[1] = new GameColor(Color.white);
    }

    public static void greenTwo() {
        panels[round][1].setBackground(Color.green);
        currentColor[1] = new GameColor(Color.green);
    }

    public static void blueTwo() {
        panels[round][1].setBackground(Color.blue);
        currentColor[1] = new GameColor(Color.blue);
    }

    public static void redTwo() {
        panels[round][1].setBackground(Color.red);
        currentColor[1] = new GameColor(Color.red);
    }

    public static void magentaTwo() {
        panels[round][1].setBackground(Color.magenta);
        currentColor[1] = new GameColor(Color.magenta);
    }

    public static void whiteThree() {
        panels[round][2].setBackground(Color.white);
        currentColor[2] = new GameColor(Color.white);
    }

    public static void greenThree() {
        panels[round][2].setBackground(Color.green);
        currentColor[2] = new GameColor(Color.green);
    }

    public static void magentaThree() {
        panels[round][2].setBackground(Color.magenta);
        currentColor[2] = new GameColor(Color.magenta);
    }

    public static void blueThree() {
        panels[round][2].setBackground(Color.blue);
        currentColor[2] = new GameColor(Color.blue);
    }

    public static void redThree() {
        panels[round][2].setBackground(Color.red);
        currentColor[2] = new GameColor(Color.red);
    }

    public static void greenFour() {
        panels[round][3].setBackground(Color.green);
        currentColor[3] = new GameColor(Color.green);
    }

    public static void blueFour() {
        panels[round][3].setBackground(Color.blue);
        currentColor[3] = new GameColor(Color.blue);
    }

    public static void redFour() {
        panels[round][3].setBackground(Color.red);
        currentColor[3] = new GameColor(Color.red);
    }

    public static void whiteFour() {
        panels[round][3].setBackground(Color.white);
        currentColor[3] = new GameColor(Color.white);
    }

    public static void magentaFour() {
        panels[round][3].setBackground(Color.magenta);
        currentColor[3] = new GameColor(Color.magenta);
    }

    public static void whiteFive() {
        panels[round][4].setBackground(Color.white);
        currentColor[4] = new GameColor(Color.white);
    }

    public static void greenFive() {
        panels[round][4].setBackground(Color.green);
        currentColor[4] = new GameColor(Color.green);
    }

    public static void blueFive() {
        panels[round][4].setBackground(Color.blue);
        currentColor[4] = new GameColor(Color.blue);
    }

    public static void redFive() {
        panels[round][4].setBackground(Color.red);
        currentColor[4] = new GameColor(Color.red);
    }

    public static void magentaFive() {
        panels[round][4].setBackground(Color.magenta);
        currentColor[4] = new GameColor(Color.magenta);
    }

    /**
     * checks the user input
     */
    public static void check() {
        // checks if four colors are selected
        for (GameColor c : currentColor) {
            if (c == null) {
                new ErrorNote("Please select four colours.");
                return;
            }
        }

        // temp list for goal-, current- and remove-Colors
        ArrayList<GameColor> tempGoal = new ArrayList<>();
        ArrayList<GameColor> tempCurrent = new ArrayList<>();
        ArrayList<GameColor> itemsToRemoveGoal = new ArrayList<>();
        ArrayList<GameColor> itemsToRemoveCurrent = new ArrayList<>();

        // adds the Colors to the temp list
        for (GameColor c : currentColor) {
            tempCurrent.add(c);
        }
        for (GameColor c : goalColors) {
            tempGoal.add(c);
        }

        // check for complete right
        for (int i = 0; i < tempCurrent.size(); i++) {
            if (tempCurrent.get(i).getColor().equals(tempGoal.get(i).getColor())) {
                completeRight++;
                tempCurrent.get(i).truthState = GameColor.COMPLETERIGHT;
                tempGoal.get(i).truthState = GameColor.COMPLETERIGHT;
            }
        }

        // add complete right colors to remove list and remove them
        for (GameColor c : tempGoal) {
            if (c.getTruthState() == 2) {
                itemsToRemoveGoal.add(c);
            }
        }

        for (GameColor c : tempCurrent) {
            if (c.getTruthState() == 2) {
                itemsToRemoveCurrent.add(c);
            }
        }

        tempGoal.removeAll(itemsToRemoveGoal);
        tempCurrent.removeAll(itemsToRemoveCurrent);

        // check if half right
        for (GameColor c : tempGoal) {
            for (int i = 0; i < tempCurrent.size(); i++) {
                if (colorToString(c.getColor()).equals(colorToString(tempCurrent.get(i).getColor()))
                        && c.getTruthState() != 1) {
                    halfRight++;
                    c.truthState = 1;
                }
            }
        }

        // paint the check panels
        for (int k = completeRight; k > 0; k--) {
            checkPanels[round][k - 1].setBackground(Color.black);
        }

        for (int k = halfRight; k > 0; k--) {
            checkPanels[round][k - 1 + completeRight].setBackground(Color.white);
        }

        // win detection
        if (completeRight == 4) {
            // win
        } else {
            completeRight = 0;
            halfRight = 0;
        }

        round++;

        // reset variables
        currentColor = new GameColor[4];
    }

    public static void reset() {
        Random rnd = new Random();
        round = 0;
        completeRight = 0;
        halfRight = 0;

        // reset pannel color
        for (JPanel[] ps : panels) {
            for (JPanel p : ps) {
                p.setBackground(Color.gray);
            }
        }

        // reset checkPannel color
        for (JPanel[] ps : checkPanels) {
            for (JPanel p : ps) {
                p.setBackground(Color.gray);
            }
        }

        // new goal colors
        for (int i = 0; i < goalColors.length; i++) {
            int r = rnd.nextInt(5);
            switch (r) {
                case 0:
                    goalColors[i] = new GameColor(Color.white);
                    break;
                case 1:
                    goalColors[i] = new GameColor(Color.green);
                    break;
                case 2:
                    goalColors[i] = new GameColor(Color.blue);
                    break;
                case 3:
                    goalColors[i] = new GameColor(Color.red);
                    break;
                case 4:
                    goalColors[i] = new GameColor(Color.magenta);
                    break;
                default:
                    reset();
                    break;
            }
        }
        for (GameColor c : goalColors) {
            System.out.println(colorToString(c.getColor()));
        }

    }

    private static String colorToString(Color pColor) {
        if (pColor.getGreen() == 255 && pColor.getRed() == 0 && pColor.getBlue() == 0) {
            return "Green";
        } else if (pColor.getGreen() == 0 && pColor.getRed() == 255 && pColor.getBlue() == 0) {
            return "Red";
        } else if (pColor.getGreen() == 0 && pColor.getRed() == 0 && pColor.getBlue() == 255) {
            return "Blue";
        } else if (pColor.getGreen() == 255 && pColor.getRed() == 255 && pColor.getBlue() == 255) {
            return "White";
        } else if (pColor.getGreen() == 0 && pColor.getRed() == 255 && pColor.getBlue() == 255) {
            return "magenta";
        } else if (pColor.getGreen() == 0 && pColor.getRed() == 0 && pColor.getBlue() == 0) {
            return "Black";
        } else {
            return "No valid Color that is used in this game.";
        }
    }

    public static void main(String[] args) {
        new MasterMind();
    }
}

class GameColor {

    public int truthState;
    public static final int FALSE = 0;
    public static final int HALFRIGHT = 1;
    public static final int COMPLETERIGHT = 2;

    public Color color;

    public GameColor(Color pColor) {
        color = pColor;
        truthState = FALSE;
    }

    public void setTruthState(int pTruthState) {
        truthState = pTruthState;
    }

    public int getTruthState() {
        return truthState;
    }

    public Color getColor() {
        return color;
    }

}
