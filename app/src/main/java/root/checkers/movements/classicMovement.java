package root.checkers.movements;

import root.common.Board;
import root.common.Position;
import root.common.Interfaces.movementValidator;

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

        if ( Math.abs(y) == Math.abs(x)) {
            //solo hacer que se pueda mover para adelante
            return moveVal(x);
        }
        return false;
    }

    private boolean moveVal(int x) {
        if (x >0){
            return x == xpos;
        }
        else{
            return Math.abs(x) == xneg;
        }
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        return board.getPosition(newPos.getX(), newPos.getY()).getPiece() != null;
    }
}
