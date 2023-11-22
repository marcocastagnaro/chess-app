package root.chess.movements;

import root.chess.movements.CheckValidator;
import root.common.Board;
import root.common.Player;
import root.common.Interfaces.VictoryValidator;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;

import java.util.List;

public class CheckMateValidator implements VictoryValidator {

    Pieces name;

    public CheckMateValidator(Pieces name) {
        this.name = name;
    }

    public boolean isInCheckmate(Player player, Board board){
        CheckValidator checkval= new CheckValidator(name);
        Piece piece = findPiece(player.getColor(), name, board);
        if (piece != null){
            if (kingHasMove(board, checkval, piece)) return false;
        }
        return !this.pieceInterceptsCheck(piece.getColor(), board);//Chequeamos si alguna pieza puede interceptar el jaque
    }

    private static boolean kingHasMove(Board board, CheckValidator checkval, Piece piece) {
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x, y);
                Position posPiece = board.getPositionWithPiece(piece);
                //Primero chequiamos si el rey se puede mover a los espacios adyacentes
                Board newBoard = board.movePiece(posPiece, possiblePos, piece);
                if (isValidate(checkval, piece, possiblePos, posPiece, newBoard)) {
                    if (newBoard != board) { //Si el rey se puede mover a un espacio adyacente, no esta en jaque mate
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidate(CheckValidator checkval, Piece piece, Position possiblePos, Position posPiece, Board newBoard) {
        return !possiblePos.equals(posPiece) && !checkval.validateMove(newBoard, piece.getColor());
    }

    public boolean pieceInterceptsCheck (Color color, Board board){
        CheckValidator checkval= new CheckValidator(name);
            for (int x=0; x < board.getRow(); x++) {
                for (int y = 0; y < board.getColumn(); y++) {
                    Piece piece1 = board.getPiece(x, y);
                    if (equalsColor(color, piece1) && (pieceCanIntercepts(board, checkval, piece1))) return true;
                }
            }
        return false;
    }

    private static boolean equalsColor(Color color, Piece piece1) {
        return piece1 != null && piece1.getColor() == color;
    }

    private static boolean pieceCanIntercepts(Board board, CheckValidator checkval, Piece piece1) {
        for (int x1 = 0; x1 < board.getRow(); x1++) {
            for (int y1 = 0; y1 < board.getColumn(); y1++) {
                Position possiblePos = board.getBoard()[x1][y1];
                Board newBoard = board.movePiece(board.getPositionWithPiece(piece1), possiblePos, piece1);
                if (newBoard != board && !checkval.validateMove(newBoard, piece1.getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Piece findPiece(Color color, Pieces name, Board board) {
        Piece king = null;
        for(int x = 0; x<board.getRow();x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                king = getPiece(color, name, board, king, x, y);
            }
        }
        return king;
    }

    private static Piece getPiece(Color color, Pieces name, Board board, Piece king, int x, int y) {
        if (board.getBoard()[x][y].getPiece() != null) {
            if (board.getBoard()[x][y].getPiece().getName() == name && board.getBoard()[x][y].getPiece().getColor() == color) {
                king = board.getBoard()[x][y].getPiece();
            }
        }
        return king;
    }

    @Override
    public boolean validateVictory(List <Player> player, Board board) {
        CheckValidator checkval = new CheckValidator(name);
        for (Player player1 : player) {
            if (playerWin(board, checkval, player1)) return true;
        }
        return false;

    }

    private boolean playerWin(Board board, CheckValidator checkval, Player player1) {
        if (checkval.validateMove(board, player1.getColor())){
            return isInCheckmate(player1, board);
        }
        return false;
    }
}
