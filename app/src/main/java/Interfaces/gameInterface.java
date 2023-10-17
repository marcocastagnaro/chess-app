package Interfaces;

import Chess.Board;
import Chess.Game;
import Chess.Piece;
import Chess.Position;

import java.util.List;

public interface gameInterface {
    public List<Piece> getPiecies ();
    public Board getBoard();
    public Game move(Position oldPos, Position newpos);
}
