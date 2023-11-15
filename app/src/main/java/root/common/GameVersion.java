package root.common;

import java.util.ArrayList;
import java.util.List;

import root.common.Interfaces.specialRules;
import root.common.Interfaces.validators;
import root.common.Interfaces.victoryValidator;


public class GameVersion {
    private final String version;
    private final List <victoryValidator> victoryInt;
    private List <specialRules> rules;

    private List<validators> validator;
    public GameVersion(String version, List <victoryValidator> victoryInt, List<validators> validator, List<specialRules> rules) {
        this.version = version;
        this.victoryInt = victoryInt;
        this.validator = validator;
        this.rules = rules;
    }


    public List<specialRules> getRules() {
        return rules;
    }

    public List <victoryValidator> getVictoryInt() {
        return victoryInt;
    }

    public String getVersion() {
        return version;
    }

    public List<validators> getValidators() {
        return validator;
    }
}
