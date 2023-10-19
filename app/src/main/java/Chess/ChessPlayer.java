package Chess;

import Enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessPlayer {
    private final String name;
    boolean turn = false;
    Color color;
    public ChessPlayer(String name, Color color) {
        this.color = color;
        this.name = name;
    }
    public boolean pieceBelongsToPlayer (Piece piece){
        return piece.getColor() == color;
    }

    public String getName() {
        return name;
    }
    public boolean getTurn(){
        return turn;
    }
    public void changeTurn(){
        turn = !turn;
    }
    public Color getColor() {
        return color;
    }


    public Board movePiece (Piece piece, Position newPosition, Board board) {
        Position oldPosition = board.getPositionWithPiece(piece);
        return board.movePiece(oldPosition, newPosition, piece);
    }

}
