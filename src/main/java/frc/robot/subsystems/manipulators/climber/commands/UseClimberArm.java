package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArm extends Command {
    private ClimberState climberState;

    public UseClimberArm(ClimberState climberState) {
        requires(ClimberArm.getInstance());
        this.climberState = climberState;
    }

    @Override
    public void execute() {
        ClimberArm.getInstance().set(climberState);
    }

    @Override
    protected boolean isFinished() {
        return ClimberArm.getInstance().getClosedLoopError() < Constants.CLIMBER.ALLOWABLE_ARM_ERROR;
    }
}
