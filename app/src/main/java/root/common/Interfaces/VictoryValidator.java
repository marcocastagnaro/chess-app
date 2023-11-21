package root.common.Interfaces;

import root.common.Board;
import root.common.Player;

import java.util.List;

public interface VictoryValidator {
    public boolean validateVictory(List<Player> player, Board board);
}
