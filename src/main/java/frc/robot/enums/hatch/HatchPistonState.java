package frc.robot.enums.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public enum HatchPistonState {
    IN(true), OUT(false);

    private boolean value;

    HatchPistonState(boolean value) {

        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
