package root.chess.tests;


import root.chess.Coordinates;
import root.common.Game;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;
import root.common.Player;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;
import root.chess.movements.CheckMateValidator;
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
        Player player1 = new Player("Jugador 1", Color.WHITE);
        List<Player> players = List.of(player1);
        player1.changeTurn();
        rook = new Piece(Pieces.ROOK, Color.WHITE, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7)),"1");
        position = new Position(2, 0, rook);
        List<Position> posiciones = List.of(
position        );

        game = tests.gameInicializer(8, 8, players, posiciones,List.of(new CheckMateValidator(Pieces.KING)));
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
        StraightMove move = new StraightMove(1, 0);
        Position whitePawn = new Position(2, 6, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"2"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(4, 0, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(0, 0, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"4"));
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
        StraightMove move = new StraightMove(1, 0);
        Position whitePawn = new Position(2, 4, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(3, 0, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"5"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(1, 0, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"6"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, rook,position);
    }
}
