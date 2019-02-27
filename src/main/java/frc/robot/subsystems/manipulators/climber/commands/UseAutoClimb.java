package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.drivetrain.commands.UseClimbDrive;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;

public class UseAutoClimb extends CommandGroup {
    public UseAutoClimb() {
        addSequential(new UseClimberArm(ClimberState.DOWN));
        addSequential(new UseLiftoffPiston(LiftoffState.DOWN));
        addParallel(new UseClimbDrive());
        addSequential(new UseLiftoffPiston(LiftoffState.UP));
        addSequential(new UseClimberArm(ClimberState.UP));
    }
}
