package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.hatch.AutoHatchRelease;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneLeftRocket extends CommandGroup {
    LevelOneLeftRocket() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftStartToLeftNearRocket));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftNearRocketToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftUTurnToLeftLoadingStation));
//        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightLoadingStationToRightUTurn));
//        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightUTurnToFrontLeftCargo));
        addSequential(new AutoHatchRelease());

    }
}
