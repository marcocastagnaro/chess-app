package root.common.Interfaces;

import root.common.Piece;
import root.common.Position;

import java.util.List;

public interface Board {
    public root.common.Board movePiece(Position oldPosition, Position newPosition, Piece piece);
    public int getRow();

    public int getColumn();
    public List<Piece> getPiecies();

}
