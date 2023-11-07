package edu.austral.dissis.chess;

import root.common.Enums.Color
import root.common.Connector.Connector
import edu.austral.dissis.chess.MyMatches.Checkers
import edu.austral.dissis.chess.MyMatches.MyGame
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import root.common.Interfaces.gameInterface

class MyChess: GameEngine {
    private var myGame: gameInterface = Checkers();
    private var currentPlayer = getCurrentPlayer(myGame)

    override fun applyMove(move: Move): MoveResult {
        val movement = Connector.toMovement(move, myGame.board)
        val fromPiece = movement.oldPos.piece
        val toPiece = movement.newPos.piece

        return if (fromPiece == null)
            InvalidMove("No piece in (${move.from.row}, ${move.from.column})")
        else if (Connector.adaptColour(fromPiece.color) != currentPlayer)
            InvalidMove("Piece does not belong to the current player")
        else if (toPiece != null && Connector.adaptColour(toPiece.color) == currentPlayer)
            InvalidMove("There is a piece in (${move.to.row}, ${move.to.column})")
        else {
            val myNewGame = myGame
            myGame = myGame.move(  movement.oldPos, movement.newPos)
            if (myGame.board == myNewGame.board) {
                return InvalidMove("Invalid move")
            }
            if (myGame.validateVictory(myGame.chessPlayers, myGame.board)) {
                return GameOver(currentPlayer)
            }
            else{
                currentPlayer = getCurrentPlayer(myGame)
                NewGameState(Connector.getPieces(myGame.board), currentPlayer)
            }
        }
    }
    override fun init(): InitialState {
        return InitialState(Connector.adaptBoard(myGame.board),(Connector.getPieces(myGame.board)),WHITE)
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
public fun getCurrentPlayer (myGame: gameInterface): PlayerColor{
    for (i in 0 until myGame.getChessPlayers().size) {
        if (myGame.getChessPlayers()[i].turn) {
            return if (myGame.getChessPlayers()[i].color == Color.WHITE) WHITE else BLACK
        }
    }
    return WHITE
}
