package edu.austral.dissis.chess.MyMatches

import Chess.*
import Enums.Color
import Enums.Piecies
import Interfaces.gameInterface
import Movements.*
import specialMove.RoqueKing
import specialMove.coronacion
import Movements.pawnEatDiagonal
import victory.checkMateValidator
import victory.checkValidator

class MyGame() : gameInterface {
    private var board: Board = Board(8, 8)
    private val playersList: MutableList<ChessPlayer> = ArrayList()
    private val checkval : checkValidator = checkValidator(Piecies.KING)
    private val checkmateval : checkMateValidator = checkMateValidator(Piecies.KING)
    private val gameVersion : GameVersion = GameVersion("Classic", checkmateval)
    private val roqueKing: RoqueKing = RoqueKing()
    private val coron: coronacion = coronacion(Piecies.PAWN,Piece(Piecies.QUEEN, Color.WHITE, listOf(straightMove(7, 7), DiagonalMove(7,7,7,7), horizontalMove(7, 7))))


    init {
        val player1 = ChessPlayer("Player 1", Color.WHITE)
            playersList.add(player1)
            val player2 = ChessPlayer("Player 2", Color.BLACK)
            playersList.add(player2)
            player1.changeTurn()
        gameVersion.setCheckval(checkval)
        gameVersion.addSpecialMovementValidators(roqueKing)
        gameVersion.addSpecialMovementValidators(coron)


    }
    var game: Game = Game(playersList, board, gameVersion)

    init {
        val positions: MutableList<Position> = mutableListOf()
        for (i in 0 until 8) {
            positions.add(Position(6, i, Piece(Piecies.PAWN, Color.WHITE, listOf(pawnMovement(0, 1,0,0,2,
                pawnEatDiagonal(0, 1, 0, 0)
            )), "$i${6}")))
            positions.add(Position(1, i, Piece(Piecies.PAWN, Color.BLACK, listOf(pawnMovement(1, 0,0,0,2,
                pawnEatDiagonal(1, 0, 0, 0)
            )), "$i${1}")))
        }

        positions.add(Position(7, 0, Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R1")))
        positions.add(Position(7, 7, Piece(Piecies.ROOK, Color.WHITE, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R2")))
        positions.add(Position(0, 0, Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R3")))
        positions.add(Position(0, 7, Piece(Piecies.ROOK, Color.BLACK, listOf(straightMove(7, 7), horizontalMove(7, 7)), "R4")))

        positions.add(Position(7, 1, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K1")))
        positions.add(Position(7, 6, Piece(Piecies.KNIGHT, Color.WHITE, listOf(Jump(1, 2), Jump(2, 1)), "K2")))
        positions.add(Position(0, 1, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K3")))
        positions.add(Position(0, 6, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K4")))


        positions.add(Position(7, 2, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8,8,8,8)), "B1")))
        positions.add(Position(7, 5, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8,8,8,8)), "B2")))
        positions.add(Position(0, 2, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8,8,8,8)), "B3")))
        positions.add(Position(0, 5, Piece(Piecies.BISHOP, Color.BLACK, listOf(DiagonalMove(8,8,8,8)), "B4")))


        positions.add(Position(7, 4, Piece(Piecies.QUEEN, Color.WHITE, listOf(straightMove(7, 7), DiagonalMove(7,7,7,7), horizontalMove(7, 7)), "Q1")))
        positions.add(Position(0, 4, Piece(Piecies.QUEEN, Color.BLACK, listOf(straightMove(7, 7), DiagonalMove(7,7,7,7), horizontalMove(7, 7)), "Q2")))

        positions.add(Position(7, 3, Piece(Piecies.KING, Color.WHITE, listOf(straightMove(1, 1), DiagonalMove(1,1,1,1), horizontalMove(1, 1)), "KI1")))
        positions.add(Position(0, 3, Piece(Piecies.KING, Color.BLACK, listOf(straightMove(1, 1), DiagonalMove(1,1,1,1), horizontalMove(1, 1)), "KI2")))

        //extra probando jaque
//        positions.add(Position(5, 2, Piece(Piecies.QUEEN, Color.WHITE, listOf(straightMove(7, 7), DiagonalMove(7), horizontalMove(7, 7)), "Q1")))
//        positions.add(Position(4, 5, Piece(Piecies.BISHOP, Color.WHITE, listOf(DiagonalMove(8)), "B1")))
//        positions.add(Position(4, 4, Piece(Piecies.PAWN, Color.WHITE, listOf(pawnMovement(0, 1,0,0,2, pawnEatDiagonal(0, 1, 0, 0))), "x${6}")))
//        positions.add(Position(5,5 , Piece(Piecies.PAWN, Color.BLACK, listOf(pawnMovement(0, 1,0,0,2, pawnEatDiagonal(0, 1, 0, 0))), "X${6}")))
//        positions.add(Position(4, 4, Piece(Piecies.KNIGHT, Color.BLACK, listOf(Jump(1, 2), Jump(2, 1)), "K3")))
//        positions.add(Position(2, 3, Piece(Piecies.QUEEN, Color.BLACK, listOf(straightMove(7, 7), DiagonalMove(7), horizontalMove(7, 7)), "Q2")))


        for (i in 0 until positions.size) {
            val position = positions[i]
            board.board[position.x][position.y] = position
        }
    }

    override fun getPiecies(): MutableList<Piece> {
        return board.piecies
    }

    override fun getBoard(): Board {
        return board
    }

    override fun move(newPos: Position?, oldPos: Position?): Game {
        return game.move(newPos, oldPos)
    }

    override fun getChessPlayers(): MutableList<ChessPlayer> {
        return playersList
    }

    override fun validateVictory(chessPlayer: MutableList<ChessPlayer>?, board: Board?): Boolean {
        return game.validateVictory(chessPlayer, board)
    }
}