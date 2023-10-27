package Common.Interfaces;

import Common.Board;
import Common.Enums.Color;
import Common.Position;

public interface notPermiteMoveValidator {
    public Board validateMove(Board board1, Color color, Position newPos);
}
