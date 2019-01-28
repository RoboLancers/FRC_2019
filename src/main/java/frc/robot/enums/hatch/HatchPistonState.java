package frc.robot.enums.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum HatchPistonState {
    IN(DoubleSolenoid.Value.kForward), OUT(DoubleSolenoid.Value.kReverse);

    private DoubleSolenoid.Value value;

    HatchPistonState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
