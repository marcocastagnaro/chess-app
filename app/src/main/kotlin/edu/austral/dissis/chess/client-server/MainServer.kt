package edu.austral.dissis.chess.`client-server`

import edu.austral.dissis.chess.MyGame
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val gameEngine = MyGame()
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val server = NettyServerBuilder.createDefault()
    private val serverBuilder : Server = Server(gameEngine, server, root)

    companion object {
        const val GameTitle = "Server"
    }
    init {
        serverBuilder.start()
    }


    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}