package edu.austral.dissis.chess.`client-server`

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.MyGame
import edu.austral.dissis.chess.`client-server`.listeners.*
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder

class Server (private val game: MyGame, private val builder : ServerBuilder, private val gameview: GameView) {

    private lateinit var server: Server
    fun start() {
        server = build()
        server.start()
        gameview.handleInitialState(InitialState(game.init().boardSize, game.init().pieces, game.init().currentPlayer))
    }

    private fun build() : Server {
        return builder
            .withPort(8080)
            .addMessageListener(
                "move",
                object : TypeReference<Message<Move>> () {},
                MoveListener(this))
            .addMessageListener(
                "init",
                object : TypeReference<Message<Unit>> () {},
                ServerInitialConnection(this)
            )
            .build()
    }
    fun handleMove(move: Move) {
        val movement = game.applyMove(move)
        when (val moveResult = movement) {
            is NewGameState -> server.broadcast(Message("valid", handleNextMove(moveResult)))
            is InvalidMove -> server.broadcast(Message("invalid", InvalidMove(moveResult.reason)))
            is GameOver -> server.broadcast(Message("gameOver", GameOver(game.getWinner())))
        }
    }
    private fun handleNextMove(moveResult: MoveResult) : MoveResult {
        gameview.handleMoveResult(moveResult)
        return moveResult
    }
    fun handleInitialConnection(){
        server.broadcast(Message("initResult", InitialState(
            game.init().boardSize, game.init().pieces, game.init().currentPlayer
        )))
    }
}