package Movements;

import Chess.Board;
import Chess.Position;
import Interfaces.movementValidator;

public class DiagonalMove implements movementValidator {

    int maxCasillas;

    public DiagonalMove(int maxCasillas) {
        this.maxCasillas = maxCasillas;
    }

    @Override
    public boolean validateMove(Position oldPos, Position newPos) {
        int x = oldPos.getX() - newPos.getX(); //para la izq = neg para la der = pos
        int y = oldPos.getY() - newPos.getY(); //para abajo = pos para arriba neg
        if (Math.abs(x) == Math.abs(y) && Math.abs(x) <= maxCasillas && Math.abs(y) <= maxCasillas) {
            return true;
        }
        return false;
    }

    @Override
    public boolean obstacle(Position oldPos, Position newPos, Board board) {
        if (oldPos.getX() > newPos.getX() && oldPos.getY() > newPos.getY()) { //arriba izq
            for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
                if (board.tienePieza(board.getBoard()[oldPos.getX() - i][oldPos.getY() - i])){
                    return true;
                }
            }
        } else if (oldPos.getX() > newPos.getX() && oldPos.getY() < newPos.getY()) { //arriba der
            for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
                if (board.tienePieza(board.getBoard()[oldPos.getX() - i][oldPos.getY() + i])){
                    return true;
                }
            }
        } else if (oldPos.getX() < newPos.getX() && oldPos.getY() > newPos.getY()) { //abajo izq
            for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
                if (board.tienePieza(board.getBoard()[oldPos.getX() + i][oldPos.getY() - i])){
                    return true;
                }
            }
        } else if (oldPos.getX() < newPos.getX() && oldPos.getY() < newPos.getY()) { //abajo der
            for (int i = 1; i < Math.abs(oldPos.getX() - newPos.getX()); i++) {
                if (board.tienePieza(board.getBoard()[oldPos.getX() + i][oldPos.getY()+ i])){
                    return true;
                }
            }
        }
        return false;
    }
}
