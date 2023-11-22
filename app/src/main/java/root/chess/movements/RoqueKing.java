package root.chess.movements;

import root.common.Board;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Pieces;
import root.common.Interfaces.MovementValidator;
import root.common.Interfaces.SpecialRules;

public class RoqueKing implements SpecialRules {
    @Override
    public Board validateMove(Board board, Position inicial, Position finalPosition) {
        Position delta = inicial.delta(finalPosition);
        Piece king = inicial.getPiece();
        if (king != null) {
            for (MovementValidator mov : king.getMovements()) { //para ver si hay un obstaculo en el camino
                if (mov.obstacle(inicial, finalPosition, board)) {
                    return board;
                }
            }
            if (validCastle(delta.getY(), delta.getX(), king)) {
                Board board1 = board.copy();
                if (delta.getX() > 0) {
                    return castleKing(board1, inicial, finalPosition, 1);
                } else {
                    return castleKing(board1, inicial, finalPosition, -2);
                }
            }
        }
        return board;
    }
    private static boolean validCastle(int y, int x, Piece king) {
        return x == 0 && king.getName() == Pieces.KING && Math.abs(y) == 2 && king.isFirstMove();
    }

    private Board castleKing(Board board, Position inicial, Position finalPosition, int dir) {
        Position towerPosition = new Position(inicial.getX(), finalPosition.getY() + dir + 1);
        Piece tower = board.getPosition(towerPosition.getX(), towerPosition.getY()).getPiece();
        if (tower.getName() == Pieces.ROOK && tower.isFirstMove()){
            Piece newKing = (board.getPosition(inicial.getX(), inicial.getY()).getPiece());
            board.getBoard()[inicial.getX()][inicial.getY()] = new Position(inicial.getX(), inicial.getY());
            board.getBoard()[finalPosition.getX()][finalPosition.getY()] = new Position(finalPosition.getX(), finalPosition.getY(), newKing);
            if ( dir > 0){
                makeCastle(inicial, finalPosition.getY() - 1, board, towerPosition, tower);
            } else {
                makeCastle(inicial, finalPosition.getY() + 1, board, towerPosition, tower);
            }
        }
        return board;
    }

    private static void makeCastle(Position inicial, int finalPosition, Board board, Position towerPosition, Piece tower) {
        Position position = new Position(inicial.getX(), finalPosition);
        board.getBoard()[towerPosition.getX()][towerPosition.getY()] = new Position(towerPosition.getX(), towerPosition.getY());
        board.getBoard()[position.getX()][position.getY()] = new Position(position.getX(), position.getY(), tower);
    }
}
