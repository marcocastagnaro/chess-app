package Movements;

import Chess.Board;
import Chess.Piece;
import Enums.Piecies;
import Chess.Position;
import Interfaces.movementValidator;

import java.util.Objects;

public class straightMove implements movementValidator {
    int xPos;
    int xNeg;
    public straightMove (int xPos, int xNeg){
        this.xPos = xPos;
        this.xNeg = xNeg;
    }
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
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
}
