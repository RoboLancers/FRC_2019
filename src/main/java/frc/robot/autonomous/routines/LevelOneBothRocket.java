package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class LevelOneBothRocket extends CommandGroup {
    public LevelOneBothRocket(StartingPosition startingPosition) {
        if (startingPosition == StartingPosition.LEVEL_1_LEFT) {
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftStartToLeftFarRocket, true));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftFarRocketToLeftFromFarTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftFromFarTurnToLeftLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftLoadingStationToLeftRocketNearTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftRocketNearTurnToLeftNearRocket));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
        } else if (startingPosition == StartingPosition.LEVEL_1_RIGHT) {
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightStartToRightFarRocket, true));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightFarRocketToRightFromFarTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightFromFarTurnToRightLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightLoadingStationToRightRocketNearTurn));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightRocketNearTurnToRightNearRocket));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
        }
    }
}
