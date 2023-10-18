package Interfaces;

import Chess.Board;
import Chess.Position;
import Enums.Color;
import Enums.Piecies;

import java.util.List;

public interface pieceInterface {
    public Color getColor();
    public List<movementValidator> getMovements();
    public boolean moveValidator(Position pos, Position fin, Board board);
    public Piecies getName();
}
