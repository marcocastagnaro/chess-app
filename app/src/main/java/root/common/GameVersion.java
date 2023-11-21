package root.common;

import java.util.List;

import root.common.Interfaces.SpecialRules;
import root.common.Interfaces.Validators;
import root.common.Interfaces.VictoryValidator;


public class GameVersion {
    private final String version;
    private final List <VictoryValidator> victoryInt;
    private List <SpecialRules> rules;

    private List<Validators> validator;
    public GameVersion(String version, List <VictoryValidator> victoryInt, List<Validators> validator, List<SpecialRules> rules) {
        this.version = version;
        this.victoryInt = victoryInt;
        this.validator = validator;
        this.rules = rules;
    }
    public List<SpecialRules> getRules() {
        return rules;
    }

    public List <VictoryValidator> getVictoryInt() {
        return victoryInt;
    }

    public List<Validators> getValidators() {
        return validator;
    }
}
