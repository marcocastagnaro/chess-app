package edu.austral.dissis.chess.MyMatches

import Checkers.game.gameCheckers
import Checkers.game.gameCheckersInterface
import Checkers.movements.classicMovement
import Checkers.movements.eatMovement
import Checkers.movements.specialMovementVal
import Common.*
import Common.Enums.Color
import Common.Enums.Piecies
import Common.specialMove.coronacion
import Common.victory.eatAllPiecies
import Common.victory.obligatoryMovement

class Checkers () : gameCheckersInterface {
    private var board: Board = Board(8, 8)
    private val playersList: MutableList<ChessPlayer> = ArrayList()
    private val coron: coronacion = coronacion(Piecies.PAWN, Piece(Piecies.QUEEN, Color.WHITE, listOf(classicMovement(1,1), eatMovement(2, 2))))
    private val validMove : specialMovementVal = specialMovementVal()
    private val victoryMode: eatAllPiecies = eatAllPiecies()
    private val gameVersion : GameVersion = GameVersion("Common", victoryMode)
    val player1 = ChessPlayer("Player 1", Color.WHITE)
    val player2 = ChessPlayer("Player 2", Color.BLACK)

    init {
        playersList.add(player1)
        playersList.add(player2)
        gameVersion.addSpecialMovementValidators(coron)
//        gameVersion.addSpecialMovementValidators(obligt)
        gameVersion.addSpecialMovementValidators(validMove)
        gameVersion.setObligatory(obligatoryMovement(2))
    }
    var game: gameCheckers = gameCheckers(board,playersList, gameVersion, player1)

    init {
        val positions: MutableList<Position> = mutableListOf()
        for (row in 0 until board.row) {
            // Recorre las columnas del tablero
            for (col in 0 until board.column) {
                // Determina el color de la casilla (alternando entre blanco y negro)
                val cellColor = if ((row + col) % 2 == 0) Color.WHITE else Color.BLACK

                // Inicializa las posiciones iniciales de las damas negras en las primeras 3 filas
                if (row < 3 && cellColor == Color.BLACK) {
                    positions.add(Position(row, col, Piece(Piecies.PAWN, Color.BLACK, listOf(classicMovement(0,1),
                        eatMovement(0, 2)))))
                }

                // Inicializa las posiciones iniciales de las damas blancas en las últimas 3 filas
                if (row >= board.row - 3 && cellColor == Color.BLACK) {
                    positions.add(Position(row, col, Piece(Piecies.PAWN, Color.WHITE, listOf(classicMovement(1,0),
                        eatMovement(2, 0)))))

                }
            }
        }

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

    override fun move( oldPos: Position?, newPos: Position?): gameCheckers {
        return game.move(oldPos, newPos)
    }

    override fun getChessPlayers(): MutableList<ChessPlayer> {
        return playersList
    }

    override fun validateVictory(chessPlayer: MutableList<ChessPlayer>?, board: Board?): Boolean {
        return game.validateVictory(chessPlayer, board)
    }
}