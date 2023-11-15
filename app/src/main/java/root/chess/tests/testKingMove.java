package root.chess.tests;


import root.chess.Coordinates;
import root.common.Game;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
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

public class testKingMove {
    testMovements tests;
    Game game;
    Piece whiteKing;
    Position position;
    @BeforeEach
    void setUp() {
        tests = new testMovements();
        Player player1 = new Player("Jugador 1", Color.WHITE);
        List<Player> players = List.of(player1);
        player1.changeTurn();
        whiteKing = new Piece(Pieces.KING, Color.WHITE, List.of(new StraightMove(1,1), new HorizontalMove(1,1), new DiagonalMove(1,1,1,1)),"1");
        position = new Position(2, 2, whiteKing);
        List<Position> posiciones = List.of(
                position
        );

        game = tests.gameInicializer(8, 8, players, posiciones,List.of(new checkMateValidator(Pieces.KING)));
    }
    @Test
    public void testkingvalidmove (){
        List<Coordinates> possibleMoves = List.of(
                new Coordinates(1, 1),
                new Coordinates(1, 2),
                new Coordinates(1, 3),
                new Coordinates(2, 1),
                new Coordinates(2, 3),
                new Coordinates(3, 1),
                new Coordinates(3, 2),
                new Coordinates(3, 3)
        );
        testMovements.assertValidMoves(possibleMoves, game, whiteKing, position);
    }
    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(1, 1),
                new Coordinates(2, 1),
                new Coordinates(3, 3)

        );
        StraightMove move = new StraightMove(1, 0);
        Position whitePawn = new Position(1, 1, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"2"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(2, 1, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(3, 3, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, whiteKing, position);
    }
}
