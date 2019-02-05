package frc.robot.enums.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;

@SuppressWarnings("unused")
public enum LiftoffState {
    UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse), NEUTRAL(DoubleSolenoid.Value.kOff);

    private DoubleSolenoid.Value value;

    LiftoffState(DoubleSolenoid.Value value) {
        this.value = value;
    }

    public DoubleSolenoid.Value getValue() {
        return value;
    }
}
