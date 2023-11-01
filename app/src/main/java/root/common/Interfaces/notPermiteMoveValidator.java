package root.common.Interfaces;

import root.common.Board;
import root.common.Enums.Color;
import root.common.Position;

public interface notPermiteMoveValidator {
    public Board validateMove(Board board1, Color color, Position newPos);
}
