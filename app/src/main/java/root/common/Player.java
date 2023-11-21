package root.common;

import root.common.enums.Color;

public class Player {
    private final String name;
    boolean turn = false;
    Color color;
    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
    }
    public Player(String name, Color color, boolean turn) {
        this.color = color;
        this.name = name;
        this.turn = turn;
    }
    public String getName() {
        return name;
    }
    public boolean getTurn(){
        return turn;
    }
    public Player changeTurn(){
        return new Player(name, color, !turn);
    }
    public Color getColor() {
        return color;
    }


    public Board movePiece (Position oldPos, Position newPosition, Board board) {
        Piece piece = oldPos.getPiece();
        return board.movePiece(oldPos, newPosition, piece);
    }

}
