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
    MovementValidator eatMov;
    int firstMove;

    public PawnMovement(int xPos, int xNeg, int yPos, int yNeg, int firstMove, MovementValidator specialmov){
        this.xPos = xPos;
        this.xNeg = xNeg;
        this.yNeg = yNeg;
        this.yPos= yPos;
        this.firstMove = firstMove;
        this.eatMov = specialmov;
    }
    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
       Position delta = oldPos.delta(newPos);

        if (isAPawn(oldPos)) {
            if (firstmove(oldPos, newPos)) return true;
            if (newPos.hasPiece()) {
                return eatMov.validateMove(oldPos, newPos);
            }
        }
        if (delta.getY() == 0){
            if (delta.getX() > 0){
                return moveForward(delta);
            }
            else if (delta.getX() < 0){
                return moveBackwards(delta);
            }
        }
        return false;
    }

    private boolean moveBackwards(Position delta) {
        return Math.abs(delta.getX()) <= xPos;
    }

    private boolean moveForward(Position delta) {
        return delta.getX() <= xNeg;
    }

    private boolean firstmove(Position oldPos, Position newPos) {
        return pawnFirstMove(oldPos, newPos);
    }

    private static boolean isAPawn(Position oldPos) {
        return oldPos.getPiece().getName() == Pieces.PAWN;
    }

    public boolean pawnFirstMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        if (piece.getName() == Pieces.PAWN){
            return validFirstMove(newPos, x, y, piece);
        }
        return false;
    }

    private boolean validFirstMove(Position newPos, int x, int y, Piece piece) {
        return y == 0 && Math.abs(x) == this.firstMove && piece.isFirstMove() && !newPos.hasPiece();
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        boolean obstacle = false;
        if (oldPos.getX() > newPos.getX()){
            if (findObstacleDown(oldPos, newPos, board)) return obstacle;
        } else if (newPos.getX() > oldPos.getX()) {
            if (findobstacleUp(oldPos, newPos, board)) return true;
        }if (oldPos.getY() > newPos.getY()){
            return findObstacleLeft(oldPos, newPos, board);
        } else if (newPos.getY() > oldPos.getY()) {
            return findobstacleRight(oldPos, newPos, board);
        }return false;
    }

    private static boolean findobstacleRight(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getY()- newPos.getY()); i++){
            if (hasPieceY(oldPos, board, i)){
                return true;
            }
        }
        return false;
    }

    private static boolean hasPieceY(Position oldPos, Board board, int i) {
        return board.hasPiece(board.getBoard()[oldPos.getX()][oldPos.getY() + i]);
    }

    private static boolean findObstacleLeft(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getY()- newPos.getY()); i++){
            if (hasPieceY(oldPos, board, -i)){
                return true;
            }
        }
        return false;
    }

    private static boolean findobstacleUp(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getX()- newPos.getX()); i++){
            if (hasPieceX(oldPos, board, i)){
                return true;
            }
        }
        return false;
    }

    private static boolean hasPieceX(Position oldPos, Board board, int i) {
        return board.hasPiece(board.getBoard()[oldPos.getX() + i][oldPos.getY()]);
    }

    private static boolean findObstacleDown(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX()- newPos.getX()); i++){
            if (hasPieceX(oldPos, board, -i)){
                return true;
            }
        }
        return false;
    }
}
