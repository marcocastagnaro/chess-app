package root.checkers.movements;

import root.common.classicMovements.DiagonalMove;
import root.common.Board;
import root.common.Interfaces.MovementValidator;
import root.common.Position;

public class EatMovement implements MovementValidator {
    int xpos;
    int xneg;
    DiagonalMove diagonalMove;
    public EatMovement(int xpos, int xneg){
        this.xpos = xpos;
        this.xneg = xneg;
        diagonalMove = new DiagonalMove(xpos, xneg,2,2);
    }
    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        return diagonalMove.validateMove(oldPos, newPos);
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        return !movementCorrect(oldPos, newPos, board, x, y);
    }

    private boolean movementCorrect(Position oldPos, Position newPos, Board board, int x, int y) {
        int middleX = oldPos.getX() - x / 2;
        int middleY = oldPos.getY() - y / 2;

        if (moveForward(newPos, x)) {
            Position poss = board.getPosition(middleX, middleY);
            return moveandeat(oldPos, board, middleX, middleY, poss);
        }
        else if (moveBackwords(newPos, x)) {
            Position poss = board.getPosition(middleX, middleY);
            return moveandeat(oldPos, board, middleX, middleY, poss);
        }
        return false;
    }

    private boolean moveBackwords(Position newPos, int x) {
        return Math.abs(x) == xneg && !newPos.hasPiece();
    }

    private boolean moveForward(Position newPos, int x) {
        return x == xpos && !newPos.hasPiece();
    }

    private static boolean moveandeat(Position oldPos, Board board, int middleX, int middleY, Position poss) {
        if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
            board.getBoard()[middleX][middleY] = new Position(middleX, middleY, null);
            return true;
        }
        return false;
    }
}
