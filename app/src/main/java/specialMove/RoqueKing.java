package specialMove;

import Chess.Board;
import Chess.Game;
import Chess.Piece;
import Chess.Position;
import Enums.Piecies;
import Interfaces.specialMovementValidator;

import javax.management.monitor.GaugeMonitor;

public class RoqueKing implements specialMovementValidator {
    @Override
    public Game validateMove(Game game, Position inicial, Position finalPosition) {
        int y = finalPosition.getY() - inicial.getY();
        int x = finalPosition.getX() - inicial.getX();
        Piece king = game.getBoard().getPosition(inicial.getX(), inicial.getY()).getPiece();

        if (x ==0 && king.getName() == Piecies.KING && Math.abs(y) == 2 && king.isFirstMove()){
            Board board1 = game.getBoard().copy();
            if (y > 0) {
                Board board2 = castleKing(board1, inicial, finalPosition, 1);
                return new Game(game.getChessPlayers(), board2, game.getGameVersion());
            } else {
                Board board2 = castleKing(board1, inicial, finalPosition, -2);
                return new Game(game.getChessPlayers(), board2, game.getGameVersion());
            }
        }

        return game;
    }




    private Board castleKing(Board board, Position inicial, Position finalPosition, int direction) {
        Position towerPosition = new Position(inicial.getX(), finalPosition.getY() + direction + 1);
        Piece tower = board.getPosition(towerPosition.getX(), towerPosition.getY()).getPiece();

        if (tower.getName() == Piecies.ROOK && tower.isFirstMove()){
            Piece newKing = (board.getPosition(inicial.getX(), inicial.getY()).getPiece());

            board.getPosition(inicial.getX(), inicial.getY()).addPiece(null);
            board.getPosition(finalPosition.getX(), finalPosition.getY()).addPiece(newKing);
            if ( direction > 0){
                Position position = new Position(inicial.getX(), finalPosition.getY() - 1);
                board.getPosition(towerPosition.getX(), towerPosition.getY()).addPiece(null);
                board.getPosition(position.getX(), position.getY()).addPiece(tower);
            }
            else {
                Position position = new Position(inicial.getX(), finalPosition.getY() + 1);
                board.getPosition(towerPosition.getX(), towerPosition.getY()).addPiece(null);
                board.getPosition(position.getX(), position.getY()).addPiece(tower);
            }
        }

        return board;
    }
}
