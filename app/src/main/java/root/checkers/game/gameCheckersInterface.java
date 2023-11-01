package root.checkers.game;

import root.common.Board;
import root.common.ChessPlayer;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public interface gameCheckersInterface {
    public List<Piece> getPiecies ();
    public Board getBoard();
    public gameCheckersInterface move(Position oldPos, Position newpos);
    public List<ChessPlayer> getChessPlayers();
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}


