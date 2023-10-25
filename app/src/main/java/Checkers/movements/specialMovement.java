package Checkers.movements;

import Classic.Board;
import Classic.Interfaces.movementValidator;
import Classic.Position;

public class specialMovement implements movementValidator {
    int xpos;
    int xneg;
    public specialMovement(int xpos, int xneg){
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

        if (Math.abs(x) == 2 && Math.abs(y) == 2) {
            int middleX = oldPos.getX() - x / 2;
            int middleY = oldPos.getY() - y / 2;

            if (x == xpos) {
                // Comer hacia arriba (y positivo)
                Position poss = board.getPosition(middleX, middleY);
                if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
                    board.getBoard()[middleX][middleY] = new Position(middleX, middleY, null);
                    return false;
                }
            } else if (Math.abs(x) == xneg) {
                // Comer hacia abajo (y negativo)
                Position poss = board.getPosition(middleX, middleY);
                if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
                    board.getBoard()[middleX][middleY] = new Position(middleX, middleY, null);
                    return false;
                }
            }
        }

        return true;
    }
}
