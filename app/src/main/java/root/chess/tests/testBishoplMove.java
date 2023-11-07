package root.chess.tests;


import root.chess.Coordinates;
import root.common.Game;
import root.chess.Movements.DiagonalMove;
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

public class testBishoplMove {
    testMovements tests;
    Game game;
    Piece whiteBishop;
    Position position;
    @BeforeEach
    void setUp() {
        tests = new testMovements();
        ChessPlayer chessPlayer1 = new ChessPlayer("Jugador 1", Color.WHITE);
        List<ChessPlayer> chessPlayers = List.of(chessPlayer1);
        chessPlayer1.changeTurn();
        whiteBishop = new Piece(Piecies.BISHOP, Color.WHITE, List.of(new DiagonalMove(7,7,7,7)),"1");
        Position position1 = new Position(2, 2, whiteBishop);
        List<Position> posiciones = List.of(
                position1
        );
        game = tests.gameInicializer(8, 8, chessPlayers, posiciones, new checkMateValidator(Piecies.KING), null);
    }
    @Test
    public void testbishopvalidmove() {
        List<Coordinates> possibleMoves = List.of(
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
        testMovements.assertValidMoves(possibleMoves, game, whiteBishop, position);
    }
    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(1, 1),
                new Coordinates(1, 3),
                new Coordinates(4, 0)

        );
        straightMove move = new straightMove(1, 0);
        Position whitePawn = new Position(1, 1, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"2"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(1, 3, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;
        Position whitePawn3 = new Position(4, 0, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn3.getX()][whitePawn3.getY()] = whitePawn3;
        testMovements.assertOccupiedMoves(possibleMoves, game, whiteBishop, position);
    }
}
