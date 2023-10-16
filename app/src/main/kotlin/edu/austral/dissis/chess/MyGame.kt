package edu.austral.dissis.chess

import Chess.*
import Movements.*

class MyGame() {
    var board: Board = Board(8, 8)
    val playersList: MutableList<ChessPlayer> = ArrayList()

        init {
            val player1 = ChessPlayer("Player 1", Color.WHITE)
            playersList.add(player1)
            val player2 = ChessPlayer("Player 2", Color.BLACK)
            playersList.add(player2)
            player1.changeTurn()
        }
    var game: Game = Game(playersList, board)

    fun Game1() : Game {
        var positions: MutableList<Position> = mutableListOf()
        for (i in 0 until 8) {
            positions.add(Position(i, 6, Piece(Piecies.PAWN, Color.WHITE, listOf(horizontalMove(0, 1)), "$i${6}")))
            positions.add(Position(i, 1, Piece(Piecies.PAWN, Color.BLACK, listOf(horizontalMove(1, 0)), "$i${1}")))
        }


        positions.add(
            Position(
                0,
                7,
                Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R1")
            )
        )
        positions.add(
            Position(
                7,
                7,
                Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R2")
            )
        )
        positions.add(
            Position(
                0,
                0,
                Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R3")
            )
        )
        positions.add(
            Position(
                7,
                0,
                Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R4")
            )
        )

        positions.add(Position(1, 7, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K1")))
        positions.add(Position(6, 7, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K2")))
        positions.add(Position(1, 0, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K3")))
        positions.add(Position(6, 0, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K4")))


        positions.add(Position(2, 7, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8)), "B1")))
        positions.add(Position(5, 7, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8)), "B2")))
        positions.add(Position(2, 0, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8)), "B3")))
        positions.add(Position(5, 0, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8)), "B4")))

        //Queen

        //Queen
        positions.add(
            Position(
                3,
                7,
                Piece(
                    Piecies.QUEEN,
                    Color.WHITE,
                    listOf(straightMove(8, 8), DiagonalMove(8), horizontalMove(8, 8)),
                    "Q1"
                )
            )
        )
        positions.add(
            Position(
                3,
                0,
                Piece(
                    Piecies.QUEEN,
                    Color.BLACK,
                    listOf(straightMove(8, 8), DiagonalMove(8), horizontalMove(8, 8)),
                    "Q2"
                )
            )
        )

        //King

        positions.add(Position(4, 7, Piece(Piecies.KING, Color.WHITE, listOf(straightMove(1, 1), DiagonalMove(1), horizontalMove(1, 1)), "K1")))
        positions.add(Position(4, 0, Piece(Piecies.KING, Color.BLACK, listOf(straightMove(1, 1), DiagonalMove(1), horizontalMove(1, 1)), "K2")))


        for (i in 0 until positions.size) {
            val position = positions[i]
            board.board[position.x][position.y] = position
        }

        return game
    }

        fun makemovement(movement: Movement): Game {
            val newGame = game.move(movement.newPos, movement.oldPos)
            return newGame
        }
    fun playerForCurrentTurn(): ChessPlayer {
        return game.playerForCurrentTurn
    }
    }