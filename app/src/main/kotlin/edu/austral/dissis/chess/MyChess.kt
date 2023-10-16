package edu.austral.dissis.chess;

import Connector.Connector
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE

public class MyChess: GameEngine {
    private val myGame: MyGame = MyGame()
    private var currentPlayer = WHITE

    override fun applyMove(move: Move): MoveResult {
        val movement = Connector.toMovement(move, myGame.board)

        return try {
            myGame.makemovement(movement)
            currentPlayer = if (currentPlayer == WHITE) BLACK else WHITE
            NewGameState(Connector.getPieces(myGame.game), currentPlayer)
        } catch (e: Exception) {
            if (movement.oldPos == null) {
                InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
            } else if (Connector.adaptColour(movement.oldPos.color) != currentPlayer) {
                InvalidMove("Piece does not belong to the current player")
            } else if (Connector.adaptColour(movement.newPos.color) == currentPlayer) {
                InvalidMove("There is a piece in (${move.to.row}, ${move.to.column})")
            } else {
                InvalidMove("Invalid move")
            }
        }}

    override fun init(): InitialState {
        return InitialState(Connector.adaptBoard(myGame.board),(Connector.getPieces(myGame.Game1())),WHITE)
    }
}
class MovePrinter : PieceMovedListener {
    override fun onMovePiece(from: Position, to: Position) {
        print("Move: from ")
        print(from)
        print(" to ")
        println(to)
    }
}
