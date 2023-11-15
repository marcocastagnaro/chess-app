package root.checkers.movements;

import root.common.classicMovements.DiagonalMove;
import root.common.Board;
import root.common.Interfaces.specialRules;
import root.common.Position;

public class eatMov implements specialRules {
    int xpos;
    int xneg;
    DiagonalMove diagonalMove;
    public eatMov(int xpos, int xneg){
        this.xpos = xpos;
        this.xneg = xneg;
        diagonalMove = new DiagonalMove(xpos, xneg,2,2);
    }
    @Override
    public Board validateMove(Board board, Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        return movementCorrect(oldPos, newPos, board, x, y);
    }

    private Board movementCorrect(Position oldPos, Position newPos, Board board, int x, int y) {

        if (diagonalMove.validateMove(oldPos, newPos)) {
            int middleX = oldPos.getX() - x / 2;
            int middleY = oldPos.getY() - y / 2;

            if (x == xpos && !newPos.hasPiece()) {
                Position poss = board.getPosition(middleX, middleY);
                return moveandeat(oldPos, board, poss, newPos);
            }
            else if (Math.abs(x) == xneg && !newPos.hasPiece()){
                Position poss = board.getPosition(middleX, middleY);
                return moveandeat(oldPos, board, poss, newPos);
            }
        }
        return board;
    }

    private static Board moveandeat(Position oldPos, Board board,Position poss, Position newPos){
        Board board1 = board.copy();
        if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
            board1.move(oldPos, newPos, oldPos.getPiece());
            board1.getBoard()[poss.getX()][poss.getY()] = new Position(poss.getX(), poss.getY(), null);
            board1.getBoard()[oldPos.getX()][oldPos.getY()] = new Position(oldPos.getX(), oldPos.getY(), null);
            return board1;
        }
        return board;
    }
}
