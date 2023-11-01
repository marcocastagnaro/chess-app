package root.checkers.movements;

import root.chess.Movements.DiagonalMove;
import root.common.Board;
import root.common.Interfaces.movementValidator;
import root.common.Position;

public class eatMovement implements movementValidator {
    int xpos;
    int xneg;
    public eatMovement(int xpos, int xneg){
        this.xpos = xpos;
        this.xneg = xneg;
    }
    DiagonalMove diagonalMove = new DiagonalMove(xpos, xneg,2,2);
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
        if (Math.abs(x) == 2 && Math.abs(y) == 2) {
            int middleX = oldPos.getX() - x / 2;
            int middleY = oldPos.getY() - y / 2;

            if (x == xpos && !newPos.hasPiece()) {
                Position poss = board.getPosition(middleX, middleY);
                return moveandeat(oldPos, board, middleX, middleY, poss);
            }
            else if (Math.abs(x) == xneg && !newPos.hasPiece()){
                Position poss = board.getPosition(middleX, middleY);
                return moveandeat(oldPos, board, middleX, middleY, poss);
            }
        }
        return false;
    }

    private static boolean moveandeat(Position oldPos, Board board, int middleX, int middleY, Position poss) {
        if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
            board.getBoard()[middleX][middleY] = new Position(middleX, middleY, null);
            return true;
        }
        return false;
    }
}
