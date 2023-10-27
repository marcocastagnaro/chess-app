package Checkers.game;

import Common.Board;
import Common.ChessPlayer;
import Common.Piece;
import Common.Position;

import java.util.List;

public interface gameCheckersInterface {
    public List<Piece> getPiecies ();
    public Board getBoard();
    public gameCheckersInterface move(Position oldPos, Position newpos);
    public List<ChessPlayer> getChessPlayers();
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}


