package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.Liftoff;

public class Elevate extends InstantCommand {

    private LiftoffState liftoffState;
    private ClimberState climberState;

    public Elevate(LiftoffState liftoffState, ClimberState climberState) {
        requires(ClimberArm.getInstance());
        this.climberState = climberState;

        requires(Liftoff.getInstance());
        this.liftoffState = liftoffState;

    }

    @Override
    protected void initialize() {
        ClimberArm.getInstance().stop();
        if (liftoffState == LiftoffState.UP) {
            Liftoff.getInstance().takeOff();
        } else {
            Liftoff.getInstance().land();
        }

        ClimberArm.getInstance().set(climberState);
    }

    @Override
    protected void end() {
        ClimberArm.getInstance().stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
