package root.checkers.game;

import javafx.geometry.Pos;
import root.common.Board;
import root.common.ChessPlayer;
import root.common.Enums.Color;
import root.common.Interfaces.victoryValidator;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public class noMoveVictory implements victoryValidator {
    public boolean hasMovements (Board board, Color color) {
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position posInicial = board.getPosition(x,y);
                if (findMov(board, color, x, y, posInicial)) return true;
            }
        }
        return false;
    }


    private static boolean findMov(Board board, Color color, int x, int y, Position posInicial) {
        if (board.tienePieza(posInicial)) {
            Piece piece = board.getPiece(x, y);
            if (piece.getColor() == color){
                for (int x1 = 0; x1 < board.getRow(); x1++){
                    for (int y1 = 0; y1< board.getColumn(); y1 ++){
                        Board board1 = board.copy();
                        Position finalPos = board1.getPosition(x1,y1);
                        if (piece.moveValidator(posInicial, finalPos, board1)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        for (ChessPlayer chessPlayer1: chessPlayer){
            if (!hasMovements(board, chessPlayer1.getColor())){
                return true;
            }
        }
        return false;
    }
}
