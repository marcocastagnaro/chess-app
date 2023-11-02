package root.checkers.movements;

import root.chess.Movements.DiagonalMove;
import root.common.Board;
import root.common.Interfaces.movementValidator;
import root.common.Position;

public class eatMovement implements movementValidator {
    int xpos;
    int xneg;
    DiagonalMove diagonalMove;
    public eatMovement(int xpos, int xneg){
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
        Position delta = oldPos.delta(newPos);
        if(Math.abs(delta.getY()) > 1 && Math.abs(delta.getX()) > 1) {
            if (delta.getX() == xpos) {
                int middleX = oldPos.getX() - delta.getX() / 2;
                int middleY = oldPos.getY() - delta.getY() / 2;
                if (!newPos.hasPiece() && middleY > 0 && middleY < 7 && middleX > 0 && middleX < 7) {
                    Position poss = board.getPosition(middleX, middleY);
                    return validIntermidiatemove(oldPos, poss);
                }
            }
            if (Math.abs(delta.getX()) == xneg) {
                int middleX = oldPos.getX() + delta.getX() / 2;
                int middleY = oldPos.getY() + delta.getY() / 2;
                if (!newPos.hasPiece() && middleY > 0 && middleY < 7 && middleX > 0 && middleX < 7) {
                    Position poss = board.getPosition(middleX, middleY);
                    return validIntermidiatemove(oldPos, poss);
                }
            }
        }
        return true;
    }

    private static boolean validIntermidiatemove(Position oldPos, Position poss) {
        return !(poss.hasPiece() && poss.getColor() != oldPos.getColor());
    }
}

