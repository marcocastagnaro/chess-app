package Chess;

import Movements.movementValidator;

import java.util.ArrayList;
import java.util.List;

import static Chess.Piecies.KING;

public class Piece {
    private Piecies name;
    private Color color;
    private List<movementValidator> movements;
    private String id;

    private boolean firstMove;
    public Piece(Piecies name, Color color, List<movementValidator> movements, String id) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        firstMove= true;
        this.id = id;
    }
    public Piecies getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    public boolean isFirstMove() {
        return firstMove;
    }
    public boolean moveValidator(Position pos, Position fin){
        for (int i=0; i < movements.toArray().length; i++){
            if (movements.get(i).validateMove(pos, fin)){
                return true;
            }
        }
        return false;
    }
    public boolean obstacle(Position oldPos, Position newPos, Board board){
        for (int i=0; i< movements.toArray().length; i++){
            if (movements.get(i).obstacle(oldPos, newPos, board)){
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public List<movementValidator> getMovements() {
        return movements;
    }

    public boolean pointTo(Position posAct, Board board){ //devuelve true si apunta a un rey de otro color
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Position possiblePos = board.getPosition(x,y);
                if (moveValidator(posAct, possiblePos) && !obstacle(posAct, possiblePos, board) && possiblePos.getPiece() != null){
                    if (possiblePos.getColor() != color && possiblePos.getPiece().getName() == Piecies.KING){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
