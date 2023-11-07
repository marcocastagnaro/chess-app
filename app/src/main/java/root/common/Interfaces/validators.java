package root.common.Interfaces;

import root.common.Game;
import root.common.Board;
import root.common.Enums.Color;
import root.common.Position;

public interface validators {
    public Game validateMove(Game game, Board board1, Color color, Position newPos, Position oldPos);
}
