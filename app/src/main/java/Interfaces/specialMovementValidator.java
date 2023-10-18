package Interfaces;

import Chess.Board;
import Chess.Game;
import Chess.Position;

public interface specialMovementValidator {
    public Game validateMove(Game game, Position position, Position newPos);
}
