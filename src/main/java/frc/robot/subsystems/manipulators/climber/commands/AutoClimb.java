package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;

public class AutoClimb extends CommandGroup {
    public AutoClimb() {
        addSequential(new UseClimberArm(ClimberState.DOWN));
        addSequential(new AutoLiftOffPiston());
    }
}
