package Checkers.movements;

import Classic.Board;
import Classic.Piece;
import Classic.Position;

public class obligatoryMovement {
    public static boolean validateMovement(Board board){
        for (int i =0; i < board.getColumn(); i ++){
            for (int j=0 ; j< board.getRow(); j++){
                Position oldPos = board.getPosition(i,j);
                if (oldPos.hasPiece()) {
                    Piece piece = oldPos.getPiece();
                    Board newTablero = board.move(oldPos, new Position(oldPos.getX() + 2, oldPos.getY() + 2, null), piece);
                    if (newTablero != board) {
                        return true;
                    }
                    newTablero = board.move(oldPos, new Position(oldPos.getX() - 2, oldPos.getY() + 2, null), piece);
                    if (newTablero != board) {
                        return true;
                    }
                    newTablero = board.move(oldPos, new Position(oldPos.getX() + 2, oldPos.getY() - 2, null), piece);
                    if (newTablero != board) {
                        return true;
                    }
                    newTablero = board.move(oldPos, new Position(oldPos.getX() - 2, oldPos.getY() - 2, null), piece);
                    if (newTablero != board) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
