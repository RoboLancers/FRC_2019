package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.hatch.AutoHatchRelease;
import frc.robot.enums.autonomous.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.Trajectory;

public class LevelOneFrontCargo extends CommandGroup {
    public LevelOneFrontCargo(StartingPosition startingPosition) {
        if(startingPosition == StartingPosition.LEVEL_1_LEFT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftStartToFrontLeftCargo));
        }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.centerStartToFrontLeftCargo));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightStartToFrontLeftCargo));
        }

        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.frontLeftCargoToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftUTurnToLeftLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftLoadingStationToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftUTurnToFrontRightCargo));
        addSequential(new AutoHatchRelease());
    }
}
