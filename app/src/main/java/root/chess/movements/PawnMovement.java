package root.chess.movements;

import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Pieces;
import root.common.Interfaces.MovementValidator;

public class PawnMovement implements MovementValidator {
    int xPos;
    int xNeg;
    int yPos;
    int yNeg;
    MovementValidator specialmov;
    int firstMove;

    public PawnMovement(int xPos, int xNeg, int yPos, int yNeg, int firstMove, MovementValidator specialmov){
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

        if (oldPos.getPiece().getName() == Pieces.PAWN) {
            if ( pawnFirstMove(oldPos, newPos) ) {
                return true;
            }
            if (newPos.hasPiece()) {
                return specialmov.validateMove(oldPos, newPos);
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
        if (piece.getName() == Pieces.PAWN){
            if (y == 0 && Math.abs(x) == this.firstMove && piece.isFirstMove() && !newPos.hasPiece() ) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        boolean obstacle = false;
        if (oldPos.getX() > newPos.getX()){
            if (findObstacleDown(oldPos, newPos, board)) return obstacle;
        }
        else if (newPos.getX() > oldPos.getX()) {
            if (findobstacleUp(oldPos, newPos, board)) return true;
        }
        if (oldPos.getY() > newPos.getY()){
            if (findObstacleLeft(oldPos, newPos, board)) return true;
        } else if (newPos.getY() > oldPos.getY()) {
            if (findobstacleRight(oldPos, newPos, board)) return true;
        }
        return false;
    }

    private static boolean findobstacleRight(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getY()- newPos.getY()); i++){
            if (board.hasPiece(board.getBoard()[oldPos.getX()][oldPos.getY() + i])){
                return true;
            }
        }
        return false;
    }

    private static boolean findObstacleLeft(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getY()- newPos.getY()); i++){
            if (board.hasPiece(board.getBoard()[oldPos.getX()][oldPos.getY() - i])){
                return true;
            }
        }
        return false;
    }

    private static boolean findobstacleUp(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getX()- newPos.getX()); i++){
            if (board.hasPiece(board.getBoard()[oldPos.getX() + i][oldPos.getY()])){
                return true;
            }
        }
        return false;
    }

    private static boolean findObstacleDown(Position oldPos, Position newPos, Board board) {
        boolean obstacle;
        for (int i = 1; i < Math.abs(oldPos.getX()- newPos.getX()); i++){
            if (board.hasPiece(board.getBoard()[oldPos.getX() - i][oldPos.getY()])){
                obstacle = true;
                return true;
            }
        }
        return false;
    }
}
