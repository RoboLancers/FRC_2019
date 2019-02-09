package frc.robot.subsystems.manipulators.hatch;

import com.robolancers.lib.wrappers.sensors.LinkedLimitSwitches;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchEjectorState;

public class HatchEjector extends Subsystem {
    private static HatchEjector instance;

    private Solenoid ejector;

    private LinkedLimitSwitches hatchDetector;

    private HatchEjector() {
        ejector = new Solenoid(RobotMap.HATCH.EJECTOR);

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
        return hatchDetector.get();
    }

    @Override
    protected void initDefaultCommand() { }
}
