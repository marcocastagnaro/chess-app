package root.chess.tests;


import root.chess.Coordinates;
import root.common.Game;
import root.chess.Movements.horizontalMove;
import root.chess.Movements.straightMove;
import root.common.ChessPlayer;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Piecies;
import root.common.victory.checkMateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class testRookMovement {
    testMovements tests;
    Game game;
    Piece rook;
    Position position;
    @BeforeEach
    void setUp() {
        tests = new testMovements();
        ChessPlayer chessPlayer1 = new ChessPlayer("Jugador 1", Color.WHITE);
        List<ChessPlayer> chessPlayers = List.of(chessPlayer1);
        chessPlayer1.changeTurn();
        rook = new Piece(Piecies.ROOK, Color.WHITE, List.of(new straightMove(7, 7), new horizontalMove(7, 7)),"1");
        position = new Position(2, 0, rook);
        List<Position> posiciones = List.of(
position        );

        game = tests.gameInicializer(8, 8, chessPlayers, posiciones,new checkMateValidator(Piecies.KING),null);
    }
    @Test
    public void testRookMovement() {
        List<Coordinates> possibleMoves = List.of(
                new Coordinates(2, 1),
                new Coordinates(2, 3),
                new Coordinates(2, 2),
                new Coordinates(2, 4),
                new Coordinates(2, 5),
                new Coordinates(2, 6),
                new Coordinates(2, 7),
                new Coordinates(0, 0),
                new Coordinates(1, 0),
                new Coordinates(3, 0),
                new Coordinates(4, 0),
                new Coordinates(5, 0),
                new Coordinates(6, 0),
                new Coordinates(7, 0)
        );
        testMovements.assertValidMoves(possibleMoves, game, rook, position);
    }
    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(2, 6),
                new Coordinates(4, 0),
                new Coordinates(0, 0)

        );
        straightMove move = new straightMove(1, 0);
        Position whitePawn = new Position(2, 6, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"2"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(4, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(0, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, rook, position);
    }
    @Test
    public void testObstaclemove(){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(2, 6),
                new Coordinates(4, 0),
                new Coordinates(0, 0)

        );
        straightMove move = new straightMove(1, 0);
        Position whitePawn = new Position(2, 4, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(3, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"5"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(1, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"6"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, rook,position);
    }
}
