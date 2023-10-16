package tests;

import Chess.*;
import Movements.DiagonalMove;
import Movements.horizontalMove;
import Movements.straightMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testCheck {
    ChessPlayer chessPlayer1 = new ChessPlayer("Jugador 1", Color.WHITE);
    ChessPlayer chessPlayer2 = new ChessPlayer("Jugador 2", Color.BLACK);
    Game oldgame;
    Piece blackQueen;
    Piece whiteKing;
    testMovements testMov = new testMovements();
    Position position1;
    Position position2;
    @BeforeEach
    void SetUp (){
        whiteKing = new Piece(Piecies.KING, Color.WHITE, List.of(new straightMove(1, 1), new horizontalMove(1, 1), new DiagonalMove(1)), "1");
        blackQueen = new Piece (Piecies.QUEEN, Color.BLACK, List.of(new horizontalMove(7,7), new straightMove(7,7), new DiagonalMove(7)),"2");
        position1 = new Position(0,0, whiteKing);
        position2 = new Position(6,2, blackQueen);
        List<Position> posiciones = List.of(
                position1, position2
        );
        List <ChessPlayer> chessPlayers = List.of (chessPlayer1, chessPlayer2);
        chessPlayer2.changeTurn();

        oldgame = testMov.gameInicializer(8,8, chessPlayers,posiciones);
    }

    @Test
    public void isInCheck (){ //testea que esta en jaque y si mueve una pieza no lo deja porque esta en jaque
        Position newPos = new Position(0,2);
        Game game = oldgame.move(newPos, position2);
        assertTrue(chessPlayer1.isCheck()); //chequea que esta en jaque
        Piece piece = new Piece(Piecies.PAWN, Color.WHITE, List.of(new straightMove(1,0)), "3");
        Position position = new Position(2,2, piece);
        game.getBoard().getBoard()[2][2] = position;
        Position newPosition = new Position(3,2);
        Game newgame = game.move(newPosition, position);
        assertNotSame(newgame.getBoard().getBoard()[newPosition.getX()][newPosition.getY()].getPiece(), piece);
        assertTrue(chessPlayer1.isCheck()); //cheqeua que sigue estando en jaque
    }

    @Test
    public void isInCheckButEscapes (){ //testea que esta en jaque y al moverse deja de estar en jaque
        Position newPos = new Position(0,2);
        Game game = oldgame.move(newPos, position2);
        assertTrue(chessPlayer1.isCheck()); //chequea que esta en jaque
        Piece piece = new Piece(Piecies.ROOK, Color.WHITE, List.of(new straightMove(7,7), new horizontalMove(7,7)), "4");
        Position position = new Position(1,1, piece);
        game.getBoard().getBoard()[1][1] = position;
        Position newPosition = new Position(0,1);
        Game newgame = game.move(newPosition, position);
        assertFalse(chessPlayer1.isCheck()); //chequea que ya no esta en jaque
    }
    @Test
    public void isInCheckMate (){ //testea que esta en jaque y no puede moverse porque esta en jaque mate
        Piece piece = new Piece(Piecies.PAWN, Color.WHITE, List.of(new straightMove(1,0)), "5");
        Position position = new Position(1,0, piece);
        oldgame.getBoard().getBoard()[1][0] = position;
        Position newPos = new Position(0,2);
        Game game = oldgame.move(newPos, position2);
        assertTrue(chessPlayer1.isCheckmate()); //chequea que esta en jaque mate
    }
}
