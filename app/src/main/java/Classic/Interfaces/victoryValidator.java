package Classic.Interfaces;

import Classic.Board;
import Classic.ChessPlayer;

import java.util.List;

public interface victoryValidator {
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}
