package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;

public class UseLiftoffPiston extends Command {
    private LiftoffState liftoffState;

    public UseLiftoffPiston(LiftoffState liftoffState){
        requires(LiftoffPiston.getInstance());
        this.liftoffState = liftoffState;
    }

    @Override
    protected void execute(){
        LiftoffPiston.getInstance().set(liftoffState);
    }

    @Override
    protected boolean isFinished() {
        return LiftoffPiston.getInstance().get() == LiftoffState.DOWN;
    }
}
