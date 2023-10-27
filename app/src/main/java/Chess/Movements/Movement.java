package Chess.Movements;
import Common.Position;

public class Movement {
    private Position oldPos;
    private Position newPos;
    public Movement(Position oldPos, Position newPos) {
        this.oldPos = oldPos;
        this.newPos = newPos;
    }

    public Position getOldPos() {
        return oldPos;
    }

    public Position getNewPos() {
        return newPos;
    }

}
