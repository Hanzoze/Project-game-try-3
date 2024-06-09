package Main;

public class SimulationSettings {
    private final int numberOfWarriorRed;
    private final int numberOfWarriorBlue;
    private final int numberOfBuilders;
    private final int numberOfBombs;
    private final int FPS;

    public SimulationSettings(int numberOfWarriorRed, int numberOfWarriorBlue, int numberOfBuilders, int numberOfBombs, int FPS) {
        this.numberOfWarriorRed = numberOfWarriorRed;
        this.numberOfWarriorBlue = numberOfWarriorBlue;
        this.numberOfBuilders = numberOfBuilders;
        this.numberOfBombs = numberOfBombs;
        this.FPS = FPS;
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
