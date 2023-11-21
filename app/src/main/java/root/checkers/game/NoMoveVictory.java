package root.checkers.game;

import root.common.Board;
import root.common.Player;
import root.common.enums.Color;
import root.common.Interfaces.VictoryValidator;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public class NoMoveVictory implements VictoryValidator {
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
        if (board.hasPiece(posInicial)) {
            Piece piece = board.getPiece(x, y);
            if (piece.getColor() == color){
                return validate_move(board, posInicial, piece);
            }
        }
        return false;
    }

    private static boolean validate_move(Board board, Position posInicial, Piece piece) {
        for (int x1 = 0; x1 < board.getRow(); x1++){
            for (int y1 = 0; y1< board.getColumn(); y1 ++){
                Board board1 = board.copy();
                Position finalPos = board1.getPosition(x1,y1);
                if (piece.moveValidator(posInicial, finalPos, board1)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean validateVictory(List<Player> player, Board board) {
        for (Player player1 : player){
            if (!hasMovements(board, player1.getColor())){
                return true;
            }
        }
        return false;
    }
}
