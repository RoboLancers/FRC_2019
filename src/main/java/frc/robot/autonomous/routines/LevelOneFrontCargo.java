package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.manipulators.hatch.commands.ToggleHatchHolder;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class LevelOneFrontCargo extends CommandGroup {
    public LevelOneFrontCargo(StartingPosition startingPosition, Objective objective){
        if(objective == Objective.FRONT_CARGO_LEFT){
            if(startingPosition == StartingPosition.LEVEL_1_LEFT){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftStartToFrontLeftCargo, true));
            }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.centerStartToFrontLeftCargo, true));
            }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.rightStartToFrontLeftCargo, true));
            }

            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.frontLeftCargoToLeftTurn));
            addSequential(new Turning(-90));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftTurnToLeftLoadingStation));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftLoadingStationToLeftTurn));
            addSequential(new Turning(90));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftTurnToFrontRightCargo));
            addSequential(new AutoHatchRelease());
        }else if(objective == Objective.FRONT_CARGO_RIGHT){
            if(startingPosition == StartingPosition.LEVEL_1_LEFT){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.leftStartToFrontRightCargo, true));
            }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.centerStartToFrontRightCargo, true));
            }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
                addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightStartToFrontRightCargo, true));
            }

            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.frontRightCargoToRightTurn));
            addSequential(new Turning(90));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightTurnToRightLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightLoadingStationToRightTurn));
            addSequential(new Turning(-90));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightTurnToFrontLeftCargo));
            addSequential(new AutoHatchRelease());
        }
    }
}
