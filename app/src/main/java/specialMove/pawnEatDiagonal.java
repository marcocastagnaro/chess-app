package specialMove;

import Chess.Game;
import Chess.Piece;
import Chess.Position;
import Enums.Piecies;
import Interfaces.specialMovementValidator;

import java.util.Objects;

public class pawnEatDiagonal implements specialMovementValidator {
    @Override
    public Game validateMove(Game game, Position position, Position newPos) {
        if (pawnDiagonalMove(position, newPos)){
            if (game.getBoard().tienePieza(newPos)){
                if (!Objects.equals(game.getBoard().getPosition(newPos.getX(), newPos.getY()).getColor(), game.getBoard().getPosition(position.getX(),position.getY()).getColor())){
                    return game.move(newPos, position);
                }
            }
        }
        return game;
    }
    public boolean pawnDiagonalMove (Position oldPos, Position newPos){
        int x = oldPos.getX() - newPos.getX();
        int y = oldPos.getY() - newPos.getY();
        Piece piece = oldPos.getPiece();
        Piece piece2 = newPos.getPiece();
        if (piece.getName() == Piecies.PAWN){
            if (Math.abs(x) == 1 && Math.abs(y) == 1){
                if (piece2 != null){
                    return !Objects.equals(piece2.getColor(), piece.getColor());
                }
            }
        }
        return false;
    }

}
