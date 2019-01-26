package frc.robot.enums.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum CargoBlockState {
    UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse);

    public DoubleSolenoid.Value value;

    CargoBlockState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
