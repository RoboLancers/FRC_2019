package frc.robot.subsystems.manipulators.hatch.enums;

public enum HatchPivotState {
    DEFENSE(0), SCORING(230), GETTING(240), FLOOR(870);

    private double position;

    HatchPivotState(int position) {
        this.position = position;
    }

    public double getPosition() {
        return position;
    }
}
