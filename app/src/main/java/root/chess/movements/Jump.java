package root.chess.movements;

import root.common.Board;
import root.common.Position;
import root.common.Interfaces.MovementValidator;

public class Jump implements MovementValidator {
    int xmov;
    int ymov; //cuantos movimientos se puede mover en x y cuantos se puede mover en y


    public Jump (int xmov, int ymov){
        this.xmov = xmov;
        this.ymov = ymov;
    }

    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        return Math.abs(x) == xmov && Math.abs(y) == ymov;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        return false;
    }
}
