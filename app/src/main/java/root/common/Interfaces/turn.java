package root.common.Interfaces;

import root.common.Board;
import root.common.Player;
import root.common.Position;

import java.util.List;

public interface turn {
    public List<Player> nextTurn(List<Player> players, Board board, Position oldpos, Position newpos);
    public Player getCurrent(List<Player> players);

}
