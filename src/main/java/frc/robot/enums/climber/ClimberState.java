package frc.robot.enums.climber;

public enum ClimberState {
    UP(2000), DOWN(0);

    private int position;

    ClimberState(int position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }
}
