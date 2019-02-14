package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class LevelOneFrontCargoRightStation extends CommandGroup {
    public LevelOneFrontCargoRightStation(StartingPosition startingPosition) {
        if(startingPosition == StartingPosition.LEVEL_1_LEFT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.leftStartToFrontRightCargo, true));
        }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.centerStartToFrontRightCargo, true));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.rightStartToFrontRightCargo, true));
        }

        addSequential(new AutoHatchRelease());
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.frontRightCargoToRightTurn));
        addSequential(new Turning(90));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.rightTurnToRightLoadingStation));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.rightLoadingStationToRightTurn));
        addSequential(new Turning(-90));
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.CARGOSHIP.RIGHT.rightTurnToFrontLeftCargo));
        addSequential(new AutoHatchRelease());
    }
}
