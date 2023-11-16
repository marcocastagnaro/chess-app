package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ServerInitialConnection(val server: edu.austral.dissis.chess.`client-server`.Server) : MessageListener<Unit> {
    override fun handleMessage(message: Message<Unit>) {
        server.handleInitialConnection()
    }
}