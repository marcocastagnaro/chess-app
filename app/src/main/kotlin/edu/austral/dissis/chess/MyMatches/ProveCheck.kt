package edu.austral.dissis.chess.MyMatches


import Chess.*
import Common.*
import Chess.Movements.DiagonalMove
import Chess.Movements.horizontalMove
import Chess.Movements.straightMove
import Common.Enums.Color
import Common.Enums.Piecies
import Chess.gameInterface
import Common.victory.eatAllPiecies

class ProveCheck() : gameInterface {
    private var board: Board = Board(4, 4)
    private val playersList: MutableList<ChessPlayer> = ArrayList()
//    private val checkval : checkValidator = checkValidator(Piecies.KING)
//    private val checkmateval : checkMateValidator = checkMateValidator(Piecies.KING)
    private val gameend : eatAllPiecies =
    eatAllPiecies()
    private val gameVersion : GameVersion =
        GameVersion("EAT ALL PIECIES", gameend)


    init {
        val player1 = ChessPlayer("Player 1", Color.WHITE)
        playersList.add(player1)
        val player2 = ChessPlayer("Player 2", Color.BLACK)
        playersList.add(player2)
        player1.changeTurn()
    }
    var game: Game = Game(playersList, board, gameVersion)

    init {
        val positions: MutableList<Position> = mutableListOf()

        positions.add(
            Position(
                3, 1,
                Piece(
                    Piecies.ROOK,
                    Color.WHITE,
                    listOf(
                        straightMove(4, 4),
                        horizontalMove(4, 4)
                    ),
                    "R1"
                )
            )
        )
        positions.add(
            Position(
                3, 3,
                Piece(
                    Piecies.ROOK,
                    Color.WHITE,
                    listOf(
                        straightMove(4, 4),
                        horizontalMove(4, 4)
                    ),
                    "R2"
                )
            )
        )

        positions.add(
            Position(
                2, 3,
                Piece(
                    Piecies.QUEEN,
                    Color.WHITE,
                    listOf(
                        straightMove(4, 4),
                        DiagonalMove(4, 4, 4, 4),
                        horizontalMove(4, 4)
                    ),
                    "Q1"
                )
            )
        )

        positions.add(
            Position(
                1, 3,
                Piece(
                    Piecies.KING,
                    Color.WHITE,
                    listOf(
                        straightMove(1, 1),
                        DiagonalMove(1, 1, 1, 1),
                        horizontalMove(1, 1)
                    ),
                    "KI1"
                )
            )
        )
        positions.add(
            Position(
                0, 0,
                Piece(
                    Piecies.KING,
                    Color.BLACK,
                    listOf(
                        straightMove(1, 1),
                        DiagonalMove(1, 1, 1, 1),
                        horizontalMove(1, 1)
                    ),
                    "KI2"
                )
            )
        )


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

    override fun getChessPlayers(): MutableList<ChessPlayer> {
        return playersList
    }

    override fun validateVictory(chessPlayer: MutableList<ChessPlayer>?, board: Board?): Boolean {
        return game.validateVictory(chessPlayer, board)
    }

}