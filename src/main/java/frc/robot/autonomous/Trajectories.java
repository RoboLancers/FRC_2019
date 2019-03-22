package frc.robot.autonomous;

import frc.robot.Constants;
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

public class Trajectories {
    private static final Length FRONT_OF_LEVEL_1_HAB_X = LengthKt.getFeet(8);
    private static final Length FRONT_OF_LEVEL_1_HAB_X_ADJUSTED = FRONT_OF_LEVEL_1_HAB_X.plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X);

    private static final Pose2d LOADING_STATION_LEFT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(24.9), Rotation2dKt.getDegree(180));
    private static final Pose2d LOADING_STATION_RIGHT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(2.2), Rotation2dKt.getDegree(180));

    public static void generateTrajectories() { }

    private static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints) {
        return generateTrajectory(waypoints, false);
    }

    private static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints, boolean reversed) {
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

    public static final class FRONT_CARGOSHIP {
        private static final Pose2d CARGOSHIP_FRONT_LEFT_SCORING = new Pose2d(LengthKt.getFeet(18.5).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(14.45), Rotation2dKt.getDegree(0));
        private static final Pose2d CARGOSHIP_FRONT_RIGHT_SCORING = new Pose2d(LengthKt.getFeet(18.5).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(12.6), Rotation2dKt.getDegree(0));

        public static final class LEFT {
            private static final Pose2d LEFT_CARGOSHIP_TURN_FACING_DOWN = new Pose2d(LengthKt.getFeet(14), LengthKt.getFeet(23), Rotation2dKt.getDegree(-135));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.LEFT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_CENTER_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.CENTER_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.RIGHT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> frontLeftCargoToLeftTurn = generateTrajectory(Arrays.asList(
                    CARGOSHIP_FRONT_LEFT_SCORING,
                    LEFT_CARGOSHIP_TURN_FACING_DOWN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
                    LEFT_CARGOSHIP_TURN_FACING_DOWN,
                    LOADING_STATION_LEFT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_LEFT_PICKUP,
                    LEFT_CARGOSHIP_TURN_FACING_DOWN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftTurnToFrontRightCargo = generateTrajectory(Arrays.asList(
                    LEFT_CARGOSHIP_TURN_FACING_DOWN,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));
        }

        public static final class RIGHT {
            private static final Pose2d RIGHT_CARGOSHIP_TURN_FACING_UP = new Pose2d(LengthKt.getFeet(14), LengthKt.getFeet(4), Rotation2dKt.getDegree(135));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.LEFT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_CENTER_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.CENTER_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.RIGHT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> frontRightCargoToRightTurn = generateTrajectory(Arrays.asList(
                    CARGOSHIP_FRONT_RIGHT_SCORING,
                    RIGHT_CARGOSHIP_TURN_FACING_UP
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightTurnToRightLoadingStation = generateTrajectory(Arrays.asList(
                    RIGHT_CARGOSHIP_TURN_FACING_UP,
                    LOADING_STATION_RIGHT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightLoadingStationToRightTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_RIGHT_PICKUP,
                    RIGHT_CARGOSHIP_TURN_FACING_UP
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightTurnToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    RIGHT_CARGOSHIP_TURN_FACING_UP,
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));
        }
    }

    public static final class ROCKET {
        public static final class LEFT {
            private static final Pose2d ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(30));
            private static final Pose2d ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(150));

            private static final Pose2d ROCKET_NEAR_TURN = new Pose2d(LengthKt.getFeet(12), LengthKt.getFeet(22), Rotation2dKt.getDegree(75));
            private static final Pose2d ROCKET_FROM_FAR_TURN = new Pose2d(LengthKt.getFeet(13), LengthKt.getFeet(25), Rotation2dKt.getDegree(-105));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToLeftFarRocket = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.LEFT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    ROCKET_FAR_HATCH
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftFarRocketToLeftFromFarTurn = generateTrajectory(Arrays.asList(
                    ROCKET_FAR_HATCH,
                    new Pose2d(LengthKt.getFeet(17.5), LengthKt.getFeet(21), Rotation2dKt.getDegree(0)),
                    ROCKET_FROM_FAR_TURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftFromFarTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
                    ROCKET_FROM_FAR_TURN,
                    LOADING_STATION_LEFT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftRocketNearTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_LEFT_PICKUP,
                    ROCKET_NEAR_TURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftRocketNearTurnToLeftNearRocket = generateTrajectory(Arrays.asList(
                    ROCKET_NEAR_TURN,
                    ROCKET_NEAR_HATCH
            ));
        }

        public static final class RIGHT {
            private static final Pose2d RIGHT_ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-30));
            private static final Pose2d RIGHT_ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-150));

            private static final Pose2d RIGHT_ROCKET_NEAR_TURN = new Pose2d(LengthKt.getFeet(12), LengthKt.getFeet(6), Rotation2dKt.getDegree(-75));
            private static final Pose2d RIGHT_ROCKET_FROM_FAR_TURN = new Pose2d(LengthKt.getFeet(13), LengthKt.getFeet(2), Rotation2dKt.getDegree(105));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToRightFarRocket = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    new Pose2d(FRONT_OF_LEVEL_1_HAB_X_ADJUSTED, Constants.ROBOT.RIGHT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0)),
                    RIGHT_ROCKET_FAR_HATCH
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightFarRocketToRightFromFarTurn = generateTrajectory(Arrays.asList(
                    RIGHT_ROCKET_FAR_HATCH,
                    new Pose2d(LengthKt.getFeet(17.5), LengthKt.getFeet(6), Rotation2dKt.getDegree(0)),
                    RIGHT_ROCKET_FROM_FAR_TURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightFromFarTurnToRightLoadingStation = generateTrajectory(Arrays.asList(
                    RIGHT_ROCKET_FROM_FAR_TURN,
                    LOADING_STATION_RIGHT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightLoadingStationToRightRocketNearTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_RIGHT_PICKUP,
                    RIGHT_ROCKET_NEAR_TURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightRocketNearTurnToRightNearRocket = generateTrajectory(Arrays.asList(
                    RIGHT_ROCKET_NEAR_TURN,
                    RIGHT_ROCKET_NEAR_HATCH
            ));
        }
    }
}