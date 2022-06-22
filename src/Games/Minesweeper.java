package Games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUIs.ErrorNote;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Minesweeper implements MouseListener {

    private static JFrame frame;
    private static JPanel panel;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;
    private static Field[][] fields;
    private static int amountOfMines, numberOfRows, numberOfColums, uncoveredFields;

    public Minesweeper() {
        // Initialize objects
        frame = new JFrame("Minesweeper");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.pack();
        frame.setFocusable(true);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);
        gbc.insets = new Insets(1, 1, 1, 1);

        frame.addMouseListener(this);

        initialize();
    }

    public static void initialize() {
        numberOfColums = 8;
        numberOfRows = 10;
        amountOfMines = 10;
        uncoveredFields = 0;
        fields = new Field[numberOfRows][numberOfColums];

        if (amountOfMines >= numberOfColums * numberOfRows) {
            amountOfMines = (numberOfColums * numberOfRows) / 8;
        }

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColums; j++) {
                fields[i][j] = new Field();
                gbc.gridx = i;
                gbc.gridy = j;
                panel.add(fields[i][j], gbc);
                fields[i][j].setBackground(Color.lightGray);
                fields[i][j].setHorizontalAlignment(JLabel.CENTER);
                fields[i][j].setOpaque(true);
            }
        }

        initializeBoard();

        frame.setVisible(true);
    }

    public static void initializeBoard() {
        int rRow, rColum;
        for (int i = 0; i < amountOfMines; i++) {
            do {
                rRow = (int) (Math.random() * numberOfRows - 1);
                rColum = (int) (Math.random() * numberOfColums - 0.1);
            } while (fields[rRow][rColum].getStatus() == Field.MINE);
            fields[rRow][rColum].status = Field.MINE;

            try {
                if (fields[rRow - 1][rColum - 1].status != Field.MINE) {
                    fields[rRow - 1][rColum - 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow - 1][rColum].status != Field.MINE) {
                    fields[rRow - 1][rColum].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow - 1][rColum + 1].status != Field.MINE) {
                    fields[rRow - 1][rColum + 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow][rColum - 1].status != Field.MINE) {
                    fields[rRow][rColum - 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow][rColum + 1].status != Field.MINE) {
                    fields[rRow][rColum + 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow + 1][rColum - 1].status != Field.MINE) {
                    fields[rRow + 1][rColum - 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow + 1][rColum].status != Field.MINE) {
                    fields[rRow + 1][rColum].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (fields[rRow + 1][rColum + 1].status != Field.MINE) {
                    fields[rRow + 1][rColum + 1].status++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        for (Field ls[] : fields) {
            for (Field l : ls) {
                l.setText(String.valueOf(l.getStatus()));
                l.setForeground(Color.lightGray);
            }
        }
    }

    private void setUp() {
        for (Field fs[] : fields) {
            for (Field f : fs) {
                f.addMouseListener(this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColums; j++) {
                    if (fields[i][j] == (Field) e.getSource()) {
                        fieldOpened(i, j);
                    }
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            flagPlaced((Field) e.getSource());
        }
    }


    private static void fieldOpened(int x, int y) {
        Field field;
        try {
            field = fields[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        if (field.getStatus() >= 30) {
            return;
        }

        if (field.covered) {
            field.covered = false;
            uncoveredFields++;
            if (field.getStatus() == 0) {
                field.setBackground(Color.gray);
                field.setForeground(Color.gray);
                fieldOpened(x - 1, y - 1);
                fieldOpened(x, y - 1);
                fieldOpened(x + 1, y - 1);
                fieldOpened(x - 1, y);
                fieldOpened(x + 1, y);
                fieldOpened(x - 1, y + 1);
                fieldOpened(x, y + 1);
                fieldOpened(x + 1, y + 1);
            } else if (field.getStatus() == Field.MINE) {
                field.setForeground(Color.red);
                new ErrorNote("Du hast verloren.");
            } else {
                switch (field.getStatus()) {
                    case Field.ONE:
                        field.setForeground(Color.blue);
                        break;
                    case Field.TWO:
                        field.setForeground(Color.green);
                        break;
                    case Field.THREE:
                        field.setForeground(Color.red);
                        break;
                    case Field.FOUR:
                        field.setForeground(Color.yellow);
                        break;
                    case Field.FIVE:
                        field.setForeground(Color.orange);
                        break;
                    case Field.SIX:
                        field.setForeground(Color.pink);
                        break;
                    case Field.SEVEN:
                        field.setForeground(Color.cyan);
                        break;
                    case Field.EIGHT:
                        field.setForeground(Color.gray);
                        break;
                    default:
                        field.setForeground(Color.black);
                        break;
                }
            }
        }

        if (uncoveredFields == numberOfColums * numberOfRows - amountOfMines) {
            new ErrorNote("Du hast gewonnen.");
        }
    }

    private static void flagPlaced(Field field) {
        if (field.covered) {
            if (field.getStatus() >= 30) {
                field.status -= Field.FLAG;
                field.setBackground(Color.lightGray);
                field.setForeground(Color.lightGray);
            } else {
                field.status += Field.FLAG;
                field.setBackground(Color.red);
                field.setForeground(Color.red);
            }
        }
    }

    public static void main(String[] args) {
        Minesweeper m = new Minesweeper();
        m.setUp();
    }

    private static class Field extends JLabel {

        private static final long serialVersionUID = 1L;

        public int status;
        public boolean covered;

        public static final int MINE = 9;
        public static final int FLAG = 30;

        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;

        public Field() {
            status = 0;
            covered = true;
        }

        public int getStatus() {
            return status;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
