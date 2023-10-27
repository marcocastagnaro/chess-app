package Common;

import java.util.ArrayList;
import java.util.List;

import Common.Interfaces.specialMovementValidator;
import Common.Interfaces.victoryValidator;
import Common.victory.checkValidator;
import Common.victory.obligatoryMovement;


public class GameVersion {
    private final String version;
    private checkValidator checkval;
    private final victoryValidator victoryInt;
    private List <specialMovementValidator> specialMovementValidators = new ArrayList<>();

    private obligatoryMovement obligatory;
    public GameVersion(String version, victoryValidator victoryInt) {
        this.version = version;
        this.victoryInt = victoryInt;
    }
    public void setObligatory (obligatoryMovement obligatory){
        this.obligatory = obligatory;
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
    public boolean hasObligatory(){
        return obligatory != null;
    }
    public obligatoryMovement getObligatory() {
        return obligatory;
    }
}
