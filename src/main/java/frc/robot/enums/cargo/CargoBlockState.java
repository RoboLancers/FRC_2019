package frc.robot.enums.cargo;

public enum CargoBlockState {
    BLOCK(true), UNBLOCK(false);

    private boolean value;

    CargoBlockState(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
