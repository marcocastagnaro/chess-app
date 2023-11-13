package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ValidMoveListener (private var client: edu.austral.dissis.chess.`client-server`.Client) : MessageListener<NewGameState> {

    override fun handleMessage(message: Message<NewGameState>) {
        client.handleValidMove(message.payload);
    }
}