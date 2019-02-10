package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneLeftRocket extends CommandGroup {
    public LevelOneLeftRocket() {
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftStartToLeftNearRocket, true));
        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftNearRocketToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftUTurnToLeftLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftLoadingStationToLeftRocketUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftUTurnToLeftFarRocket));
        addSequential(new AutoHatchRelease());
    }
}
