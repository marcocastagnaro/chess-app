package root.common.specialRule;

import org.jetbrains.annotations.NotNull;
import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;
import root.common.Interfaces.SpecialRules;

public class Coronation implements SpecialRules {
    Pieces name;
    Piece piece;
    public Coronation(Pieces name, Piece piece){
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
                return whiteCoronation(newPos, board, piece2);
            }
            else {
                return blackCoronation(newPos, board, piece2);
            }
        }
        return board1;
    }

    @NotNull
    private static Board whiteCoronation(Position newPos, Board board, Piece piece2) {
        board.getBoard()[newPos.getX()][newPos.getY()] = new Position(newPos.getX(), newPos.getY(), piece2);
        return board;
    }

    @NotNull
    private static Board blackCoronation(Position newPos, Board board, Piece piece2) {
        Piece piece3 = new Piece(piece2.getName(), Color.BLACK, piece2.getMovements(), piece2.getId(), false);
        return whiteCoronation(newPos, board, piece3);
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
