package Connector;

import Chess.*;
import Movements.Movement;
import Movements.movementValidator;
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

        List<ChessPiece> toReturn = new ArrayList<>(positions.size());

        for (Chess.Position position : positions) {
            Chess.Piece piece = position.getPiece();
            if (piece != null) {
                toReturn.add(new ChessPiece(piece.getId(), adaptColour(piece.getColor()), adaptPosition(position), adaptName(piece.getName())));
            }
        }

        return toReturn;
    }
    private static String adaptName(Piecies name){

        return switch (name) {
            case PAWN -> "pawn";
            case ROOK -> "rook";
            case KNIGHT -> "knight";
            case BISHOP -> "bishop";
            case QUEEN -> "queen";
            case KING -> "king";
            default -> "empty";
        };
    }
    public static Movement toMovement(Move move, Board board){

        Chess.Position fromPosition = board.getPosition(move.getFrom().getRow() -1 , move.getFrom().getColumn() -1 );
        Chess.Position toPosition = board.getPosition(move.getTo().getRow() -1 , move.getTo().getColumn() -1 );

        return new Movement(fromPosition, toPosition);

    }
    public static List<ChessPiece> getPieces(Game game) {
        List<ChessPiece> lista = new ArrayList<>();
        for (ChessPlayer players : game.getChessPlayers()){
            lista.addAll(Connector.adaptPieces(players.getLivingPiecies(game.getBoard())));
        }
        return lista;
    }
}
