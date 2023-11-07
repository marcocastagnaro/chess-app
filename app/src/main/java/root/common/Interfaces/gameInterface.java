package root.common.Interfaces;

import root.common.Game;
import root.common.Board;
import root.common.ChessPlayer;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public interface gameInterface {
    public List<Piece> getPiecies ();
    public Board getBoard();
    public Game move(Position oldPos, Position newpos);
    public List<ChessPlayer> getChessPlayers();
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}
