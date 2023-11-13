package edu.austral.dissis.chess.`client-server`

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    launch(client1::class.java)
}

class client1  :  Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val client = Client(root)
    init {
        client.start()
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Client1"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}