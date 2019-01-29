package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.climber.ClimberState;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArm extends Command {
    private ClimberState climberState;

    public UseClimberArm(ClimberState climberState) {
        requires(ClimberArm.getInstance());
        this.climberState = climberState;
    }

    @Override
    public void initialize() {
        ClimberArm.getInstance().set(climberState);
    }

    @Override
    protected boolean isFinished() {
        return ClimberArm.getInstance().getPosition() == climberState.getPosition();
    }
}
