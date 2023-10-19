package Connector;

import Chess.*;
import Enums.Color;
import Enums.Piecies;
import Movements.Movement;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Connector {
    public static BoardSize adaptBoard(Board board){
        return new BoardSize(board.getRow(), board.getColumn());
    }
    public static Position adaptPosition(Chess.Position position){
        return new Position(position.getX() +1 , position.getY() +1 );
    }
    public static PlayerColor adaptColour(Color colour){
        if (colour == Color.BLACK) return PlayerColor.BLACK;
        else return PlayerColor.WHITE;
    }
    public static List<ChessPiece> adaptPieces(List<Chess.Position> positions){

        List<ChessPiece> piezas = new ArrayList<>(positions.size());

        for (Chess.Position position : positions) {
            Chess.Piece piece = position.getPiece();
            if (piece != null) {
                piezas.add(new ChessPiece(piece.getId(), adaptColour(piece.getColor()), adaptPosition(position), adaptName(piece.getName())));
            }
        }

        return piezas;
    }
    private static String adaptName(Piecies name){

        return switch (name) {
            case PAWN -> "pawn";
            case ROOK -> "rook";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case QUEEN -> "queen";
            case KING -> "king";
            case CHANCELLOR ->  "chancellor";
            default -> "empty";
        };
    }
    public static Movement toMovement(Move move, Board board){

        Chess.Position fromPosition = board.getPosition(move.getFrom().getRow() -1 , move.getFrom().getColumn() -1 );
        Chess.Position toPosition = board.getPosition(move.getTo().getRow() -1 , move.getTo().getColumn() -1 );

        return new Movement(fromPosition, toPosition);

    }
    public static List<ChessPiece> getPieces(Chess.Board board) {
            List <Chess.Position> positions = new ArrayList<>();
            for (int x =0; x<board.getRow(); x++){
                for (int y =0; y< board.getColumn(); y++){
                    if (board.getPosition(x,y).hasPiece()){
                        positions.add(board.getPosition(x ,y));
                    }
                }
            }
            return adaptPieces(positions);
        }
}
