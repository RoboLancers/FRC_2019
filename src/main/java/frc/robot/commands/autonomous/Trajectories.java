package frc.robot.commands.autonomous;

import frc.robot.Constants;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.geometry.Rectangle2d;
import org.ghrobotics.lib.mathematics.twodim.trajectory.TrajectoryGeneratorKt;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.CentripetalAccelerationConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.TimingConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.VelocityLimitRegionConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedTrajectory;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.AccelerationKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

import java.util.Arrays;
import java.util.List;

public class Trajectories {
    // Measured from middle of front
    private static final Pose2d CARGOSHIP_FRONT_LEFT_SCORING = new Pose2d(LengthKt.getFeet(18.2).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(14.45), Rotation2dKt.getDegree(0));
    private static final Pose2d CARGOSHIP_FRONT_RIGHT_SCORING = new Pose2d(LengthKt.getFeet(18.2).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(12.6), Rotation2dKt.getDegree(0));

    private static final Pose2d CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(20.5), Rotation2dKt.getDegree(180));
    private static final Pose2d CARGOSHIP_AND_NEAR_ROCKET_RIGHT_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(6.5), Rotation2dKt.getDegree(180));

    private static final Pose2d LOADING_STATION_LEFT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(24.9), Rotation2dKt.getDegree(180));
    private static final Pose2d LOADING_STATION_RIGHT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(14.5), Rotation2dKt.getDegree(180));

    private static final Pose2d LOADING_STATION_LEFT_UTURN = new Pose2d(LengthKt.getFeet(12), LengthKt.getFeet(14.5), Rotation2dKt.getDegree(0));
    private static final Pose2d LOADING_STATION_RIGHT_UTURN = new Pose2d(LengthKt.getFeet(12), LengthKt.getFeet(12.5), Rotation2dKt.getDegree(0));

    private static final Pose2d LEFT_ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(30));
    private static final Pose2d LEFT_ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(150));

    private static final Pose2d RIGHT_ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-30));
    private static final Pose2d RIGHT_ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-150));

    //Trajectories
    public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_LEFT,
            CARGOSHIP_FRONT_LEFT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_CENTER,
            CARGOSHIP_FRONT_LEFT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_RIGHT,
            CARGOSHIP_FRONT_LEFT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontRightCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_RIGHT,
            CARGOSHIP_FRONT_RIGHT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontRightCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_CENTER,
            CARGOSHIP_FRONT_RIGHT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> LeftStartToFrontRightCargo = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_LEFT,
            CARGOSHIP_FRONT_RIGHT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> frontLeftCargoToLeftUTurn = generateTrajectory(Arrays.asList(
            CARGOSHIP_FRONT_LEFT_SCORING,
            CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN
    ));

    public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
            CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN,
            LOADING_STATION_LEFT_PICKUP
    ));

    public static TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftUTurn = generateTrajectory(Arrays.asList(
            LOADING_STATION_LEFT_PICKUP,
            LOADING_STATION_LEFT_UTURN
    ));

    public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToFrontRightCargo = generateTrajectory(Arrays.asList(
            CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN,
            CARGOSHIP_FRONT_RIGHT_SCORING
    ));

    public static TimedTrajectory<Pose2dWithCurvature> leftStartToLeftRocket = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_LEFT,
            LEFT_ROCKET_NEAR_HATCH
    ));

    public static TimedTrajectory<Pose2dWithCurvature> rightStartToRightRocket = generateTrajectory(Arrays.asList(
            Constants.ROBOT.LEVEL_1_RIGHT,
            RIGHT_ROCKET_NEAR_HATCH
    ));

    public static TimedTrajectory<Pose2dWithCurvature> leftNearRocketToLeftUTurn = generateTrajectory(Arrays.asList(
            LEFT_ROCKET_NEAR_HATCH,
            CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN
    ));

    public static TimedTrajectory<Pose2dWithCurvature> rightNearRocketToRightUTurn = generateTrajectory(Arrays.asList(
            RIGHT_ROCKET_NEAR_HATCH,
            CARGOSHIP_AND_NEAR_ROCKET_RIGHT_UTURN
    ));



    public static void generateTrajectories() { }

    private static List<TimingConstraint<Pose2dWithCurvature>> constraints = Arrays.asList(
            new CentripetalAccelerationConstraint(AccelerationKt.getAcceleration(LengthKt.getFeet(4.5))),
            new VelocityLimitRegionConstraint(new Rectangle2d(LengthKt.getFeet(0.0), LengthKt.getFeet(7.0), LengthKt.getFeet(8.0), LengthKt.getFeet(13.0)), VelocityKt.getVelocity(LengthKt.getFeet(3.0)))
    );

    private static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints) {
        return generateTrajectory(waypoints, false);
    }

    private static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints, boolean reversed) {
        return TrajectoryGeneratorKt.getDefaultTrajectoryGenerator().generateTrajectory(
                waypoints,
                constraints,
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                VelocityKt.getVelocity(LengthKt.getFeet(10)),
                AccelerationKt.getAcceleration(LengthKt.getFeet(4.0)),
                reversed,
                false
        );
    }
}