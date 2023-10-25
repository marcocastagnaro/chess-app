package Chess.tests;

import Chess.*;

import Classic.ChessPlayer;
import Classic.Piece;
import Classic.Position;
import Classic.Enums.Color;
import Classic.Enums.Piecies;
import Chess.Movements.straightMove;
import Classic.victory.checkMateValidator;
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
        ChessPlayer chessPlayer1 = new ChessPlayer("Jugador 1", Color.WHITE);
        List<ChessPlayer> chessPlayers = List.of(chessPlayer1);
        chessPlayer1.changeTurn();
        pawn = new Piece(Piecies.PAWN, Color.WHITE, List.of(new straightMove(1, 0)), "1");
        position = new Position(2, 2, pawn);
        List<Position> posiciones = List.of(
position        );

        game = tests.gameInicializer(8, 8, chessPlayers, posiciones,new checkMateValidator(Piecies.KING), null);
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
        Position blackPawn = new Position(3, 3, new Piece(Piecies.PAWN, Color.BLACK, List.of(new straightMove(0, 1), new straightMove(1, 1)), "2"));
        game.getBoard().getBoard()[blackPawn.getX()][blackPawn.getY()] = blackPawn;
        testMovements.assertValidMoves(possibleMoves, game, pawn, position);
    }
    @Test
    public void testOccupiedMovements (){
        List<Coordinates> possibleMoves = Arrays.asList(
                new Coordinates(3, 2),
                new Coordinates(3, 3)
        );
        straightMove move = new straightMove(1, 0);
        Position whitePawn = new Position(3, 2, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"3"));
        game.getBoard().getBoard()[whitePawn.getX()][whitePawn.getY()] = whitePawn;
        Position whitePawn2 = new Position(3, 3, new Piece(Piecies.PAWN, Color.WHITE, List.of(move),"4"));
        game.getBoard().getBoard()[whitePawn2.getX()][whitePawn2.getY()] = whitePawn2;

        testMovements.assertOccupiedMoves(possibleMoves, game, pawn, position);
    }
}
