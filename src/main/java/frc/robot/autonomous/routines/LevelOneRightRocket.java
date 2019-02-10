package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneRightRocket extends CommandGroup {
    public LevelOneRightRocket() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightStartToRightNearRocket, true));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightNearRocketToRightUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightUTurnToRightLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightLoadingStationToRightRocketUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightUTurnToRightFarRocket));
        addSequential(new AutoHatchRelease());
    }
}
