package Checkers.movements;

import Classic.Board;
import Classic.Interfaces.movementValidator;
import Classic.Position;

public class specialMovementVal implements movementValidator {
    int xpos;
    int xneg;
    public specialMovementVal(int xpos, int xneg){
        this.xpos = xpos;
        this.xneg = xneg;
    }
    //clase para comprobar si puede hacer ek movimiento, devuelve un true pero no modifica nada al board.
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
                    return false;
                }
            } else if (Math.abs(x) == xneg) {
                // Comer hacia abajo (y negativo)
                Position poss = board.getPosition(middleX, middleY);
                if (poss != null && poss.hasPiece() && poss.getColor() != oldPos.getColor()) {
                    return false;
                }
            }
        }

        return true;
    }
}
