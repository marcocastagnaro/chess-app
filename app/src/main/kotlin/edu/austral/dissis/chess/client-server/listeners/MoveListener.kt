package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.`client-server`.Server
import edu.austral.dissis.chess.gui.Move
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class MoveListener(val server: Server): MessageListener<Move> {
    override fun handleMessage(message: Message<Move>) {
        server.handleMove(message.payload)
    }

}