package Classic.specialMove;

import Classic.Board;
import Chess.Game;
import Classic.Piece;
import Classic.Position;
import Classic.Enums.Piecies;
import Classic.Interfaces.movementValidator;
import Classic.Interfaces.specialMovementValidator;

public class RoqueKing implements specialMovementValidator {
    @Override
    public Board validateMove(Board board, Position inicial, Position finalPosition) {
        int y = finalPosition.getY() - inicial.getY();
        int x = finalPosition.getX() - inicial.getX();
        Piece king = board.getPosition(inicial.getX(), inicial.getY()).getPiece();
//        if (game.getGameVersion().getCheckval().isInCheck(game.getBoard(), king.getColor())){
//            return game;
//        }
        for (movementValidator mov : king.getMovements()){
            if(mov.obstacle(inicial, finalPosition, board)){
                return board;
            }
        }
        if (x ==0 && king.getName() == Piecies.KING && Math.abs(y) == 2 && king.isFirstMove()){
            Board board1 = board.copy();
            if (y > 0) {
                return castleKing(board1, inicial, finalPosition, 1);
            } else {
                return castleKing(board1, inicial, finalPosition, -2);
            }
        }

        return board;
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
