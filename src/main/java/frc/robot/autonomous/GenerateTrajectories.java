package frc.robot.autonomous;

import frc.robot.Constants;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.trajectory.TrajectoryGeneratorKt;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedTrajectory;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

import java.util.Arrays;
import java.util.List;

public class GenerateTrajectories {
    private static final Length FRONT_OF_LEVEL_1_HAB_X = LengthKt.getFeet(8);
    private static final Length FRONT_OF_LEVEL_1_HAB_X_ADJUSTED = FRONT_OF_LEVEL_1_HAB_X.plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X);

    private static final Pose2d LOADING_STATION_LEFT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(24.9), Rotation2dKt.getDegree(180));
    private static final Pose2d LOADING_STATION_RIGHT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(2.2), Rotation2dKt.getDegree(180));

    public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontLeftCargo;

    public GenerateTrajectories(StartingPosition startingPosition, Objective objective) {
        if(objective == Objective.FRONT_CARGO_LEFT) {
            Pose2d CARGOSHIP_FRONT_LEFT_SCORING = new Pose2d(LengthKt.getFeet(18.5).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(14.45), Rotation2dKt.getDegree(0));
            Pose2d CARGOSHIP_FRONT_RIGHT_SCORING = new Pose2d(LengthKt.getFeet(18.5).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(12.6), Rotation2dKt.getDegree(0));

            Pose2d LEFT_CARGOSHIP_TURN_FACING_DOWN = new Pose2d(LengthKt.getFeet(14), LengthKt.getFeet(23), Rotation2dKt.getDegree(-135));

            if(startingPosition == StartingPosition.LEVEL_1_LEFT){
                leftStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                        Constants.ROBOT.LEVEL_1_LEFT_START,
                        new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.LEFT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                        CARGOSHIP_FRONT_LEFT_SCORING
                ));
            }else if(startingPosition == StartingPosition.LEVEL_1_CENTER){
                TimedTrajectory<Pose2dWithCurvature> centerStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                        Constants.ROBOT.LEVEL_1_CENTER_START,
                        new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.CENTER_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                        CARGOSHIP_FRONT_LEFT_SCORING
                ));
            }else if(startingPosition == StartingPosition.LEVEL_1_RIGHT){
                TimedTrajectory<Pose2dWithCurvature> rightStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                        Constants.ROBOT.LEVEL_1_RIGHT_START,
                        new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.RIGHT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                        CARGOSHIP_FRONT_LEFT_SCORING
                ));
            }

            TimedTrajectory<Pose2dWithCurvature> frontLeftCargoToLeftTurn = generateTrajectory(Arrays.asList(
                    CARGOSHIP_FRONT_LEFT_SCORING,
                    LEFT_CARGOSHIP_TURN_FACING_DOWN
            ), true);

            TimedTrajectory<Pose2dWithCurvature> leftTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
                    LEFT_CARGOSHIP_TURN_FACING_DOWN,
                    LOADING_STATION_LEFT_PICKUP
            ));

            TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_LEFT_PICKUP,
                    LEFT_CARGOSHIP_TURN_FACING_DOWN
            ), true);

            TimedTrajectory<Pose2dWithCurvature> leftTurnToFrontRightCargo = generateTrajectory(Arrays.asList(
                    LEFT_CARGOSHIP_TURN_FACING_DOWN,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));
        }
    }

    public static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints) {
        return generateTrajectory(waypoints, false);
    }

    public static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints, boolean reversed) {
        return TrajectoryGeneratorKt.getDefaultTrajectoryGenerator().generateTrajectory(
                waypoints,
                Constants.PATH_FOLLOWING.CONSTRAINTS,
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                Constants.PATH_FOLLOWING.MAX_VELOCITY,
                Constants.PATH_FOLLOWING.MAX_ACCELERATION,
                reversed,
                true
        );
    }
}
