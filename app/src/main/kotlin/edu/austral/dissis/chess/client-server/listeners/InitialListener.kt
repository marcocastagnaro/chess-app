package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.`client-server`.Client
import edu.austral.dissis.chess.gui.InitialState
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InitialListener(private var client: Client) : MessageListener<InitialState> {
    override fun handleMessage(message: Message<InitialState>) {
        client.handleInit(message.payload);
    }
}