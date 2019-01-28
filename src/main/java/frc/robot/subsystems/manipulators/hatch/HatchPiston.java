package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchPistonState;

public class HatchPiston extends Subsystem {
    private static HatchPiston instance;
    private DigitalInput hatchDetector;
    private Solenoid ejector;

    private HatchPiston() {
        ejector = new Solenoid(RobotMap.HATCH.EJECTOR, RobotMap.HATCH.EJECTOR);
        hatchDetector = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT);
    }

    public synchronized static HatchPiston getInstance() {
        if (instance == null) {
            instance = new HatchPiston();
        }
        return instance;
    }

    public void set(HatchPistonState hatchPistonState) {
        ejector.set(hatchPistonState.getValue());
    }

    public boolean get() {
        return hatchDetector.get();
    }

    @Override
    protected void initDefaultCommand() {
    }
}
