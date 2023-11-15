package root.chess.tests;

import root.chess.Coordinates;
import root.common.Game;
import root.common.classicMovements.StraightMove;
import root.common.Player;
import root.common.Piece;
import root.common.Position;
import root.common.Enums.Color;
import root.common.Enums.Pieces;
import root.common.victory.checkMateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class testPawnMovement {
    testMovements tests;
    Game game;
    Piece pawn;
    Position position;

    @BeforeEach
    void setUp() {
        tests = new testMovements();
        Player player1 = new Player("Jugador 1", Color.WHITE);
        List<Player> players = List.of(player1);
        player1.changeTurn();
        pawn = new Piece(Pieces.PAWN, Color.WHITE, List.of(new StraightMove(1, 0)), "1");
        position = new Position(2, 2, pawn);
        List<Position> posiciones = List.of(
position        );

        game = tests.gameInicializer(8, 8, players, posiciones,List.of(new checkMateValidator(Pieces.KING)));
    }

    @Test
    public void firstMovePawn (){
        List <Coordinates> possibleMoves = List.of(
                new Coordinates(4, 2),
                new Coordinates(3, 2)

        );
        testMovements.assertValidMoves(possibleMoves, game, pawn, position);
    }
    @Test
    public void secondMovePawn (){
        List <Coordinates> possibleMoves = List.of(
                new Coordinates(3, 2)
        );
        testMovements.assertValidMoves(possibleMoves, game, pawn, position);
    }
    @Test
    public void eatPawn (){
        List <Coordinates> possibleMoves = List.of(
                new Coordinates(3, 3),
                new Coordinates(3,2) //No comer pero es un posible movimiento
        );
        Position blackPawn = new Position(3, 3, new Piece(Pieces.PAWN, Color.BLACK, List.of(new StraightMove(0, 1), new StraightMove(1, 1)), "2"));
        game.getBoard().getBoard()[blackPawn.getX()][blackPawn.getY()] = blackPawn;
        testMovements.assertValidMoves(possibleMoves, game, pawn, position);
    }
    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(3, 2),
                new Coordinates(3, 3)
        );
        StraightMove move = new StraightMove(1, 0);
        Position whitePawn = new Position(3, 2, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(3, 3, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;

        testMovements.assertOccupiedMoves(possibleMoves, game, pawn, position);
    }
}
