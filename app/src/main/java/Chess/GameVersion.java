package Chess;

import java.util.List;
import Movements.movementValidator;


public class GameVersion {
    private final String version;
    private List<List<movementValidator>> movements;

    public GameVersion(String version, List<List<movementValidator>> movements) {
        this.movements = movements;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
