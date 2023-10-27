package Common.Interfaces;

import Common.Board;
import Common.ChessPlayer;

import java.util.List;

public interface victoryValidator {
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}
