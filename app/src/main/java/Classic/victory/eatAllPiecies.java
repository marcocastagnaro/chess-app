package Classic.victory;

import Classic.Board;
import Classic.ChessPlayer;
import Classic.Piece;
import Classic.Enums.Color;
import Classic.Interfaces.victoryValidator;

import java.util.List;

public class eatAllPiecies implements victoryValidator {
    public boolean hasPiecies (Board board, Color color){
        for (int x =0; x<board.getRow(); x ++){
            for (int y =0; y<board.getColumn(); y++){
                Piece piece = board.getPiece(x,y);
                if (piece != null){
                    if (piece.getColor() == color){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean validateVictory(List<ChessPlayer> chessPlayer, Board board) {
        for (ChessPlayer chessPlayer1: chessPlayer){
                if (!hasPiecies(board, chessPlayer1.getColor())){
                    return true;
                }
            }
        return false;
    }
}
