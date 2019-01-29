package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.autonomous.commands.AutoHatchRelease;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchEjector;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneFrontCargo extends CommandGroup {
    LevelOneFrontCargo() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.levelOneRightCargo));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.RightCargoToLeftHatch));
        addSequential(new UseHatchEjector(HatchEjectorState.IN));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftHatchToLeftCargo));
        addSequential(new AutoHatchRelease());
    }
}
