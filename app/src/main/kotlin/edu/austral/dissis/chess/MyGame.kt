package edu.austral.dissis.chess

import Chess.*
import Enums.Color
import Enums.Piecies
import Interfaces.gameInterface
import Movements.*

class MyGame() : gameInterface {
    private var board: Board = Board(8, 8)
    private val playersList: MutableList<ChessPlayer> = ArrayList()

    init {
            val player1 = ChessPlayer("Player 1", Color.WHITE)
            playersList.add(player1)
            val player2 = ChessPlayer("Player 2", Color.BLACK)
            playersList.add(player2)
            player1.changeTurn()
        }
    var game: Game = Game(playersList, board)

    init {
        val positions: MutableList<Position> = mutableListOf()
        for (i in 0 until 8) {
            positions.add(Position(6, i, Piece(Piecies.PAWN, Color.WHITE, listOf(straightMove(0, 1)), "$i${6}")))
            positions.add(Position(1, i, Piece(Piecies.PAWN, Color.BLACK, listOf(straightMove(1, 0)), "$i${1}")))
        }

        positions.add(Position(7, 0, Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R1")))
        positions.add(Position(7, 7, Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R2")))
        positions.add(Position(0, 0, Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R3")))
        positions.add(Position(0, 7, Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R4")))

        positions.add(Position(7, 1, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K1")))
        positions.add(Position(7, 6, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K2")))
        positions.add(Position(0, 1, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K3")))
        positions.add(Position(0, 6, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K4")))


        positions.add(Position(7, 2, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8)), "B1")))
        positions.add(Position(7, 5, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8)), "B2")))
        positions.add(Position(0, 2, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8)), "B3")))
        positions.add(Position(0, 5, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8)), "B4")))


        positions.add(Position(4, 3, Piece(Piecies.QUEEN, Color.WHITE, listOf(straightMove(7, 7), DiagonalMove(7), horizontalMove(7, 7)), "Q1")))
        positions.add(Position(0, 3, Piece(Piecies.QUEEN, Color.BLACK, listOf(straightMove(7, 7), DiagonalMove(7), horizontalMove(7, 7)), "Q2")))

        positions.add(Position(7, 4, Piece(Piecies.KING, Color.WHITE, listOf(straightMove(1, 1), DiagonalMove(1), horizontalMove(1, 1)), "KI1")))
        positions.add(Position(0, 4, Piece(Piecies.KING, Color.BLACK, listOf(straightMove(1, 1), DiagonalMove(1), horizontalMove(1, 1)), "KI2")))


        for (i in 0 until positions.size) {
            val position = positions[i]
            board.board[position.x][position.y] = position
        }
    }

    override fun getPiecies(): MutableList<Piece> {
        return board.piecies;
    }

    override fun getBoard(): Board {
        return board;
    }

    override fun move(newPos: Position?, oldPos: Position?): Game {
        return game.move(newPos, oldPos)
    }
}