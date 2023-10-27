package Common.victory;

import Common.Board;
import Common.Enums.Color;
import Common.Interfaces.notPermiteMoveValidator;
import Common.Piece;
import Common.Position;

public class obligatoryMovement implements notPermiteMoveValidator {
    int dif;
    public obligatoryMovement(int dif){
        this.dif = dif;
    }

    public Board validateMove(Board board1, Color color, Position newPos) {
        Piece pieceFinalPos = newPos.getPiece();
        for (int x = 0; x<board1.getRow();x++) {
            for (int y = 0; y < board1.getColumn(); y++) {
                Position position1 = board1.getPosition(x, y);
                if (isValid(color, pieceFinalPos, position1)){
                    Piece piece = position1.getPiece();
                    Board newTablero;
                    if (board1.getPosition(position1.getX() + dif, position1.getY() + dif) != null) {
                        newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() + dif), piece);
                        if (newTablero != board1) {
                            return newTablero;
                        }
                    }
                    if (board1.getPosition(position1.getX() - dif, position1.getY() + dif) != null) {

                        newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() + dif), piece);
                        if (newTablero != board1) {
                            return newTablero;
                        }
                    }
                    if (board1.getPosition(position1.getX() + dif, position1.getY() - dif) != null) {

                        newTablero = board1.move(position1, board1.getPosition(position1.getX() + dif, position1.getY() - dif), piece);
                        if (newTablero != board1) {
                            return newTablero;
                        }
                    }
                    if (board1.getPosition(position1.getX() - dif, position1.getY() - dif) != null) {
                        newTablero = board1.move(position1, board1.getPosition(position1.getX() - dif, position1.getY() - dif), piece);
                        if (newTablero != board1) {
                            return newTablero;
                        }
                    }
                }
            }
        }
        return board1;
    }

    private static boolean isValid(Color color, Piece pieceFinalPos, Position position1) {
        return position1.hasPiece() && position1.getPiece().getColor() == color && position1.getPiece() != pieceFinalPos;
    }

    public Board hasAnotherMove (Board board, Position oldpos, Position newPos){
        Piece piece = newPos.getPiece();
        Board board1 = board.copy();
        int x = oldpos.getX() - newPos.getX();
        if (Math.abs(x)  < dif){
            return board;
        }
                Board newTablero;
                if (board1.getPosition(newPos.getX() + dif, newPos.getY() + dif) != null) {
                    newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() + dif), piece);
                    if (newTablero != board1) {
                        return newTablero;
                    }
                }
                if (board1.getPosition(newPos.getX() - dif, newPos.getY() + dif) != null) {
                    newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() + dif), piece);
                    if (newTablero != board1) {
                        return newTablero;
                    }
                }
                if (board1.getPosition(newPos.getX() + dif, newPos.getY() - dif) != null) {

                    newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() + dif, newPos.getY() - dif), piece);
                    if (newTablero != board1) {
                        return newTablero;
                    }
                }
                if (board1.getPosition(newPos.getX() - dif, newPos.getY() - dif) != null) {
                    newTablero = board1.movePiece(newPos, board1.getPosition(newPos.getX() - dif, newPos.getY() - dif), piece);
                    if (newTablero != board1) {
                        return newTablero;
                    }
                }
        return board;
    }
}
