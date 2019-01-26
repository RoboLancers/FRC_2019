package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.subsystems.manipulators.climber.Climber;
import frc.robot.subsystems.manipulators.climber.Liftoff;

public class Elevate extends InstantCommand {

    private LiftoffState liftoffState;
    private ClimberState climberState;

    public Elevate(LiftoffState liftoffState, ClimberState climberState) {
        requires(Climber.getInstance());
        this.climberState = climberState;

        requires(Liftoff.getInstance());
        this.liftoffState = liftoffState;

    }

    @Override
    protected void initialize(){
        Climber.getInstance().stop();
        if(liftoffState == LiftoffState.UP){
            Liftoff.getInstance().takeOff();
        }else{
            Liftoff.getInstance().land();
        }

        Climber.getInstance().set(climberState);
    }

    @Override
    protected void end(){
        Climber.getInstance().stop();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
