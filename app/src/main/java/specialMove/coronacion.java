package specialMove;

import Chess.Board;
import Chess.Game;
import Chess.Piece;
import Chess.Position;
import Enums.Color;
import Enums.Piecies;
import Interfaces.specialMovementValidator;

public class coronacion implements specialMovementValidator {
    Piecies name;
    Piece piece;
    public coronacion (Piecies name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    @Override
    public Game validateMove(Game game, Position position, Position newPos) {
        if (validateCoronation(position, newPos)){
            Board board = game.getBoard().copy();
            piece.setId(position.getPiece().getId());
            board.getPosition(newPos.getX(), newPos.getY()).addPiece(piece);
            board.getPosition(position.getX(),position.getY()).addPiece(null);
            return new Game(game.getChessPlayers(), board, game.getGameVersion());
        }
        return game;
    }
    public boolean validateCoronation (Position oldPos, Position newPos){
        Piece piece = oldPos.getPiece();
        if (piece.getName() == name){
            if (piece.getColor() == Color.WHITE){
                if (newPos.getX() == 0){
                    return true;
                }
            } else {
                if (newPos.getX() == 7){
                    return true;
                }
            }
        }
        return false;
    }
}
