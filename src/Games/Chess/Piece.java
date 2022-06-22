package Games.Chess;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUIs.ErrorNote;

public abstract class Piece {

    protected int x, y, ID;
    protected Type type;
    protected Color color;
    protected ImageIcon image;

    public Piece(int x, int y, Type type, Color color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = color;
        this.ID = Integer.valueOf(String.valueOf(x) + String.valueOf(y));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public int getID() {
        return ID;
    }

    public ImageIcon getImage() {
        return image;
    }

    public abstract boolean possibleMove(int x, int y);

    public void move(int x, int y, JPanel startField, JPanel targetField) {
        if (x > 0 && x <= 8 && y <= 8 && y > 0) {
            if (Chess.getPieceAt(x, y) == null) {
                if (possibleMove(x, y)) {
                    setX(x);
                    setY(y);
                    for (Piece p : Chess.pieces) {
                        if (p.getID() == Integer.valueOf(String.valueOf(x) + String.valueOf(y))) {
                            Chess.deadPieces.add(p);
                            Chess.pieces.remove(p);
                            break;
                        }
                    }
                    setID(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
                    startField.removeAll();
                    targetField.add(new JLabel(getImage()));
                } else {
                    new ErrorNote("Can't go there with " + getType() + ".");
                }
            } else if (Chess.getPieceAt(x, y).getColor() != getColor()) {
                if (possibleMove(x, y)) {
                    //if (Chess.getPieceAt(x, y).getType() == Type.KING) {
                    //    new ErrorNote(Chess.getPieceAt(x, y).getColor() + " hat verloren.");
                    //}
                    Chess.deadPieces.add(Chess.getPieceAt(x, y));
                    Chess.pieces.remove(Chess.getPieceAt(x, y));
                    Chess.getFieldAt(x, y).removeAll();
                    setX(x);
                    setY(y);
                    for (Piece p : Chess.pieces) {
                        if (p.getID() == Integer.valueOf(String.valueOf(x) + String.valueOf(y))) {
                            Chess.deadPieces.add(p);
                            Chess.pieces.remove(p);
                            break;
                        }
                    }
                    setID(Integer.valueOf(String.valueOf(x) + String.valueOf(y)));
                    startField.removeAll();
                    targetField.add(new JLabel(getImage()));
                } else {
                    new ErrorNote("Can't go there with " + getType() + ".");
                }
            }

        } else {
            new ErrorNote("Not a field.");
        }
    }

}

class King extends Piece {

    public King(int x, int y, Color color) {
        super(x, y, Type.KING, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/King.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/King.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return Math.abs(getX() - x) <= 1 && Math.abs(getY() - y) <= 1;
    }

}

class Queen extends Piece {

    public Queen(int x, int y, Color color) {
        super(x, y, Type.QUEEN, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/Queen.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/Queen.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return Math.abs(getX() - x) == Math.abs(getY() - y) || (getX() - x == 0 ^ getY() - y == 0);
    }

}

class Bishop extends Piece {

    public Bishop(int x, int y, Color color) {
        super(x, y, Type.BISHOP, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/Bishop.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/Bishop.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return Math.abs(getX() - x) == Math.abs(getY() - y);
    }

}

class Knight extends Piece {

    public Knight(int x, int y, Color color) {
        super(x, y, Type.KNIGHT, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/Knight.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/Knight.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return (Math.abs(getX() - x) == 2 && Math.abs(getY() - y) == 1)
                ^ (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 2);
    }

}

class Rook extends Piece {

    public Rook(int x, int y, Color color) {
        super(x, y, Type.ROOK, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/Rook.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/Rook.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return getX() - x == 0 ^ getY() - y == 0;
    }

}

class Pawn extends Piece {

    boolean facingUp;

    public Pawn(int x, int y, Color color) {
        super(x, y, Type.PAWN, color);
        try {
            if (color == Color.WHITE) {
                image = new ImageIcon(getClass().getResource("img/White/Pawn.png"));
            } else {
                image = new ImageIcon(getClass().getResource("img/Black/Pawn.png"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (color == Color.WHITE) {
            facingUp = true;
        } else {
            facingUp = false;
        }
    }

    @Override
    public boolean possibleMove(int x, int y) {
        if (facingUp) {
            if (getY() == 2 && y - getY() == 2) {
                return true;
            } else if (y - getY() == 1) {
                return true;
            } else if (y - getY() == 1 && Math.abs(x - getX()) == 1
                    && Chess.getPieceAt(x, y).getColor() != getColor()) {
                return true;
            }
        } else {
            if (getY() == 7 && getY() - y == 2) {
                return true;
            } else if (getY() - y == 1) {
                return true;
            } else if (getY() - y == 1 && Math.abs(x - getX()) == 1
                    && Chess.getPieceAt(x, y).getColor() != getColor()) {
                return true;
            }
        }
        return false;
    }

}

enum Type {
    KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN;
}

enum Color {
    WHITE, BLACK;
}
