package root.chess;

import root.common.Board;
import root.common.Interfaces.Turn;
import root.common.Player;
import root.common.Position;

import java.util.List;

public class RandomTurn implements Turn {
    @Override
    public List<Player> nextTurn(List<Player> players, Board board, Position oldpos, Position newpos) {
        List<Player> newList = players;
        int num = players.size();
        int random = (int) (Math.random() * num);
        for (int i = 0; i <= players.toArray().length - 1; i++) {
            if (players.get(i).getTurn()) {
                newList.set(i, players.get(i).changeTurn());
                break;
            }
        }
        newList.set(random, players.get(random).changeTurn());
        return newList;
    }

    @Override
    public Player getCurrent(List<Player> players) {
        return players.stream().filter(Player::getTurn).findFirst().get();
    }
}
