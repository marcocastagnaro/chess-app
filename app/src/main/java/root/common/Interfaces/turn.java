package root.common.Interfaces;

import root.common.Board;
import root.common.ChessPlayer;
import root.common.Position;

import java.util.List;

public interface turn {
    public List<ChessPlayer> nextTurn(List<ChessPlayer> chessPlayers, Board board, Position oldpos, Position newpos);
    public ChessPlayer getCurrent(List<ChessPlayer> chessPlayers);

}
