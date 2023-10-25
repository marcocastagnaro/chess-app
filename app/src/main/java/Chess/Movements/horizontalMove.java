package Chess.Movements;

import Classic.Board;
import Classic.Position;
import Classic.Interfaces.movementValidator;

public class horizontalMove implements movementValidator {
    int yPos;
    int yNeg;
    public horizontalMove (int yPos, int yNeg){
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
