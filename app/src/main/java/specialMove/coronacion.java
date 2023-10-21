package specialMove;

import Chess.Board;
import Chess.Game;
import Chess.Piece;
import Chess.Position;
import Enums.Color;
import Enums.Piecies;
import Interfaces.specialMovementValidator;
import org.jetbrains.annotations.NotNull;

public class coronacion implements specialMovementValidator {
    Piecies name;
    Piece piece;
    public coronacion (Piecies name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    @Override
    public Game validateMove(Game game, Position position, Position newPos) {
        Piece piece1 = piece.copy();
        if (position.getPiece().moveValidator(position,newPos,game.getBoard())) {
            if (validateCoronation(position, newPos)) {
                Board board = game.getBoard().copy();
                Piece piece2 = new Piece(piece1.getName(), piece1.getColor(), piece1.getMovements(), position.getPiece().getId(), false);
                if (position.getColor() == Color.WHITE) {
                    return getGame(game, position, newPos, board, piece2, Color.BLACK);
                } else {
                    return getGame(game, position, newPos, board, piece2, Color.WHITE);
                }
            }
        }
        return game;
    }

    @NotNull
    private static Game getGame(Game game, Position position, Position newPos, Board board, Piece piece2, Color color) {
        Piece piece3 = new Piece(piece2.getName(), color, piece2.getMovements(), piece2.getId(), false);
        board.getBoard()[newPos.getX()][newPos.getY()] = new Position(newPos.getX(), newPos.getY(), piece3);
        board.getBoard()[position.getX()][position.getY()] = new Position(position.getX(), position.getY());
        return new Game(game.getChessPlayers(), board, game.getGameVersion());
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
