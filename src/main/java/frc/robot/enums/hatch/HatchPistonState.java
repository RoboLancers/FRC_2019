package frc.robot.enums.hatch;

public enum HatchPistonState {
    IN(false), OUT(true);

    private boolean value;

    HatchPistonState(boolean value) {

        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
