package root.chess.movements;

import root.common.Board;
import root.common.Game;
import root.common.Interfaces.Validators;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;

import java.util.Objects;

public class CheckValidator implements Validators {
    private Pieces name;

    public CheckValidator(Pieces name) {
        this.name = name;
    }

    public boolean pointTo(Position posAct, Board board){ //devuelve true si apunta a un rey de otro color
        Piece piece = posAct.getPiece();
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x,y);
                if (possiblesCheck(posAct, board, piece, possiblePos)) return true;
            }
        }
        return false;
    }

    private boolean possiblesCheck(Position posAct, Board board, Piece piece, Position possiblePos) {
        if (possiblePos.hasPiece() && possiblePos.getColor() != piece.getColor() && possiblePos.getPiece().getName() == name){
            return piece.moveValidator(posAct, possiblePos, board);
            }
        return false;
    }

    public boolean validateMove(Board board, Color color){ //al hacer el movimiento chequear si esta en jaque.
        for (int x=0; x < board.getRow(); x++){
            for (int y=0; y< board.getColumn(); y++){
                if (findsCheck(board, color, x, y)) return true;
            }
        }
        return false;
    }

    private boolean findsCheck(Board board, Color color, int x, int y) {
        if (board.getPiece (x, y) != null ) {
            if (otherColor(board, color, x, y)) {
                return this.pointTo(board.getPosition(x, y), board);
            }
        }
        return false;
    }

    private static boolean otherColor(Board board, Color color, int x, int y) {
        return !Objects.equals(board.getPiece(x, y).getColor(), color);
    }

    @Override
    public Game validateMove(Game game, Board board1, Color color, Position newPos, Position oldPos) {
        if (validateMove(board1, color)){
            return new Game(board1, game.getChessPlayers(), game.getGameVersion(),game.getCustomizeTurn());
        }
        return game;
    }
}
