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
    public List<Position> getLivingPiecies (Board board) {
        List<Position> livingPiecies = new ArrayList<>();
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                if (board.getPiece(x,y) != null) {
                    if (Objects.equals(board.getPiece(x,y).getColor(), color)) {
                        livingPiecies.add(board.getPosition(x,y));
                    }
                }
            }
        }
        return livingPiecies;
    }
    public Color getColor() {
        return color;
    }


    public Board movePiece (Piece piece, Position newPosition, Board board) {
        Position oldPosition = board.getPositionWithPiece(piece);

        return board.movePiece(oldPosition, newPosition, piece);
    }

}
