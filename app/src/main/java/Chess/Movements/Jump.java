package Chess.Movements;

import Classic.Board;
import Classic.Position;
import Classic.Interfaces.movementValidator;

public class Jump implements movementValidator {
    int xmov;
    int ymov; //cuantos movimientos se puede mover en x y cuantos se puede mover en y
  //  int dif;
    public Jump (int xmov, int ymov){
        this.xmov = xmov;
        this.ymov = ymov;
    }

    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
            if (Math.abs(x) == xmov && Math.abs(y) == ymov){
                //if (Math.abs(x) - Math.abs(y) == dif || Math.abs(y) - Math.abs(x) == dif) {
                    return true;
            }
            return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        return false;
    }
}
