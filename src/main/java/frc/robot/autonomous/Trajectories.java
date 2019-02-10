package frc.robot.autonomous;

import frc.robot.Constants;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.trajectory.TrajectoryGeneratorKt;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedTrajectory;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

import java.util.Arrays;
import java.util.List;

public class Trajectories {
    private static final Pose2d LOADING_STATION_LEFT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(24.9), Rotation2dKt.getDegree(180));
    private static final Pose2d LOADING_STATION_RIGHT_PICKUP = new Pose2d(LengthKt.getFeet(0).plus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(2.2), Rotation2dKt.getDegree(180));

    public static final class CARGOSHIP {
        private static final Pose2d CARGOSHIP_FRONT_LEFT_SCORING = new Pose2d(LengthKt.getFeet(18.2).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(14.45), Rotation2dKt.getDegree(0));
        private static final Pose2d CARGOSHIP_FRONT_RIGHT_SCORING = new Pose2d(LengthKt.getFeet(18.2).minus(Constants.ROBOT.MIDDLE_OF_ROBOT_X), LengthKt.getFeet(12.6), Rotation2dKt.getDegree(0));

        public static final class LEFT {
            private static final Pose2d CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(20.5), Rotation2dKt.getDegree(180));
            private static final Pose2d LEFT_LOADING_STATION_LEFT_CARGO_UTURN = new Pose2d(LengthKt.getFeet(10.5), LengthKt.getFeet(14.5), Rotation2dKt.getDegree(0));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_CENTER_START,
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> frontLeftCargoToLeftUTurn = generateTrajectory(Arrays.asList(
                    CARGOSHIP_FRONT_LEFT_SCORING,
                    CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
                    CARGOSHIP_AND_NEAR_ROCKET_LEFT_UTURN,
                    LOADING_STATION_LEFT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftCargoUTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_LEFT_PICKUP,
                    LEFT_LOADING_STATION_LEFT_CARGO_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToFrontRightCargo = generateTrajectory(Arrays.asList(
                    LEFT_LOADING_STATION_LEFT_CARGO_UTURN,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));
        }

        public static final class RIGHT {
            private static final Pose2d CARGOSHIP_AND_NEAR_ROCKET_RIGHT_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(6.5), Rotation2dKt.getDegree(180));
            private static final Pose2d RIGHT_LOADING_STATION_RIGHT_CARGO_UTURN = new Pose2d(LengthKt.getFeet(10.5), LengthKt.getFeet(12.5), Rotation2dKt.getDegree(0));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> centerStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_CENTER_START,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToFrontRightCargo = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    CARGOSHIP_FRONT_RIGHT_SCORING
            ));

            public static TimedTrajectory<Pose2dWithCurvature> frontRightCargoToRightUTurn = generateTrajectory(Arrays.asList(
                    CARGOSHIP_FRONT_RIGHT_SCORING,
                    CARGOSHIP_AND_NEAR_ROCKET_RIGHT_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightUTurnToRightLoadingStation = generateTrajectory(Arrays.asList(
                    CARGOSHIP_AND_NEAR_ROCKET_RIGHT_UTURN,
                    LOADING_STATION_RIGHT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightLoadingStationToRightCargoUTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_RIGHT_PICKUP,
                    RIGHT_LOADING_STATION_RIGHT_CARGO_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightUTurnToFrontLeftCargo = generateTrajectory(Arrays.asList(
                    RIGHT_LOADING_STATION_RIGHT_CARGO_UTURN,
                    CARGOSHIP_FRONT_LEFT_SCORING
            ));
        }
    }

    public static final class ROCKET {
        public static final class LEFT {
            private static final Pose2d LEFT_ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(30));
            private static final Pose2d LEFT_ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21), LengthKt.getFeet(25.1), Rotation2dKt.getDegree(150));

            private static final Pose2d LEFT_LOADING_STATION_LEFT_ROCKET_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(20.5), Rotation2dKt.getDegree(180));

            public static TimedTrajectory<Pose2dWithCurvature> leftStartToLeftNearRocket = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_LEFT_START,
                    LEFT_ROCKET_NEAR_HATCH
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftNearRocketToLeftUTurn = generateTrajectory(Arrays.asList(
                    LEFT_ROCKET_NEAR_HATCH,
                    LEFT_LOADING_STATION_LEFT_ROCKET_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToLeftLoadingStation = generateTrajectory(Arrays.asList(
                    LEFT_LOADING_STATION_LEFT_ROCKET_UTURN,
                    LOADING_STATION_LEFT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> leftLoadingStationToLeftRocketUTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_LEFT_PICKUP,
                    LEFT_LOADING_STATION_LEFT_ROCKET_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> leftUTurnToLeftFarRocket = generateTrajectory(Arrays.asList(
                    LEFT_LOADING_STATION_LEFT_ROCKET_UTURN,
                    LEFT_ROCKET_FAR_HATCH
            ));
        }

        public static final class RIGHT {
            private static final Pose2d RIGHT_ROCKET_NEAR_HATCH = new Pose2d(LengthKt.getFeet(17), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-30));
            private static final Pose2d RIGHT_ROCKET_FAR_HATCH = new Pose2d(LengthKt.getFeet(21), LengthKt.getFeet(2.0), Rotation2dKt.getDegree(-150));

            private static final Pose2d RIGHT_LOADING_STATION_RIGHT_ROCKET_UTURN = new Pose2d(LengthKt.getFeet(18), LengthKt.getFeet(6.5), Rotation2dKt.getDegree(180));

            public static TimedTrajectory<Pose2dWithCurvature> rightStartToRightNearRocket = generateTrajectory(Arrays.asList(
                    Constants.ROBOT.LEVEL_1_RIGHT_START,
                    RIGHT_ROCKET_NEAR_HATCH
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightNearRocketToRightUTurn = generateTrajectory(Arrays.asList(
                    RIGHT_ROCKET_NEAR_HATCH,
                    RIGHT_LOADING_STATION_RIGHT_ROCKET_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightUTurnToRightLoadingStation = generateTrajectory(Arrays.asList(
                    RIGHT_LOADING_STATION_RIGHT_ROCKET_UTURN,
                    LOADING_STATION_RIGHT_PICKUP
            ));

            public static TimedTrajectory<Pose2dWithCurvature> rightLoadingStationToRightRocketUTurn = generateTrajectory(Arrays.asList(
                    LOADING_STATION_RIGHT_PICKUP,
                    RIGHT_LOADING_STATION_RIGHT_ROCKET_UTURN
            ), true);

            public static TimedTrajectory<Pose2dWithCurvature> rightUTurnToRightFarRocket = generateTrajectory(Arrays.asList(
                    RIGHT_LOADING_STATION_RIGHT_ROCKET_UTURN,
                    RIGHT_ROCKET_FAR_HATCH
            ));
        }
    }

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
}