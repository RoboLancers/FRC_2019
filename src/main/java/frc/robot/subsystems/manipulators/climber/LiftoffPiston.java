package frc.robot.subsystems.manipulators.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.climber.LiftoffState;

public class LiftoffPiston extends Subsystem {
    private static LiftoffPiston instance;
    private DoubleSolenoid liftOff;

    private LiftoffPiston() {
        liftOff = new DoubleSolenoid(RobotMap.CLIMBER.LIFTOFF_PISTON_FORWARD, RobotMap.CLIMBER.LIFTOFF_PISTON_REVERSE);
    }

    public synchronized static LiftoffPiston getInstance() {
        if (instance == null) {
            instance = new LiftoffPiston();
        }
        return instance;
    }

    public void set(LiftoffState liftoffState) {
        liftOff.set(liftoffState.getValue());
    }

    @Override
    protected void initDefaultCommand() {
    }
}
