package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.hatch.AutoHatchRelease;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneRightRocket extends CommandGroup {
    LevelOneRightRocket() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightStartToRightNearRocket));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightNearRocketToRightUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightUTurnToRightLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightLoadingStationToRightRocketUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightUTurnToRightFarRocket));
        addSequential(new AutoHatchRelease());
    }
}
