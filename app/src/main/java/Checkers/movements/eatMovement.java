package Checkers.movements;

import Common.Board;
import Common.Interfaces.movementValidator;
import Common.Position;

public class eatMovement implements movementValidator {
    int xpos;
    int xneg;
    public eatMovement(int xpos, int xneg){
        this.xpos = xpos;
        this.xneg = xneg;
    }
    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        if (Math.abs(x) == Math.abs(y)){
            if (x>0){
                return x == xpos;
            }
            else {
                return Math.abs(x) == xneg;
            }
        }
        return false;
    }
    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        if (movementCorrect(oldPos, newPos, board, x, y)) return false;

        return true;
    }

    private boolean movementCorrect(Position oldPos, Position newPos, Board board, int x, int y) {
        if (Math.abs(x) == 2 && Math.abs(y) == 2) {
            int middleX = oldPos.getX() - x / 2;
            int middleY = oldPos.getY() - y / 2;

            if (x == xpos && !newPos.hasPiece()) {
                Position poss = board.getPosition(middleX, middleY);
                if (moveandeat(oldPos, board, middleX, middleY, poss)) return true;
            }
            else if (Math.abs(x) == xneg && !newPos.hasPiece()){
                Position poss = board.getPosition(middleX, middleY);
                if (moveandeat(oldPos, board, middleX, middleY, poss)) return true;
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
