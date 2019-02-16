package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.autonomous.Trajectories;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class LevelOneRocket extends CommandGroup {
    public LevelOneRocket(StartingPosition startingPosition, Objective objective){
        if(startingPosition == StartingPosition.LEVEL_1_LEFT && objective == Objective.LEFT_ROCKET){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftStartToLeftNearRocket, true));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftNearRocketToLeftTurn));
            addSequential(new Turning(180));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.LEFT.leftTurnToLeftLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
        }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT && objective == Objective.RIGHT_ROCKET){
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightStartToRightNearRocket, true));
            addSequential(new AutoHatchRelease());
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightNearRocketToRightTurn));
            addSequential(new Turning(180));
            addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.ROCKET.RIGHT.rightTurnToRightLoadingStation));
            addSequential(new UseHatchHolder(HatchHolderState.HOLD));
        }
    }
}
