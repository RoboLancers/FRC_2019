package frc.robot.subsystems.manipulators.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class LiftoffPiston extends Subsystem implements Loggable {
    private static LiftoffPiston instance;

    private DoubleSolenoid liftOff;

    private LiftoffPiston() {
        liftOff = new DoubleSolenoid(RobotMap.CLIMBER.LIFTOFF_PISTON_UP, RobotMap.CLIMBER.LIFTOFF_PISTON_DOWN);
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

    @Log.ToString(name = "Liftoff State", rowIndex = 0, columnIndex = 6, width = 2, height = 1)
    public LiftoffState get(){
        return liftOff.get() == LiftoffState.UP.getValue() ? LiftoffState.UP : LiftoffState.DOWN;
    }

    @Override
    protected void initDefaultCommand() { }

    @Override
    public String configureLogName(){
        return "Climber";
    }
}
