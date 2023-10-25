package Classic.Interfaces;
import Classic.Board;
import Classic.Position;
public interface movementValidator {
    public boolean validateMove(Position oldPos, Position newPos);

    public boolean obstacle(Position oldPos, Position newPos, Board board);

}
