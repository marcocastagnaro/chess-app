package root.common.Interfaces;

import root.common.Board;
import root.common.ChessPlayer;

import java.util.List;

public interface victoryValidator {
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}
