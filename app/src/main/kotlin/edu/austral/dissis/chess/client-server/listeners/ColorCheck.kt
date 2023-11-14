package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.`client-server`.Client
import edu.austral.dissis.chess.gui.PlayerColor
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ColorCheck (private val client: Client) : MessageListener<PlayerColor> {
    override fun handleMessage(message: Message<PlayerColor>) {
        TODO("Not yet implemented")
    }
}