package Games.Chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chess implements MouseListener {

    private static JFrame frame;
    private static JPanel panel;
    private static GridBagLayout gbl;
    private static GridBagConstraints gbc;

    public static ArrayList<Piece> pieces, deadPieces;
    public static HashMap<Integer, JPanel> fields;

    private static boolean isFirstField = true;
    private static JPanel firstField;
    private static Piece firstPiece;
    private static int firstID = 0;
    private static Color currenColor = Color.WHITE;

    public Chess() {
        // Initialize objects
        frame = new JFrame("Chess");
        panel = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        // Various settings
        frame.add(panel);
        frame.setMinimumSize(new Dimension(800, 800));
        frame.pack();
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.minimumLayoutSize(frame);

        frame.addMouseListener(this);

        initialize();
    }

    private static void initialize() {
        pieces = new ArrayList<>();
        deadPieces = new ArrayList<>();
        fields = new HashMap<>();

        // position the fields
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gbc.gridx = i;
                gbc.gridy = j;
                JPanel p = new JPanel();
                panel.add(p, gbc);
                fields.put(Integer.valueOf(String.valueOf(i + 1) + String.valueOf(8 - j)), p);
            }
        }

        boolean white = true;
        int n = 0;
        for (Component p : panel.getComponents()) {
            if (white) {
                p.setBackground(java.awt.Color.white);
            } else {
                p.setBackground(java.awt.Color.black);
            }
            if (n < 7) {
                white = !white;
                n++;
            } else {
                n = 0;
            }
        }

        for (JPanel p : fields.values()) {
            p.add(new JLabel());
        }

        placePieces();

        frame.setVisible(true);
    }

    private void setup() {
        for (JPanel p : fields.values()) {
            p.addMouseListener(this);
        }
    }

    private static void placePieces() {
        Color color = Color.WHITE;
        int y = 1;

        // place Pawns
        for (int i = 1; i <= 8; i++) {
            Pawn p = new Pawn(i + 1, 2, Color.WHITE);
            pieces.add(p);
            getFieldAt(i, 2).add(new JLabel(p.getImage()));
            Pawn p1 = new Pawn(i + 1, 7, Color.BLACK);
            pieces.add(p1);
            getFieldAt(i, 7).add(new JLabel(p1.getImage()));
        }

        for (int j = 0; j <= 1; j++) {
            if (j == 1) {
                color = Color.BLACK;
                y = 8;
            }

            // place Rooks
            Rook r = new Rook(1, y, color);
            pieces.add(r);
            getFieldAt(1, y).add(new JLabel(r.getImage()));
            Rook r1 = new Rook(8, y, color);
            pieces.add(r1);
            getFieldAt(8, y).add(new JLabel(r1.getImage()));

            // place Knights
            Knight kn = new Knight(2, y, color);
            pieces.add(kn);
            getFieldAt(2, y).add(new JLabel(kn.getImage()));
            Knight kn1 = new Knight(7, y, color);
            pieces.add(kn1);
            getFieldAt(7, y).add(new JLabel(kn1.getImage()));

            // place Bishops
            Bishop b = new Bishop(3, y, color);
            pieces.add(b);
            getFieldAt(3, y).add(new JLabel(b.getImage()));
            Bishop b1 = new Bishop(6, y, color);
            pieces.add(b1);
            getFieldAt(6, y).add(new JLabel(b1.getImage()));

            // place Queen
            Queen q = new Queen(4, y, color);
            pieces.add(q);
            getFieldAt(4, y).add(new JLabel(q.getImage()));

            // place King
            King k = new King(4, y, color);
            pieces.add(k);
            getFieldAt(5, y).add(new JLabel(k.getImage()));
        }

    }

    private static Integer getX(int input) {
        return (int) input / 10;
    }

    private static Integer getY(int input) {
        return (int) input % 10;
    }

    public static JPanel getFieldAt(int x, int y) {
        for (Integer s : fields.keySet()) {
            if (getX(s) == x && getY(s) == y) {
                return fields.get(s);
            }
        }
        return null;
    }

    public static Piece getPieceAt(int x, int y) {
        for (Piece p : pieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Chess c = new Chess();
        c.setup();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel jp = (JPanel) e.getSource();
        if (isFirstField) {
            for (Entry<Integer, JPanel> entry : fields.entrySet()) {
                if (entry.getValue().equals(jp)) {
                    firstID = entry.getKey();
                    if (getPieceAt(getX(firstID), getY(firstID)) != null) {
                        if (getPieceAt(getX(firstID), getY(firstID)).getColor() != null) {
                            if (getPieceAt(getX(firstID), getY(firstID)).getColor() == currenColor) {
                                for (Piece pi : pieces) {
                                    if (firstID == pi.getID()) {
                                        jp.setBorder(new LineBorder(Utility.Color.green, 4));
                                        firstField = jp;
                                        firstPiece = pi;
                                        isFirstField = !isFirstField;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (Entry<Integer, JPanel> entry : fields.entrySet()) {
                if (entry.getValue().equals(jp)) {
                    int i = entry.getKey(); // id second field
                    for (Piece pi : pieces) {
                        if (firstID == pi.getID()) {
                            if (getPieceAt(getX(i), getY(i)) == null
                                    || getPieceAt(getX(i), getY(i)).getColor() != firstPiece.getColor()) {
                                firstField.setBorder(null);
                                pi.move(getX(i), getY(i), firstField, entry.getValue());
                                isFirstField = !isFirstField;
                                if (currenColor == Color.WHITE) {
                                    currenColor = Color.BLACK;
                                } else {
                                    currenColor = Color.WHITE;
                                }
                                break;
                            } else {
                                firstField.setBorder(null);
                                firstField = jp;
                                firstPiece = pi;
                                firstID = i;
                                jp.setBorder(new LineBorder(Utility.Color.green, 4));
                            }
                        }
                    }
                }
            }
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
