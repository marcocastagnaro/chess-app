package Movements;

import Chess.Board;
import Chess.Piece;
import Chess.Piecies;
import Chess.Position;

import java.util.Objects;

public class straightMove implements movementValidator{
    int xPos;
    int xNeg;
    public straightMove (int xPos, int xNeg){
        this.xPos = xPos;
        this.xNeg = xNeg;
    }
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        if (oldPos.getPiece().getName() == Piecies.PAWN) {
            if (pawnDiagonalMove(oldPos, newPos) || pawnFirstMove(oldPos, newPos)) {
                return true;
            }
        }
        if (y == 0){
            if (x > 0){
                return x <= xNeg;
            } else if (x < 0){
                return Math.abs(x) <= xPos;
            }
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
         if (oldPos.getX() > newPos.getX()){
            for (int i=1; i < Math.abs(oldPos.getX()- newPos.getX());i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX() - i][oldPos.getY()])){
                    return true;
                }
            }
        } else if (newPos.getX() > oldPos.getX()) {
            for (int i =1 ; i< Math.abs (oldPos.getX()- newPos.getX()); i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX() + i][oldPos.getY()])){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pawnFirstMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        if (piece.getName() == Piecies.PAWN){
            if (y == 0 && Math.abs(x) == 2 && piece.isFirstMove() ) {
                return true;
            }
        }
        return false;
    }
    public boolean pawnDiagonalMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        Piece piece2 = newPos.getPiece();
        if (piece.getName() == Piecies.PAWN){
            if (Math.abs(x) == 1 && Math.abs(y) == 1){
                if (piece2 != null){
                    return !Objects.equals(piece2.getColor(), piece.getColor());
                }
            }
        }
        return false;
    }
}
