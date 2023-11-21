package root.common.classicMovements;

import root.common.Board;
import root.common.Position;
import root.common.Interfaces.MovementValidator;

public class DiagonalMove implements MovementValidator {
    int xPos;
    int xNeg;
    int yPos;
    int yNeg;

    public DiagonalMove(int xPos, int xNeg, int yPos, int yNeg) {
        this.xPos = xPos;
        this.xNeg = xNeg;
        this.yPos = yPos;
        this.yNeg = yNeg;
    }

    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX(); //para la izq = neg para la der = pos
        int y = oldPos.getY() - newPos.getY(); //para abajo = pos para arriba neg
        return Math.abs(x) == Math.abs(y) && x <= xPos && x >= -xNeg && y <= yPos && y >= -yNeg;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (oldPos.getX() > newPos.getX() && oldPos.getY() > newPos.getY()) { //arriba izq
            return findObstacleLeftUp(oldPos, newPos, board);
        }
        else if (oldPos.getX() > newPos.getX() && oldPos.getY() < newPos.getY()) { //arriba der
            return findObstacleRightUp(oldPos, newPos, board);
        } else if (oldPos.getX() < newPos.getX() && oldPos.getY() > newPos.getY()) { //abajo izq
            return finObstacleLeftDown(oldPos, newPos, board);
        } else if (oldPos.getX() < newPos.getX() && oldPos.getY() < newPos.getY()) { //abajo der
            return findObstacleRightDown(oldPos, newPos, board);
        }
        return false;
    }

    private static boolean findObstacleRightDown(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
            if (board.hasPiece(board.getBoard()[oldPos.getX() + i][oldPos.getY()+ i])){
                return true;
            }
        }
        return false;
    }

    private static boolean finObstacleLeftDown(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
            if (board.hasPiece(board.getBoard()[oldPos.getX() + i][oldPos.getY() - i])){
                return true;
            }
        }
        return false;
    }

    private static boolean findObstacleRightUp(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
            if (board.hasPiece(board.getBoard()[oldPos.getX() - i][oldPos.getY() + i])){
                return true;
            }
        }
        return false;
    }

    private static boolean findObstacleLeftUp(Position oldPos, Position newPos, Board board) {
        for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
            if (board.hasPiece(board.getBoard()[oldPos.getX() - i][oldPos.getY() - i])){
                return true;
            }
        }
        return false;
    }
}
