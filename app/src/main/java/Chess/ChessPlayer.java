package Chess;

import Enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChessPlayer {
    private final String name;
    private boolean check = false;
    boolean turn = false;
    Color color;
    boolean checkmate = false;
    public ChessPlayer(String name, Color color) {
        this.color = color;
        this.name = name;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }
    public boolean pieceBelongsToPlayer (Piece piece){
        return piece.getColor() == color;
    }

    public boolean isCheckmate() {
        return checkmate;
    }

    public void setCheckmate(boolean checkmate) {
        this.checkmate = checkmate;
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

    public boolean isInCheck (Board board){ //al hacer el movimiento chequear si esta en jaque.
        for (int x=0; x < board.getRow(); x++){
            for (int y=0; y< board.getColumn(); y++){
                if (board.getPiece (x,y) != null ) {
                    if (!Objects.equals(board.getPiece(x, y).getColor(), color)) {
                        if (board.getPiece(x,y).pointTo(board.getPosition(x,y), board)) {
                            setCheck(true);
                            return true;
                        }
                    }
                }
            }
        }
        setCheck(false);
        return false;
    }

    public boolean isCheck() {
        return check;
    }
    public Piece findKing (Board board){
        return board.findKing(color);
    }
    public boolean pieceInterceptsCheck (Board board){
        return board.pieceInterceptsCheck(this);
    }
    public Board movePiece (Piece piece, Position newPosition, Board board) {
        Position oldPosition = board.getPositionWithPiece(piece);
        Board board1 = board.movePiece(oldPosition, newPosition, piece);
        if (isInCheck(board1)){
            return board;
        }
        return board1;
        // chequar que al hacer el movimiento el rey no quede en jaque
    }

}
