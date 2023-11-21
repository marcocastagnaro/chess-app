package root.chess.tests;

import root.chess.Coordinates;
import root.common.Game;
import root.common.classicMovements.DiagonalMove;
import root.common.classicMovements.HorizontalMove;
import root.common.classicMovements.StraightMove;
import root.common.Player;
import root.common.Piece;
import root.common.Position;
import root.common.enums.Color;
import root.common.enums.Pieces;

import root.common.victory.CheckMateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class testQueenMovement {
    testMovements tests;
    Game game;
    Piece queen;
    Position position;
        @BeforeEach
        void setUp() {
            tests = new testMovements();
            Player player1 = new Player("Jugador 1", Color.WHITE);
            List<Player> players = List.of(player1);
            player1.changeTurn();
            queen = new Piece(Pieces.QUEEN, Color.WHITE, List.of(new StraightMove(7, 7), new HorizontalMove(7, 7), new DiagonalMove(7,7,7,7)),"1");
            position = new Position(2, 2, queen);
            List<Position> posiciones = List.of(
position            );

            game = tests.gameInicializer(8, 8, players, posiciones,List.of(new CheckMateValidator(Pieces.KING)));
        }
        @Test
        public void testQueenMovement() {
            List<Coordinates> possibleMoves = List.of(
                    new Coordinates(0, 2),
                    new Coordinates(1, 2),
                    new Coordinates(3, 2),
                    new Coordinates(4, 2),
                    new Coordinates(5, 2),
                    new Coordinates(6, 2),
                    new Coordinates(7, 2),
                    new Coordinates(2, 0),
                    new Coordinates(2, 1),
                    new Coordinates(2, 3),
                    new Coordinates(2, 4),
                    new Coordinates(2, 5),
                    new Coordinates(2, 6),
                    new Coordinates(2, 7),
                    new Coordinates(0, 0),
                    new Coordinates(1, 1),
                    new Coordinates(3, 3),
                    new Coordinates(4, 4),
                    new Coordinates(5, 5),
                    new Coordinates(6, 6),
                    new Coordinates(7, 7),
                    new Coordinates(0, 4),
                    new Coordinates(1, 3),
                    new Coordinates(3, 1),
                    new Coordinates(4,  0)
            );
            testMovements.assertValidMoves(possibleMoves, game, queen, position);
        }
        @Test
    public void testOccupiedposition(){
            List<Coordinates> possibleMoves = Arrays.asList(
                    new Coordinates(0, 2),
                    new Coordinates(4, 4),
                    new Coordinates(3, 1)

            );
            StraightMove move = new StraightMove(1, 0);
            Position whitePawn = new Position(0, 2, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"2"));
            game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
            Position whitePawn2 = new Position(3, 1, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"3"));
            game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
            Position whitePawn3 = new Position(4, 4, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"4"));
            game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
            testMovements.assertOccupiedMoves(possibleMoves, game, queen, position);
        }
        @Test
    public void testObstacleMove (){ //testea que hay un obstaculo en el camino
            List<Coordinates> possibleMoves = Arrays.asList(
                    new Coordinates(0, 2),
                    new Coordinates(4, 4)

            );
            StraightMove move = new StraightMove(1, 0);
            Position whitePawn = new Position(1, 2, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"5"));
            game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
            Position whitePawn2 = new Position(3, 3, new Piece(Pieces.PAWN, Color.WHITE, List.of(move),"6"));
            game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;

            testMovements.assertOccupiedMoves(possibleMoves, game, queen,position);
        }
}
