package root.common.Interfaces;
import root.common.Board;
import root.common.Position;
public interface MovementValidator {
    public boolean validateMove(Position oldPos, Position newPos);

    public boolean obstacle(Position oldPos, Position newPos, Board board);

}
