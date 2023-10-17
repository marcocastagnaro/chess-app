package tests;

import Chess.*;

import Enums.Color;
import Enums.Piecies;
import Movements.Jump;
import Movements.straightMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class testKnightMove {
    testMovements tests;
    Game game;
    Piece whiteHorse;
    Position position;
    @BeforeEach
    void setUp() {
        tests = new testMovements();
        ChessPlayer chessPlayer1 = new ChessPlayer("Jugador 1", Color.WHITE);
        List<ChessPlayer> chessPlayers = List.of(chessPlayer1);
        chessPlayer1.changeTurn();
        whiteHorse = new Piece(Piecies.KNIGHT, Color.WHITE, List.of(new Jump(2, 1), new Jump(1, 2)), "1");
        position = new Position(2, 2, whiteHorse);
        List<Position> posiciones = List.of(
position        );

        game = tests.gameInicializer(8, 8, chessPlayers, posiciones);
    }
    @Test
    public void testKnightMovement() {
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(1, 0),
                new Coordinates(0, 1),
                new Coordinates(4, 3),
                new Coordinates(4, 1),
                new Coordinates(1, 4),
                new Coordinates(3, 4),
                new Coordinates(0,3),
                new Coordinates (3,0)
        );
        testMovements.assertValidMoves(possibleMoves, game, whiteHorse, position);
    }

    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(1, 0),
                new Coordinates(0, 1),
                new Coordinates(4, 3)

        );
        straightMove move = new straightMove(1, 0);
        Position whitePawn = new Position(1, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move), "2"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(0, 1, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(4, 3, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, whiteHorse, position);
    }
}