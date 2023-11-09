package edu.austral.dissis.chess.MyMatches


import root.checkers.game.checkersTurn
import root.checkers.game.noMoveVictory
import root.checkers.movements.classicMovement
import root.checkers.movements.eatMov
import root.checkers.movements.eatMovement
import root.common.*
import root.common.Enums.Color
import root.common.Enums.Piecies
import root.common.specialRule.coronacion
import root.common.victory.eatAllPiecies
import root.checkers.movements.obligatoryValidator
import root.common.Game
import root.common.Interfaces.gameInterface
import root.common.Interfaces.validators
import root.common.Interfaces.victoryValidator

class Checkers () : gameInterface {
    private var board: Board = Board(8, 8)
    private val playersList: MutableList<ChessPlayer> = ArrayList()
    private val coron: coronacion =
        coronacion(
            Piecies.PAWN,
            Piece(
                Piecies.QUEEN,
                Color.WHITE,
                listOf(
                    classicMovement(1, 1),
                    eatMovement(2, 2)
                )
            )
        )
//    private val eat : eatMov = eatMov(2,2);

    private val obligatoryMovement: obligatoryValidator =
        obligatoryValidator(2)
    private val eatAll: eatAllPiecies =
        eatAllPiecies()
    private val noMov : noMoveVictory = noMoveVictory()
    private val victoryMode : MutableList<victoryValidator> = ArrayList()
    private val validator: MutableList<validators> = ArrayList()
    private val gameVersion : GameVersion = GameVersion("root/common", victoryMode, validator)
    val player1 =
        ChessPlayer("Player 1", Color.WHITE)
    val player2 =
        ChessPlayer("Player 2", Color.BLACK)
    val customizeTurn =
        checkersTurn(2)
    init {
        playersList.add(player1)
        playersList.add(player2)
        gameVersion.addSpecialMovementValidators(coron)
        validator.add(obligatoryMovement)
        victoryMode.add(eatAll)
        victoryMode.add(noMov)

    }
    var game: Game =
        Game(board, playersList, gameVersion, player1, customizeTurn)

    init {
        val positions: MutableList<Position> = mutableListOf()
        for (row in 0 until board.row) {
            // Recorre las columnas del tablero
            for (col in 0 until board.column) {
                // Determina el color de la casilla (alternando entre blanco y negro)
                val cellColor = if ((row + col) % 2 == 0) Color.WHITE else Color.BLACK

                // Inicializa las posiciones iniciales de las damas negras en las primeras 3 filas
                if (row < 3 && cellColor == Color.BLACK) {
                    positions.add(
                        Position(
                            row, col, Piece(
                                Piecies.PAWN, Color.BLACK, listOf(
                                    classicMovement(0, 1),
                                    eatMovement(0, 2)
                                )
                            )
                        )
                    )
                }

                // Inicializa las posiciones iniciales de las damas blancas en las últimas 3 filas
                if (row >= board.row - 3 && cellColor == Color.BLACK) {
                    positions.add(
                        Position(
                            row, col, Piece(
                                Piecies.PAWN, Color.WHITE, listOf(
                                    classicMovement(1, 0),
                                    eatMovement(2, 0)
                                )
                            )
                        )
                    )

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

    override fun move(oldPos: Position?, newPos: Position?): Game {
        return game.move(oldPos, newPos)
    }

    override fun getChessPlayers(): MutableList<ChessPlayer> {
        return playersList
    }

    override fun validateVictory(chessPlayer: MutableList<ChessPlayer>?, board: Board?): Boolean {
        return game.validateVictory(chessPlayer, board)
    }
}