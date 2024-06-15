package Main;

/**
 * The SimulationSettings class holds the settings for the simulation, including
 * the number of each type of entity.
 */
public class SimulationSettings {
    //all settings for number of Entity
    private final int numberOfWarriorRed;
    private final int numberOfWarriorBlue;
    private final int numberOfBuilders;
    private final int numberOfBombs;

    /**
     * Constructor for the SimulationSettings.
     *
     * @param numberOfWarriorRed the number of red warriors
     * @param numberOfWarriorBlue the number of blue warriors
     * @param numberOfBuilders the number of builders
     * @param numberOfBombs the number of bombs
     */
    public SimulationSettings(int numberOfWarriorRed, int numberOfWarriorBlue, int numberOfBuilders, int numberOfBombs) {
        this.numberOfWarriorRed = numberOfWarriorRed;
        this.numberOfWarriorBlue = numberOfWarriorBlue;
        this.numberOfBuilders = numberOfBuilders;
        this.numberOfBombs = numberOfBombs;
    }

    /**
     * Gets the number of red warriors.
     *
     * @return the number of red warriors
     */
    public int getNumberOfWarriorsRed() {
        return numberOfWarriorRed;
    }

    /**
     * Gets the number of blue warriors.
     *
     * @return the number of blue warriors
     */
    public int getNumberOfWarriorsBlue() {
        return numberOfWarriorBlue;
    }

    /**
     * Gets the number of builders.
     *
     * @return the number of builders
     */
    public int getNumberOfBuilders() {
        return numberOfBuilders;
    }

    /**
     * Gets the number of bombs.
     *
     * @return the number of bombs
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }
}
