package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchEjectorState;

public class HatchEjector extends Subsystem {
    private static HatchEjector instance;
    private DigitalInput hatchDetector;
    private Solenoid ejector;

    private HatchEjector() {
        ejector = new Solenoid(RobotMap.HATCH.EJECTOR, RobotMap.HATCH.EJECTOR);
        hatchDetector = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT);
    }

    public synchronized static HatchEjector getInstance() {
        if (instance == null) {
            instance = new HatchEjector();
        }
        return instance;
    }

    public void set(HatchEjectorState hatchEjectorState) {
        ejector.set(hatchEjectorState.getValue());
    }

    public boolean getLimitSwitch() {
        return hatchDetector.get();
    }

    @Override
    protected void initDefaultCommand() {
    }
}
