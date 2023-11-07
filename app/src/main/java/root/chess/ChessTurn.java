package root.chess;

import root.common.Board;
import root.common.ChessPlayer;
import root.common.Position;

import java.util.List;

public class ChessTurn implements root.common.Interfaces.turn{

    public List<ChessPlayer> nextTurn(List<ChessPlayer> chessPlayers, Board board, Position oldPos, Position newPos){
        List<ChessPlayer> newList = chessPlayers;
        for (int i = 0; i <= chessPlayers.toArray().length - 1; i++) {
            if (chessPlayers.get(i).getTurn()) {
                newList.set(i,chessPlayers.get(i).changeTurn());
                chessPlayers.get(i).changeTurn();
                if (i + 1 == chessPlayers.toArray().length) {
                    newList.set(0,chessPlayers.get(0).changeTurn());
                    break;
                } else {
                    newList.set(i+1,chessPlayers.get(i + 1).changeTurn());
                    break;
                }
            }
        }
        return newList;
    }
    public ChessPlayer changeFirstTurn (ChessPlayer player){
        return new ChessPlayer(player.getName(), player.getColor(), true);
    }

    @Override
    public ChessPlayer getCurrent(List<ChessPlayer> chessPlayers){
        return chessPlayers.stream().filter(ChessPlayer::getTurn).findFirst().get();
    }
}
