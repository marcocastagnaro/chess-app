package edu.austral.dissis.chess.`client-server`

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.`client-server`.listeners.*
import edu.austral.dissis.chess.gui.*
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import java.net.InetSocketAddress

class Client(private val gameView: GameView, /*private val color: PlayerColor*/) {
    private lateinit var client: Client

    fun start() {
        client = build()
        client.connect()
        client.send(Message("init", Unit))
        gameView.addListener(Sendmove(this))
    }
    fun handleMove (payload: Move){
        client.send(Message("move", payload))
    }
//    fun checkTurn () : boolean{
//        if (c){
//            return true
//        }
//    }
    private fun build() : Client {
        return NettyClientBuilder.createDefault()
            .withAddress(InetSocketAddress(8080))
            .addMessageListener(
                "initResult", object : TypeReference<Message<InitialState>>() {},
                InitialListener(this) //listener para conectarse. (se conecta el cliente)
            )
            .addMessageListener(
                "valid",
                object : TypeReference<Message<NewGameState>> () {},
                ValidMoveListener(this) //listener para los mensajes de movimientos validos.
            )
            .addMessageListener(
                "invalid",
                object : TypeReference<Message<InvalidMove>> () {},
                InvalidMoveListener(this) //listener para los mensajes de movimientos invalidos.
            )
            .addMessageListener(
                "gameOver",
                object : TypeReference<Message<GameOver>> () {},
                GameOverListener(this) //listener para los mensajes de fin de juego.
            )
            .build()
    }
    fun handleInit(payload: InitialState) {
        gameView.handleInitialState(payload)
    }
    fun handleValidMove(payload: NewGameState) {
        gameView.handleMoveResult(payload)
    }
    fun handleInvalidmove(payload: InvalidMove){
        gameView.handleMoveResult(payload)
    }

    fun handleGameOver (payload: GameOver){
        gameView.handleMoveResult(payload)
    }
}