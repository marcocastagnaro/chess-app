package Common.Interfaces;
import Common.Board;
import Common.Position;
public interface movementValidator {
    public boolean validateMove(Position oldPos, Position newPos);

    public boolean obstacle(Position oldPos, Position newPos, Board board);

}
