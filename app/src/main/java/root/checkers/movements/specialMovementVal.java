package root.checkers.movements;

import root.common.Board;
import root.common.Interfaces.specialMovementValidator;
import root.common.Piece;
import root.common.Position;

public class specialMovementVal implements specialMovementValidator{
    int xPos;
    int xNeg;
    int intXpos;
    int intxNeg;
    public specialMovementVal(int xPos, int xNeg){
        this.xPos = xPos;
        this.xNeg = xNeg;
    }
    //clase para comprobar si puede hacer ek movimiento, devuelve un true pero no modifica nada al board.
    public Board validateMove(Board board1, Position position, Position newPos) {
            Position delta = position.delta(newPos);
            Board board2 = board1.copy();
            Piece piece = position.getPiece();
        rightDirection(piece);
        eatMovement eat = new eatMovement(intXpos, intxNeg);
            if (eat.validateMove(position, newPos) && !eat.obstacle(position, newPos, board2)) {
                int middleX = position.getX() - delta.getX() / 2;
                int middleY = position.getY() - delta.getY() / 2;
                Board newTablero = board1.copy();
                newTablero.getBoard()[middleX][middleY] = new Position(middleX,middleY);
                return newTablero;
            }
            return board1;
        }

    private void rightDirection(Piece piece) {
        if (piece.getColor() == root.common.Enums.Color.WHITE) {
            intxNeg = 0;
            intXpos = xPos;
        }
        else {
            intxNeg = xNeg;
            intXpos = 0;
        }
    }
}
