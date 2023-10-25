package Classic.victory;

import Classic.Board;
import Classic.ChessPlayer;
import Classic.Interfaces.victoryValidator;
import Classic.Piece;
import Classic.Position;
import Classic.Enums.Color;
import Classic.Enums.Piecies;

import java.util.List;

public class checkMateValidator implements victoryValidator {

    Piecies name;

    public checkMateValidator(Piecies name) {
        this.name = name;
    }

    public boolean isInCheckmate(ChessPlayer chessPlayer,Board board){
        checkValidator checkval= new checkValidator(name);
        Piece piece = findPiece(chessPlayer.getColor(), name, board);
        if (piece != null){
        for (int x = 0; x < board.getRow(); x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                Position possiblePos = board.getPosition(x,y);
                Position posPiece = board.getPositionWithPiece(piece);
                //Primero chequiamos si el rey se puede mover a los espacios adyacentes
                    Board newBoard = board.movePiece(posPiece, possiblePos, piece);
                    if (!possiblePos.equals(posPiece) && !checkval.isInCheck(newBoard, piece.getColor())) {
                        if (newBoard != board) {
                            return false;
                        }
                    }
                }

                }
            }
        else { //por ahora devolvemos false si no existe el king para no tener problemas en los tests
            return false;
        }
        return !this.pieceInterceptsCheck(piece.getColor(), board);//Chequeamos si alguna pieza puede interceptar el jaque
    }
    public boolean pieceInterceptsCheck (Color color, Board board){
        checkValidator checkval= new checkValidator(name);
            for (int x=0; x < board.getRow(); x++){
                for (int y=0; y< board.getColumn(); y++) {
                    Piece piece1 = board.getPiece(x, y);
                    if (piece1 != null) {
                        if (piece1.getColor() == color) {
                            for (int x1 = 0; x1 < board.getRow(); x1++) {
                                for (int y1 = 0; y1 < board.getColumn(); y1++) {
                                    Position possiblePos = board.getBoard()[x1][y1];
                                    Board newBoard = board.movePiece(board.getPositionWithPiece(piece1), possiblePos, piece1);
                                    if (newBoard != board && !checkval.isInCheck(newBoard, piece1.getColor())) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return false;
    }
    public Piece findPiece(Color color, Piecies name, Board board) {
        Piece king = null;
        for(int x = 0; x<board.getRow();x++) {
            for (int y = 0; y < board.getColumn(); y++) {
                if (board.getBoard()[x][y].getPiece() != null) {
                    if (board.getBoard()[x][y].getPiece().getName() == name && board.getBoard()[x][y].getPiece().getColor() == color) {
                        king = board.getBoard()[x][y].getPiece();
                    }
                }
            }
        }
        return king;
    }

    @Override
    public boolean validateVictory(List <ChessPlayer> chessPlayer, Board board) {
        checkValidator checkval = new checkValidator(name);
        for (ChessPlayer chessPlayer1 : chessPlayer) {
            if (checkval.isInCheck(board, chessPlayer1.getColor())){
                if (isInCheckmate(chessPlayer1, board)) {
                    return true;
                }
            }
        }
        return false;

    }
}
