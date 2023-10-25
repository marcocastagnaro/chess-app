package Checkers.movements;

import Classic.Board;
import Classic.Position;
import Classic.Interfaces.movementValidator;

public class classicMovement implements movementValidator {
    int xpos;
    int xneg;
    public classicMovement(int xpos, int xneg ) {
        this.xpos = xpos;
        this.xneg = xneg;
    }

    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        if (y == 1 || y == -1 && Math.abs(x) == 1) {
            //solo hacer que se pueda mover para adelante
            if (x >0){
                return x ==xpos;
            }
            else{
                return Math.abs(x) == xneg;
            }
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        return board.getPosition(newPos.getX(), newPos.getY()).getPiece() != null;
    }
}
