package root.common.victory;

import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Piecies;

import java.util.Objects;

public class checkValidator {
    private Piecies name;

    public checkValidator(Piecies name) {
        this.name = name;
    }

    public boolean pointTo(Position posAct, Board board){ //devuelve true si apunta a un rey de otro color
        Piece piece = posAct.getPiece();
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x,y);
                if (possiblePos.hasPiece()){
                    if (possiblePos.getColor() != piece.getColor() && possiblePos.getPiece().getName() == name){
                        if (piece.moveValidator(posAct, possiblePos, board)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean validateMove(Board board, Color color){ //al hacer el movimiento chequear si esta en jaque.
        for (int x=0; x < board.getRow(); x++){
            for (int y=0; y< board.getColumn(); y++){
                if (board.getPiece (x,y) != null ) {
                    if (!Objects.equals(board.getPiece(x, y).getColor(), color)) {
                        if (this.pointTo(board.getPosition(x,y), board)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
