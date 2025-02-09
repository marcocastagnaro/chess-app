package root.common.victory;

import root.common.Board;
import root.common.Player;
import root.common.Piece;
import root.common.enums.Color;
import root.common.Interfaces.VictoryValidator;

import java.util.List;

public class EatAllPiecies implements VictoryValidator {
    public boolean hasPiecies (Board board, Color color){
        for (int x =0; x<board.getRow(); x ++){
            for (int y =0; y<board.getColumn(); y++){
                Piece piece = board.getPiece(x,y);
                if (equalsColor(color, piece)){
                        return true;
                    }
            }
        }
        return false;
    }

    private static boolean equalsColor(Color color, Piece piece) {
        return piece != null && piece.getColor() == color;
    }

    @Override
    public boolean validateVictory(List<Player> player, Board board) {
        for (Player player1 : player){
            if (!hasPiecies(board, player1.getColor())){
                return true;
            }
        }
        return false;
    }
}
