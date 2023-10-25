package Classic.Interfaces;

import Classic.Board;
import Classic.Position;
import Classic.Enums.Color;
import Classic.Enums.Piecies;

import java.util.List;

public interface pieceInterface {
    public Color getColor();
    public List<movementValidator> getMovements();
    public boolean moveValidator(Position pos, Position fin, Board board);
    public Piecies getName();
}
