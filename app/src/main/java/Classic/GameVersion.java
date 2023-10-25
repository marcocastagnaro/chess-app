package Classic;

import java.util.ArrayList;
import java.util.List;

import Classic.Interfaces.specialMovementValidator;
import Classic.Interfaces.victoryValidator;
import Classic.victory.checkValidator;


public class GameVersion {
    private final String version;
    private checkValidator checkval;
    private final victoryValidator victoryInt;
    private List <specialMovementValidator> specialMovementValidators = new ArrayList<>();

    public GameVersion(String version, victoryValidator victoryInt) {
        this.version = version;
        this.victoryInt = victoryInt;
    }

    public void setCheckval(checkValidator checkval) {
        this.checkval = checkval;
    }

    public List<specialMovementValidator> getSpecialMovementValidators() {
        return specialMovementValidators;
    }

    public void addSpecialMovementValidators(specialMovementValidator specialMovementVal){
        this.specialMovementValidators.add(specialMovementVal);
    }
    public boolean hasCheckValidator(){
        return checkval != null;
    }

    public checkValidator getCheckval() {
        return checkval;
    }

    public victoryValidator getVictoryInt() {
        return victoryInt;
    }

    public String getVersion() {
        return version;
    }
}
