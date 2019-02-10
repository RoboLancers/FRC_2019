package frc.robot.subsystems.manipulators.hatch.enums;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum HatchEjectorState {
    RETRACT(DoubleSolenoid.Value.kReverse), EJECT(DoubleSolenoid.Value.kForward);

    private DoubleSolenoid.Value value;

    HatchEjectorState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
