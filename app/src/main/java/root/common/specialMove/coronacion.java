package root.common.specialMove;

import root.chess.Game;
import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Piecies;
import root.common.Interfaces.specialMovementValidator;
import org.jetbrains.annotations.NotNull;

public class coronacion implements specialMovementValidator {
    Piecies name;
    Piece piece;
    public coronacion (Piecies name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    @Override
    public Board validateMove(Board board1, Position position, Position newPos) {
        Piece piece1 = piece.copy();
        Board board2 = board1.copy();
        if (position.getPiece().moveValidator(position,newPos,board2)) {
            if (validateCoronation(position, newPos)) {
                Board board = board1.copy();
                Piece piece2 = new Piece(piece1.getName(), piece1.getColor(), piece1.getMovements(), position.getPiece().getId(), false);
                if (position.getColor() == Color.WHITE) {
                    return board.move(position, newPos, piece2);
                } else {
                    Piece piece3 = new Piece(piece2.getName(), Color.BLACK, piece2.getMovements(), piece2.getId(), false);
                    return board.move(position, newPos, piece3);
                }
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
