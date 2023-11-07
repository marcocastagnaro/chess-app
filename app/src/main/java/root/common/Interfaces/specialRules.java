package root.common.Interfaces;

import root.common.Board;
import root.common.Position;

public interface specialRules {
    public Board validateMove(Board board1, Position position, Position newPos);
}
