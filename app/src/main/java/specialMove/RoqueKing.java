package specialMove;

import Chess.Board;
import Chess.Game;
import Chess.Piece;
import Chess.Position;
import Enums.Piecies;
import Interfaces.movementValidator;
import Interfaces.specialMovementValidator;

import javax.management.monitor.GaugeMonitor;

public class RoqueKing implements specialMovementValidator {
    @Override
    public Game validateMove(Game game, Position inicial, Position finalPosition) {
        int y = finalPosition.getY() - inicial.getY();
        int x = finalPosition.getX() - inicial.getX();
        Piece king = game.getBoard().getPosition(inicial.getX(), inicial.getY()).getPiece();
        if (game.getGameVersion().getCheckval().isInCheck(game.getBoard(), king.getColor())){
            return game;
        }
        for (movementValidator mov : king.getMovements()){
            if(mov.obstacle(inicial, finalPosition, game.getBoard())) {
                return game;
            }
        }
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

            board.getBoard()[inicial.getX()][inicial.getY()] = new Position(inicial.getX(), inicial.getY());
            board.getBoard()[finalPosition.getX()][finalPosition.getY()] = new Position(finalPosition.getX(), finalPosition.getY(), newKing);
            if ( direction > 0){
                Position position = new Position(inicial.getX(), finalPosition.getY() - 1);
                board.getBoard()[towerPosition.getX()][towerPosition.getY()] = new Position(towerPosition.getX(), towerPosition.getY());
                board.getBoard()[position.getX()][position.getY()] = new Position(position.getX(), position.getY(), tower);
            }
            else {
                Position position = new Position(inicial.getX(), finalPosition.getY() + 1);
                board.getBoard()[towerPosition.getX()][towerPosition.getY()] = new Position(towerPosition.getX(), towerPosition.getY());
                board.getBoard()[position.getX()][position.getY()] = new Position(position.getX(), position.getY(), tower);
            }
        }

        return board;
    }
}
