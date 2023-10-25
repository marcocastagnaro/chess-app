package Chess.Movements;

import Classic.Board;
import Classic.Piece;
import Classic.Position;
import Classic.Enums.Piecies;
import Classic.Interfaces.movementValidator;

public class pawnMovement implements movementValidator {
    int xPos;
    int xNeg;
    int yPos;
    int yNeg;
    movementValidator specialmov;
    int firstMove;

    public pawnMovement (int xPos, int xNeg, int yPos, int yNeg, int firstMove, movementValidator specialmov){
        this.xPos = xPos;
        this.xNeg = xNeg;
        this.yNeg = yNeg;
        this.yPos= yPos;
        this.firstMove = firstMove;
        this.specialmov = specialmov;
    }
    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();

        if (oldPos.getPiece().getName() == Piecies.PAWN) {
            if ( pawnFirstMove(oldPos, newPos) ) {
                return true;
            }
            if (newPos.hasPiece()) {
                if (specialmov.validateMove(oldPos, newPos)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        if (y == 0){
            if (x > 0){
                return x <= xNeg;
            } else if (x < 0){
                return Math.abs(x) <= xPos;
            }
        }
        return false;
    }
    public boolean pawnFirstMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        if (piece.getName() == Piecies.PAWN){
            if (y == 0 && Math.abs(x) == this.firstMove && piece.isFirstMove() || x ==0 && Math.abs(y) == this.firstMove && piece.isFirstMove() ) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (oldPos.getX() > newPos.getX()){
            for (int i=1; i < Math.abs(oldPos.getX()- newPos.getX());i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX() - i][oldPos.getY()])){
                    return true;
                }
            }
        } else if (newPos.getX() > oldPos.getX()) {
            for (int i =1 ; i< Math.abs (oldPos.getX()- newPos.getX()); i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX() + i][oldPos.getY()])){
                    return true;
                }
            }
        }
        if (oldPos.getY() > newPos.getY()){
            for (int i=1; i < Math.abs(oldPos.getY()- newPos.getY());i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX()][oldPos.getY() - i])){
                    return true;
                }
            }
        } else if (newPos.getY() > oldPos.getY()) {
            for (int i =1 ; i< Math.abs (oldPos.getY()- newPos.getY()); i++){
                if (board.tienePieza(board.getBoard()[oldPos.getX()][oldPos.getY() + i])){
                    return true;
                }
            }
        }
        return false;
    }
}
