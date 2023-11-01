package root.common;

import root.common.Interfaces.turn;

import java.util.List;

public class Turn implements root.common.Interfaces.turn{

    public void nextTurn(List<ChessPlayer> chessPlayers){
        for (int i = 0; i <= chessPlayers.toArray().length - 1; i++) {
            if (chessPlayers.get(i).getTurn()) {
                chessPlayers.get(i).changeTurn();
                if (i + 1 == chessPlayers.toArray().length) {
                    chessPlayers.get(0).changeTurn();
                    break;
                } else {
                    chessPlayers.get(i + 1).changeTurn();
                    break;
                }
            }
        }
    }

    @Override
    public ChessPlayer getCurrent(List<ChessPlayer> chessPlayers){
        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
    }
}
