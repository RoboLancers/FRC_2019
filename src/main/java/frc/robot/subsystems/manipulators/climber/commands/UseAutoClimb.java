package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.misc.Sensors;

public class UseAutoClimb extends CommandGroup {
    public UseAutoClimb() {
        addSequential(new UseClimberArm(ClimberState.DOWN));
        addSequential(new UseLiftoffPiston(LiftoffState.DOWN));
        if (Sensors.getInstance().getPitch() >= Constants.CLIMBER.CLIMBING_ANGLE) {
            addSequential(new UseDrivetrain());
            addParallel(new UseClimberArm(ClimberState.UP));
        }
        addSequential(new UseLiftoffPiston(LiftoffState.UP));
    }
}
