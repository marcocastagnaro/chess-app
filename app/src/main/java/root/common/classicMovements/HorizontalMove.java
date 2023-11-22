package root.common.classicMovements;

import root.common.Board;
import root.common.Position;
import root.common.Interfaces.MovementValidator;

public class HorizontalMove implements MovementValidator {
    int yPos;
    int yNeg;
    public HorizontalMove(int yPos, int yNeg){
        this.yPos = yPos;
        this.yNeg = yNeg;
    }
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        if (x == 0){
            if (y > 0){
                return y <= yPos;
            } else if (y < 0){
                return Math.abs(y) <= yNeg;
            }
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (oldPos.getY() > newPos.getY()){
            return findObstacle1(oldPos, newPos, board);
        } else if (newPos.getY() > oldPos.getY()) {
            return findObstacle2(oldPos, newPos, board);
        }
        return false;
    }

    private static boolean findObstacle2(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getY()- newPos.getY()); i++){
            if (isHasPiece(oldPos, board, i)){
                return true;
            }
        }
        return false;
    }

    private static boolean isHasPiece(Position oldPos, Board board, int i) {
        return board.hasPiece(board.getBoard()[oldPos.getX()][oldPos.getY() + i]);
    }

    private static boolean findObstacle1(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getY()- newPos.getY()); i++){
            if (isHasPiece(oldPos, board, -i)){
                return true;
            }
        }
        return false;
    }

}
