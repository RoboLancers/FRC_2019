package frc.robot.enums.climber;

public enum ClimberState {
    UP(0), DOWN(257);

    private int position;

    ClimberState(int position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }
}
