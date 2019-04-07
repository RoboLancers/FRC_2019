package frc.robot.subsystems.manipulators.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;

public class LiftoffPiston extends Subsystem {
    private static LiftoffPiston instance;

    private DoubleSolenoid liftOff;

    private LiftoffState state;

    private LiftoffPiston() {
        liftOff = new DoubleSolenoid(RobotMap.CLIMBER.LIFTOFF_PISTON_UP, RobotMap.CLIMBER.LIFTOFF_PISTON_DOWN);
        state = LiftoffState.NEUTRAL;
    }

    public synchronized static LiftoffPiston getInstance() {
        if (instance == null) {
            instance = new LiftoffPiston();
        }
        return instance;
    }

    public void set(LiftoffState liftoffState) {
        liftOff.set(liftoffState.getValue());
        state = liftoffState;
    }

    public LiftoffState get() {
        return state;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
