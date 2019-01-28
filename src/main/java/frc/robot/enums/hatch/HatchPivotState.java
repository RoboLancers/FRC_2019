package frc.robot.enums.hatch;

public enum HatchPivotState {
    DEFENSE(0), SCORING(3000), FLOOR(9000);

    private double position;

    HatchPivotState(int position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }
}
