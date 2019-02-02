package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.commands.subsystems.manipulator.hatch.AutoHatchRelease;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchEjector;
import frc.robot.enums.autonomous.StartingPosition;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.subsystems.drivetrain.Drivetrain;

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
        //TODO Turn 90
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.frontLeftCargoToLeftLoadingStation));
        //TODO Turn 180
        addSequential(Drivetrain.getInstance().followTrajectory(Trajectories.leftLoadingStationToFrontRightCargo));
        addSequential(new AutoHatchRelease());
    }
}
