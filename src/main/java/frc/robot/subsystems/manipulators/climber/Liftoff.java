package frc.robot.subsystems.manipulators.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.climber.LiftoffState;

public class Liftoff extends Subsystem {

    private DoubleSolenoid liftOff;
    private static Liftoff instance;

    public Liftoff() {
        liftOff = new DoubleSolenoid(RobotMap.LIFTOFF_FORWARD, RobotMap.LIFTOFF_REVERSE);
    }

    public void set(LiftoffState liftoffState) {
        liftOff.set(liftoffState.getValue());
    }

    public synchronized static Liftoff getInstance() {
        if (instance == null) {
            instance = new Liftoff();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
