package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.ClimberState;
import frc.robot.enums.LiftoffState;
import frc.robot.subsystems.manipulators.Climber;
import frc.robot.subsystems.manipulators.Liftoff;

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
