package frc.robot.enums.cargo;

public enum CargoBlockState {
    UP(true), DOWN(false);

    private boolean value;

    CargoBlockState(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
