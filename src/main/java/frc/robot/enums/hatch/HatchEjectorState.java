package frc.robot.enums.hatch;

public enum HatchEjectorState {
    IN(false), OUT(true);

    private boolean value;

    HatchEjectorState(boolean value) {

        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}