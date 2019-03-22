package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class LevelOneCargoRocket extends CommandGroup {
    public LevelOneCargoRocket(StartingPosition startingPosition, Objective objective) {
        if(startingPosition == StartingPosition.LEVEL_1_LEFT && objective == Objective.CARGO_ROCKET_LEFT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftStartToFrontLeftCargo));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.frontLeftCargoToLeftTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.LEFT.leftTurnToLeftLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftLoadingStationToLeftRocketNearTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftRocketNearTurnToLeftNearRocket));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT && objective == Objective.CARGO_ROCKET_RIGHT){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightStartToFrontRightCargo));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.frontRightCargoToRightTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.FRONT_CARGOSHIP.RIGHT.rightTurnToRightLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightLoadingStationToRightRocketNearTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightRocketNearTurnToRightNearRocket));
        }
    }
}
