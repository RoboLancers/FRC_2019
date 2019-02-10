package frc.robot.subsystems.manipulators.hatch;

import com.robolancers.lib.wrappers.sensors.LinkedLimitSwitches;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;

public class HatchEjector extends Subsystem {
    private static HatchEjector instance;

    private DoubleSolenoid ejector;

    private LinkedLimitSwitches hatchDetector;

    private HatchEjector() {
        ejector = new DoubleSolenoid(RobotMap.HATCH.EJECTOR_OUT, RobotMap.HATCH.EJECTOR_IN);

        hatchDetector = new LinkedLimitSwitches(RobotMap.HATCH.LIMIT_SWITCH_ONE, RobotMap.HATCH.LIMIT_SWITCH_TWO, RobotMap.HATCH.LIMIT_SWITCH_THREE, RobotMap.HATCH.LIMIT_SWITCH_FOUR);
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

    public HatchEjectorState get(){
        return ejector.get() == HatchEjectorState.EJECT.getValue() ? HatchEjectorState.EJECT : HatchEjectorState.RETRACT;
    }

    public boolean hasHatch() {
        return !hatchDetector.get();
    }

    @Override
    protected void initDefaultCommand() { }
}
