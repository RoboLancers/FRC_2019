package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class HatchEjector extends Subsystem implements Loggable {
    private static HatchEjector instance;

    private DoubleSolenoid ejector;
    private DigitalInput hatchDetector;

    private HatchEjector() {
        ejector = new DoubleSolenoid(RobotMap.HATCH.MODULE, RobotMap.HATCH.EJECTOR_IN, RobotMap.HATCH.EJECTOR_OUT);

        hatchDetector = new DigitalInput(RobotMap.HATCH.LIMIT_SWITCH);
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

    @Log.ToString(name = "Hatch Ejector State")
    public HatchEjectorState get(){
        return ejector.get() == HatchEjectorState.EJECT.getValue() ? HatchEjectorState.EJECT : HatchEjectorState.RETRACT;
    }

    @Log.BooleanBox(name = "Hatch Detected")
    public boolean hasHatch() {
        return !hatchDetector.get();
    }

    @Override
    protected void initDefaultCommand() { }

    @Override
    public String configureLogName(){
        return "Hatch";
    }
}
