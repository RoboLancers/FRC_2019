package frc.robot.subsystems.manipulators;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Hatch extends Subsystem {

    private DoubleSolenoid Hatch1, Hatch2, Hatch3;
    private static Hatch instance;

    public Hatch() {
        Hatch1 = new DoubleSolenoid(RobotMap.HATCH1_FORWARD, RobotMap.HATCH1_REVERSE);
        Hatch2 = new DoubleSolenoid(RobotMap.HATCH2_FORWARD, RobotMap.HATCH2_REVERSE);
        Hatch3 = new DoubleSolenoid(RobotMap.HATCH3_FORWARD, RobotMap.HATCH3_REVERSE);
    }

    public void setBack() {
        Hatch1.set(DoubleSolenoid.Value.kReverse);
        Hatch2.set(DoubleSolenoid.Value.kReverse);
        Hatch3.set(DoubleSolenoid.Value.kReverse);
    }

    public void setForward() {
        Hatch1.set(DoubleSolenoid.Value.kForward);
        Hatch2.set(DoubleSolenoid.Value.kForward);
        Hatch3.set(DoubleSolenoid.Value.kForward);
    }

    public synchronized static Hatch getInstance() {
        if(instance == null) {
            instance = new Hatch();
        }
        return instance;
    }


    @Override
    protected void initDefaultCommand() {

    }
}
