package frc.robot.commands.autonomous;

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
    public static TimedTrajectory<Pose2dWithCurvature> levelOneLeftCargo, levelOneRightCargo;
    public static TimedTrajectory<Pose2dWithCurvature> RightCargoToLeftHatch, leftCargoToLeftHatch, leftHatchToLeftCargo, leftHatchToRightCargo;
    private static List<TimingConstraint<Pose2dWithCurvature>> constraints = Arrays.asList(
            new CentripetalAccelerationConstraint(AccelerationKt.getAcceleration(LengthKt.getFeet(4.5))),
            new VelocityLimitRegionConstraint(new Rectangle2d(LengthKt.getFeet(0.0), LengthKt.getFeet(7.0), LengthKt.getFeet(8.0), LengthKt.getFeet(13.0)), VelocityKt.getVelocity(LengthKt.getFeet(3.0)))
    );

    public static void generateTimedTrajectories() {
        levelOneLeftCargo = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(5.25), LengthKt.getFeet(13.25), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(14.25), Rotation2dKt.getDegree(0.0))
                ),
                false
        );
        levelOneRightCargo = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(5.25), LengthKt.getFeet(13.25), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(12.5), Rotation2dKt.getDegree(0.0))
                ),
                false
        );
        RightCargoToLeftHatch = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(12.5), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(11.75), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0)),
                        new Pose2d(LengthKt.getFeet(0.5), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0))
                ),
                true
        );
        leftCargoToLeftHatch = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(14.25), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(11.75), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0)),
                        new Pose2d(LengthKt.getFeet(0.5), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0))
                ),
                true
        );
        leftHatchToLeftCargo = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(0.5), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(11.75), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0)),
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(14.25), Rotation2dKt.getDegree(180.0))
                ),
                false
        );
        leftHatchToRightCargo = generateTrajectory(
                Arrays.asList(
                        new Pose2d(LengthKt.getFeet(0.5), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(0.0)),
                        new Pose2d(LengthKt.getFeet(11.75), LengthKt.getFeet(2.25), Rotation2dKt.getDegree(180.0)),
                        new Pose2d(LengthKt.getFeet(17.0), LengthKt.getFeet(12.5), Rotation2dKt.getDegree(180.0))
                ),
                false
        );
    }

    public static TimedTrajectory<Pose2dWithCurvature> generateTrajectory(List<Pose2d> waypoints, boolean reversed) {
        return TrajectoryGeneratorKt.getDefaultTrajectoryGenerator().generateTrajectory(
                waypoints,
                constraints,
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                VelocityKt.getVelocity(LengthKt.getFeet(0)),
                VelocityKt.getVelocity(LengthKt.getFeet(10)),
                AccelerationKt.getAcceleration(LengthKt.getFeet(4.0)),
                reversed
        );
    }
}

// comment