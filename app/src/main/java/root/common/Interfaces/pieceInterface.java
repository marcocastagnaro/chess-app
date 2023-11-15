package root.common.Interfaces;

import root.common.Board;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Pieces;

public interface pieceInterface {
    public Color getColor();
    public boolean moveValidator(Position pos, Position fin, Board board);
    public Pieces getName();
}
