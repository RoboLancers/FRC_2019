package frc.robot.enums.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum LiftoffState {
    UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse);

    public DoubleSolenoid.Value value;

    LiftoffState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
