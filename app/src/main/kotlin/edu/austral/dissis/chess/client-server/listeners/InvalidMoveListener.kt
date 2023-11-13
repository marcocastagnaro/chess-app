package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InvalidMoveListener (private var client: edu.austral.dissis.chess.`client-server`.Client):MessageListener<InvalidMove> {
    override fun handleMessage(message: Message<InvalidMove>) {
        client.handleInvalidmove(message.payload)
    }
}