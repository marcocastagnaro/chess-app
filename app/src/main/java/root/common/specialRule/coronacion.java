package root.common.specialRule;

import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Piecies;
import root.common.Interfaces.specialRules;

public class coronacion implements specialRules {
    Piecies name;
    Piece piece;
    public coronacion (Piecies name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    @Override
    public Board validateMove(Board board1, Position position, Position newPos) {
        Piece piece1 = piece.copy();
        if (validateCoronation(position, newPos)) {
            Board board = board1.copy();
            Piece piece2 = new Piece(piece1.getName(), piece1.getColor(), piece1.getMovements(), position.getPiece().getId(), false);
            if (position.getColor() == Color.WHITE) {
                board.getBoard()[newPos.getX()][newPos.getY()] = new Position(newPos.getX(), newPos.getY(), piece2);
                return board;
            } else {
                Piece piece3 = new Piece(piece2.getName(), Color.BLACK, piece2.getMovements(), piece2.getId(), false);
                board.getBoard()[newPos.getX()][newPos.getY()] = new Position(newPos.getX(), newPos.getY(), piece3);
                return board;
            }
        }
        return board1;
    }
    public boolean validateCoronation (Position oldPos, Position newPos){
        Piece piece = oldPos.getPiece();
        if (piece.getName() == name){
            if (piece.getColor() == Color.WHITE){
                return newPos.getX() == 0;
            } else {
                return newPos.getX() == 7;
            }
        }
        return false;
    }
}
