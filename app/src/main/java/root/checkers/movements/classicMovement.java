package root.checkers.movements;

import root.common.classicMovements.DiagonalMove;
import root.common.Board;
import root.common.Position;
import root.common.Interfaces.movementValidator;

public class classicMovement implements movementValidator {
    int xpos;
    int xneg;
    DiagonalMove diagonalMove;
    public classicMovement(int xpos, int xneg ) {
        this.xpos = xpos;
        this.xneg = xneg;
        diagonalMove = new DiagonalMove(xpos, xneg,1,1);

    }
    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        return diagonalMove.validateMove(oldPos, newPos);

    }
    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (!newPos.hasPiece()) {
            return diagonalMove.obstacle(oldPos, newPos, board);
        }
        return true;
    }
}
