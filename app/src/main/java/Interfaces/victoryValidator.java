package Interfaces;

import Chess.Board;
import Chess.ChessPlayer;

import java.util.List;

public interface victoryValidator {
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board);
}
