package Common.Interfaces;

import Common.Board;
import Common.Piece;
import Common.Position;

import java.util.List;

public interface boardInterface {
    public Board movePiece(Position oldPosition, Position newPosition, Piece piece);
    public int getRow();

    public int getColumn();
    public List<Piece> getPiecies();

}
