package Chess;

import Enums.Color;
import Enums.Piecies;
import Interfaces.movementValidator;
import Interfaces.pieceInterface;

import java.util.List;

public class Piece implements pieceInterface {
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
    public Piece(Piecies name, Color color, List<movementValidator> movements) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        firstMove= true;
        this.id = String.valueOf(hashCode());
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
    public boolean moveValidator(Position pos, Position fin, Board board){
        for (int i=0; i < movements.toArray().length; i++){
            if (movements.get(i).validateMove(pos, fin)){
                if (!movements.get(i).obstacle(pos, fin, board)) {
                    return true;
                }
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
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Piece copy (){
        return new Piece(name, color, movements);
    }
}
