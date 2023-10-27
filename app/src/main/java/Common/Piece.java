package Common;

import Common.Enums.Color;
import Common.Enums.Piecies;
import Common.Interfaces.movementValidator;
import Common.Interfaces.pieceInterface;

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
    public Piece(Piecies name, Color color, List<movementValidator> movements, String id, boolean firstMove) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        this.id = id;
        this.firstMove = firstMove;
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

    public Piece copy (){
        return new Piece(name, color, movements);
    }
}
