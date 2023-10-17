package Interfaces;
import Chess.Board;
import Chess.Position;
public interface movementValidator {
    public boolean validateMove(Position oldPos, Position newPos);

    public boolean obstacle(Position oldPos, Position newPos, Board board);

}
