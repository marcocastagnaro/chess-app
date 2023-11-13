package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class GameOverListener (private var client: edu.austral.dissis.chess.`client-server`.Client) : MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        client.handleGameOver(message.payload)
    }
}