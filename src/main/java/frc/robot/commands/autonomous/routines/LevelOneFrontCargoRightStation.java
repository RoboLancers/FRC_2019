package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.hatch.AutoHatchRelease;
import frc.robot.enums.autonomous.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneFrontCargoRightStation extends CommandGroup {
    public LevelOneFrontCargoRightStation(StartingPosition startingPosition) {
        if(startingPosition == StartingPosition.LEVEL_1_LEFT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.LeftStartToFrontRightCargo));
        }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.centerStartToFrontRightCargo));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.rightStartToFrontRightCargo));
        }

        addSequential(new AutoHatchRelease());
        //addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.frontRightCargoToRightUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftUTurnToLeftLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftLoadingStationToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftUTurnToFrontRightCargo));
        addSequential(new AutoHatchRelease());

    }
}
