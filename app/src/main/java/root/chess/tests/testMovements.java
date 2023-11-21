package root.chess.tests;


import root.chess.Coordinates;
import root.common.Game;
import root.chess.ChessTurn;
import root.common.*;

import root.common.Interfaces.Turn;
import root.common.Interfaces.VictoryValidator;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testMovements {

    //crear clase piecefactory --> crear piezas
    public Game gameInicializer(int row, int column, List<Player> players, List<Position> posiciones, List<VictoryValidator> vic) {
        Board board = new Board(row, column);
        for (Position posicion : posiciones) {
            board.getBoard()[posicion.getX()][posicion.getY()] = posicion;
        }
        GameVersion gameVersion = new GameVersion("1", vic, new ArrayList<>(), new ArrayList<>());
        Turn cusTurn = new ChessTurn();
        return new Game( board, players, gameVersion, players.get(0), cusTurn);
    }

    static Position getPositionfromGame(int row, int column, Game game) {
        for (int x = 0; x < game.getBoard().getBoard().length; x++) {
            for (int y = 0; y < game.getBoard().getBoard()[0].length; y++) {
                if (x == row && y == column) {
                    return game.getBoard().getBoard()[x][y];
                }
            }
        }
        return null;
    }
    public static List<Position> getNotValidMoves(List<Position> validMoves, Game game, Piece piece) {
        List<Position> notValidMoves = new ArrayList<>();
        for (int x = 0; x < game.getBoard().getRow(); x++) {
            for (int y = 0; y < game.getBoard().getColumn(); y++) {
                Position currentPosition = game.getBoard().getBoard()[x][y];
                if (!validMoves.contains(currentPosition) && currentPosition.getPiece() != piece){
                    notValidMoves.add(currentPosition);
                }
            }
        }
        return notValidMoves;
    }
    public static void assertValidMoves(List<Coordinates> validMoves, Game game, Piece piece, Position oldPos) {
        List<Position> possibleMoves = new ArrayList<>();
        for (Coordinates coordinates : validMoves) {
            Position position = getPositionfromGame(coordinates.getRow(), coordinates.getColumn(), game);
            possibleMoves.add(position);
        }
        List <Position> notValidmoves = getNotValidMoves(possibleMoves, game, piece);
        for (Position targetPosition : possibleMoves) {
            Game newgame = game.move(oldPos, targetPosition);
            assertEquals(piece.getId(), newgame.getBoard().getPiece(targetPosition.getX(),targetPosition.getY()).getId());
        }
        for (Position notValidPosition : notValidmoves){
            Game newgame = game.move(oldPos, notValidPosition);
            assertNotSame(piece, newgame.getBoard().getBoard()[notValidPosition.getX()][notValidPosition.getY()].getPiece());
        }
    }

    public static void assertOccupiedMoves (List<Coordinates> validMoves, Game game, Piece piece, Position oldpos) {
        List<Position> possibleMoves = new ArrayList<>();
        for (Coordinates coordinates : validMoves) {
            Position position = getPositionfromGame(coordinates.getRow(), coordinates.getColumn(), game);
            possibleMoves.add(position);
        }
        for (Position targetPosition : possibleMoves) {
            Game newgame = game.move(oldpos, targetPosition);
            assertNotSame(newgame.getBoard().getBoard()[targetPosition.getX()][targetPosition.getY()].getPiece(), piece);
        }
    }
}
