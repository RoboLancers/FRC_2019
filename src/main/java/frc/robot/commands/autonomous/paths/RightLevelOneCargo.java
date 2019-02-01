package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.autonomous.commands.AutoHatchRelease;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchEjector;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class RightLevelOneCargo extends CommandGroup {
    RightLevelOneCargo() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightLevelOneFrontRightCargo));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.RightCargoToRightHatch));
        addSequential(new UseHatchEjector(HatchEjectorState.IN));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.RightHatchToLeftCargo));
        addSequential(new AutoHatchRelease());
    }
}
