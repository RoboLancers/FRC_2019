package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneFrontCargoLeftStation extends CommandGroup {
    public LevelOneFrontCargoLeftStation(StartingPosition startingPosition) {
        if(startingPosition == StartingPosition.LEVEL_1_LEFT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.leftStartToFrontLeftCargo, true));
        }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.centerStartToFrontLeftCargo, true));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.rightStartToFrontLeftCargo, true));
        }

        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.frontLeftCargoToLeftUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.leftUTurnToLeftLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.leftLoadingStationToLeftCargoUTurn));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.LEFT.leftUTurnToFrontRightCargo));
        addSequential(new AutoHatchRelease());
    }
}
