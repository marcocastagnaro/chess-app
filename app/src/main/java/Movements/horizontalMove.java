package Movements;

import Chess.Board;
import Chess.Piece;
import Enums.Piecies;
import Chess.Position;
import Interfaces.movementValidator;

import java.util.Objects;

public class horizontalMove implements movementValidator {
    int yPos;
    int yNeg;
    public horizontalMove (int yPos, int yNeg){
        this.yPos = yPos;
        this.yNeg = yNeg;
    }
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        if (x == 0){
            if (oldPos.getPiece().getName() == Piecies.PAWN){
                if (pawnDiagonalMove(oldPos, newPos) || pawnFirstMove(oldPos, newPos)){
                    return true;
                }
            }
            if (y > 0){
                return y <= yPos;
            } else if (y < 0){
                return Math.abs(y) <= yNeg;
            }
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (oldPos.getY() > newPos.getY()){
            for (int i=1; i < Math.abs(oldPos.getY()- newPos.getY());i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX()][oldPos.getY() - i])){
                    return true;
                }
            }
        } else if (newPos.getY() > oldPos.getY()) {
            for (int i =1 ; i< Math.abs (oldPos.getY()- newPos.getY()); i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX()][oldPos.getY() + i])){
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
            if (x == 0 && Math.abs(y) == 2 && piece.isFirstMove() ) {
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
