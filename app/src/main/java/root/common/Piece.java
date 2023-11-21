package root.common;

import root.common.enums.Color;
import root.common.enums.Pieces;
import root.common.Interfaces.MovementValidator;
import root.common.Interfaces.PieceInterface;

import java.util.List;

public class Piece implements PieceInterface {
    private Pieces name;
    private Color color;
    private List<MovementValidator> movements;
    private String id;

    private boolean firstMove;
    public Piece(Pieces name, Color color, List<MovementValidator> movements, String id) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        firstMove= true;
        this.id = id;
    }
    public Piece(Pieces name, Color color, List<MovementValidator> movements) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        firstMove= true;
        this.id = String.valueOf(hashCode());
    }
    public Piece(Pieces name, Color color, List<MovementValidator> movements, String id, boolean firstMove) {
        this.name = name;
        this.color = color;
        this.movements = movements;
        this.id = id;
        this.firstMove = firstMove;
    }
    public Pieces getName() {
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

    public List<MovementValidator> getMovements() {
        return movements;
    }

    public Piece copy (){
        return new Piece(name, color, movements);
    }
}
