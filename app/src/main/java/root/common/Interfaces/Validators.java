package root.common.Interfaces;

import root.common.Game;
import root.common.Board;
import root.common.enums.Color;
import root.common.Position;

public interface Validators {
    public Game validateMove(Game game, Board board1, Color color, Position newPos, Position oldPos);
}
