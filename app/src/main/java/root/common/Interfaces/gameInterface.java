package root.common.Interfaces;

import root.common.Game;
import root.common.Board;
import root.common.Player;
import root.common.Piece;
import root.common.Position;

import java.util.List;

public interface gameInterface {
    public List<Piece> getPiecies ();
    public Board getBoard();
    public Game move(Position oldPos, Position newpos);
    public List<Player> getChessPlayers();
    public boolean validateVictory(List<Player> player, Board board);
}
