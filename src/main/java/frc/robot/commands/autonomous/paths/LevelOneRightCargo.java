package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPiston;
import frc.robot.enums.hatch.HatchPistonState;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.hatch.HatchPiston;

public class LevelOneRightCargo extends CommandGroup {

    public LevelOneRightCargo() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.levelOneRightCargo));
        addSequential(new UseHatchPiston(HatchPistonState.OUT));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightCargoToLeftHatch));
        addSequential(new UseHatchPiston(HatchPistonState.IN));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftHatchToLeftCargo));

    }
}
