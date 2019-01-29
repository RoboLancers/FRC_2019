package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.enums.climber.ClimberState;

public class AutoClimb extends CommandGroup {
    public AutoClimb() {
        addSequential(new UseClimberArm(ClimberState.DOWN));
        addSequential(new AutoLiftOffPiston());
    }
}
