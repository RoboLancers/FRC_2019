package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchEjectorState;

public class HatchEjector extends Subsystem {
    private static HatchEjector instance;

    private Solenoid ejector;

    private DigitalInput hatchDetector1, hatchDetector2, hatchDetector3, hatchDetector4;

    private HatchEjector() {
        ejector = new Solenoid(RobotMap.HATCH.EJECTOR, RobotMap.HATCH.EJECTOR);

        hatchDetector1 = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT_ONE);
        hatchDetector2 = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT_TWO);
        hatchDetector3 = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT_THREE);
        hatchDetector4 = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH_PORT_FOUR);
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

    public boolean hasHatch() {
        return  hatchDetector1.get() ||
                hatchDetector2.get() ||
                hatchDetector3.get() ||
                hatchDetector4.get();
    }

    @Override
    protected void initDefaultCommand() { }
}
