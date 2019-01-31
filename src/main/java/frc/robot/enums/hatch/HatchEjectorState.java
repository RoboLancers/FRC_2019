package frc.robot.enums.hatch;

public enum HatchEjectorState {
    RETRACT(false), EJECT(true);

    private boolean value;

    HatchEjectorState(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
