package Interfaces;

import Chess.Board;
import Chess.Piece;
import Chess.Position;

import java.util.List;

public interface boardInterface {
    public Board movePiece(Position oldPosition, Position newPosition, Piece piece);
    public int getRow();

    public int getColumn();
    public List<Piece> getPiecies();

}
