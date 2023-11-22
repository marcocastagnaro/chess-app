package root.common.classicMovements;

import root.common.Board;
import root.common.Position;
import root.common.Interfaces.MovementValidator;

public class StraightMove implements MovementValidator {
    int xPos;
    int xNeg;
    public StraightMove(int xPos, int xNeg){
        this.xPos = xPos;
        this.xNeg = xNeg;
    }
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        if (y == 0){
            if (x > 0){
                return x <= xNeg;
            } else if (x < 0){
                return Math.abs(x) <= xPos;
            }
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
         if (oldPos.getX() > newPos.getX()){
             return findObstacleDown(oldPos, newPos, board);
         } else if (newPos.getX() > oldPos.getX()) {
             return findObstacleUp(oldPos, newPos, board);
         }
        return false;
    }

    private static boolean findObstacleUp(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i< Math.abs (oldPos.getX()- newPos.getX()); i++){
            if (isHasPiece(oldPos, board, i)){
                return true;
            }
        }
        return false;
    }

    private static boolean isHasPiece(Position oldPos, Board board, int i) {
        return board.hasPiece(board.getBoard()[oldPos.getX() + i][oldPos.getY()]);
    }

    private static boolean findObstacleDown(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX()- newPos.getX()); i++){
            if (isHasPiece(oldPos, board, -i)){
                return true;
            }
        }
        return false;
    }
}
