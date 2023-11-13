package edu.austral.dissis.chess.`client-server`.listeners

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.chess.gui.MoveResult
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class Sendmove (private var client: edu.austral.dissis.chess.`client-server`.Client) : GameEventListener {
    override fun handleMove(move: Move) {
        client.handleMove(move)
    }

}