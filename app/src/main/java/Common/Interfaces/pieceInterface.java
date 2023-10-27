package Common.Interfaces;

import Common.Board;
import Common.Position;
import Common.Enums.Color;
import Common.Enums.Piecies;

import java.util.List;

public interface pieceInterface {
    public Color getColor();
    public List<movementValidator> getMovements();
    public boolean moveValidator(Position pos, Position fin, Board board);
    public Piecies getName();
}
