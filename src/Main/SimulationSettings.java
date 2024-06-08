package Main;

public class SimulationSettings {
    private final int numberOfWarriorRed;
    private final int numberOfWarriorBlue;
    private final int numberOfBuilders;
    private final int numberOfBombs;

    public SimulationSettings(int numberOfWarriorRed, int numberOfWarriorBlue, int numberOfBuilders, int numberOfBombs) {
        this.numberOfWarriorRed = numberOfWarriorRed;
        this.numberOfWarriorBlue = numberOfWarriorBlue;
        this.numberOfBuilders = numberOfBuilders;
        this.numberOfBombs = numberOfBombs;
    }

    public int getNumberOfWarriorsRed() {
        return numberOfWarriorRed;
    }
    public int getNumberOfWarriorsBlue() {
        return numberOfWarriorBlue;
    }


    public int getNumberOfBuilders() {
        return numberOfBuilders;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }
}
