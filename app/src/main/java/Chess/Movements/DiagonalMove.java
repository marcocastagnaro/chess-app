package Chess.Movements;

import Classic.Board;
import Classic.Position;
import Classic.Interfaces.movementValidator;

public class DiagonalMove implements movementValidator {
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
        if (Math.abs(x) == Math.abs(y) && x <= xPos && x >= - xNeg && y <= yPos && y >= - yNeg) {
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
