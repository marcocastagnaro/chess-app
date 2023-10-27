package Common.Interfaces;

import Common.Board;
import Common.Position;

public interface specialMovementValidator {
    public Board validateMove(Board board1, Position position, Position newPos);
}
