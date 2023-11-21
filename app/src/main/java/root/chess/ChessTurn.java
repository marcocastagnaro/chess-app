package root.chess;

import root.common.Board;
import root.common.Interfaces.Turn;
import root.common.Player;
import root.common.Position;

import java.util.List;

public class ChessTurn implements Turn {

    public List<Player> nextTurn(List<Player> players, Board board, Position oldPos, Position newPos){
        List<Player> newList = players;
        for (int i = 0; i <= players.toArray().length - 1; i++) {
            if (players.get(i).getTurn()) {
                newList.set(i, players.get(i).changeTurn());
                if (i + 1 == players.toArray().length) {
                    newList.set(0, players.get(0).changeTurn());
                    break;
                } else {
                    newList.set(i+1, players.get(i + 1).changeTurn());
                    break;
                }
            }
        }
        return newList;
    }
    @Override
    public Player getCurrent(List<Player> players){
        return players.stream().filter(Player::getTurn).findFirst().get();
    }
}
