package Movements;

import Chess.Board;
import Chess.Piece;
import Chess.Position;
import Enums.Piecies;
import Interfaces.movementValidator;
import Interfaces.specialMovementValidator;

import java.util.Objects;

public class pawnMovement implements movementValidator {
    int xPos;
    int xNeg;
    int yPos;
    int yNeg;
    specialMovementValidator specialmov;
    int firstMove;

    public pawnMovement (int xPos, int xNeg, int yPos, int yNeg, int firstMove, specialMovementValidator specialmov){
        this.xPos = xPos;
        this.xNeg = xNeg;
        this.yNeg = yNeg;
        this.yPos= yPos;
        this.firstMove = firstMove;
        this.specialmov = specialmov;
    }
    @Override
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
    public boolean pawnFirstMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        if (piece.getName() == Piecies.PAWN){
            if (y == 0 && Math.abs(x) == this.firstMove && piece.isFirstMove() || x ==0 && Math.abs(y) == this.firstMove && piece.isFirstMove() ) {
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
}
