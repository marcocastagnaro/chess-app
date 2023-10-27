package Checkers.movements;

import Common.Board;
import Common.Interfaces.specialMovementValidator;
import Common.Piece;
import Common.Position;

public class specialMovementVal implements specialMovementValidator{

    //clase para comprobar si puede hacer ek movimiento, devuelve un true pero no modifica nada al board.
    public Board validateMove(Board board1, Position position, Position newPos) {

            Board board2 = board1.copy();
            Piece pieza = position.getPiece();
            if (pieza.moveValidator(position, newPos, board2)) {
                return board2;
            }
            return board1;
        }
}
