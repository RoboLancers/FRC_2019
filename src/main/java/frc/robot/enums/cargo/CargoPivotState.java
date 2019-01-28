package frc.robot.enums.cargo;


public enum CargoPivotState {
    UP(true), DOWN(false);

    private boolean value;

    CargoPivotState(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
