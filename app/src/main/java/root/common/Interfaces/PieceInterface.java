package root.common.Interfaces;

import root.common.Board;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;

public interface PieceInterface {
    public Color getColor();
    public boolean moveValidator(Position pos, Position fin, Board board);
    public Pieces getName();
}
