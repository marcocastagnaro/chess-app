package Checkers.movements;

import Classic.Board;
import Classic.Interfaces.movementValidator;
import Classic.Interfaces.specialMovementValidator;
import Classic.Piece;
import Classic.Position;

public class eatObligateMovement implements specialMovementValidator {

    @Override
    public Board validateMove(Board board1, Position position, Position newPos) {
        Piece pieza = position.getPiece();
        for (int x =0; x < board1.getRow(); x ++){
            for (int y=0; y< board1.getColumn(); y++){
                Position possiblePos = board1.getPosition(x,y);
                int x1 = position.getX() - possiblePos.getX();
                int y1 = position.getY() - possiblePos.getY();
                if (Math.abs(y1) > 1 && Math.abs(x1) > 1) {
                    if (pieza.moveValidator(position, possiblePos, board1)) {
                        Board board2 = board1.copy();
                        board2.getBoard()[x][y] = new Position(x, y, pieza);
                    }
                }
            }
        }
        return board1;
    }
}
