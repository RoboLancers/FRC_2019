package frc.robot.subsystems.manipulators.hatch.enums;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum HatchHolderState {
    RELEASE(DoubleSolenoid.Value.kReverse), HOLD(DoubleSolenoid.Value.kForward);

    private DoubleSolenoid.Value value;

    HatchHolderState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
