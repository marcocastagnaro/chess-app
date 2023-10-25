package Classic.Interfaces;

import Chess.Game;
import Classic.Board;
import Classic.Position;

public interface specialMovementValidator {
    public Board validateMove(Board board1, Position position, Position newPos);
}
