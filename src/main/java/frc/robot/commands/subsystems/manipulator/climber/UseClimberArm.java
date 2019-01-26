package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.climber.ClimberState;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArm extends InstantCommand {
    private ClimberState climberState;

    public UseClimberArm(ClimberState climberState) {
        requires(ClimberArm.getInstance());
        this.climberState = climberState;
    }

    @Override
    public void initialize() {
        if (climberState == ClimberState.UP) {
            ClimberArm.getInstance().set(ClimberState.UP);
        } else {
            ClimberArm.getInstance().set(ClimberState.DOWN);
        }
    }
}
