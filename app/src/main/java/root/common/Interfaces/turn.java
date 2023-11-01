package root.common.Interfaces;

import root.common.ChessPlayer;

import java.util.List;

public interface turn {
    public void nextTurn(List<ChessPlayer> chessPlayers);
    public ChessPlayer getCurrent(List<ChessPlayer> chessPlayers);

}
