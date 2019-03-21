package frc.robot.autonomous;

import com.robolancers.lib.subsystems.drivetrain.TankDriveSubsystem;
import com.team254.lib.physics.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.misc.Camera;
import org.ghrobotics.lib.debug.LiveDashboard;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.geometry.Translation2d;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedEntry;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedTrajectory;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TrajectorySamplePoint;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.Velocity;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;
import org.ghrobotics.lib.subsystems.drive.TrajectoryTrackerDriveBase;
import org.ghrobotics.lib.subsystems.drive.TrajectoryTrackerOutput;

import java.util.function.Supplier;

@SuppressWarnings({"unused", "FieldCanBeLocal", "WeakerAccess"})
public class TrajectoryTrackerCommand extends Command {
    private TrajectoryTracker trajectoryTracker;
    private Supplier<TimedTrajectory<Pose2dWithCurvature>> trajectorySource;
    private TrajectoryTrackerDriveBase driveBase;
    private TankDriveSubsystem tankDriveSubsystem;
    private Localization localization;
    private boolean reset;

    private TrajectoryTrackerOutput nextState;
    private Translation2d robotTranslation;
    private DifferentialDrive.DriveDynamics driveDynamics;
    private double leftVelocity, rightVelocity;
    private TrajectorySamplePoint<TimedEntry<Pose2dWithCurvature>> referencePoint;
    private Pose2d referencePose;

    public TrajectoryTrackerCommand(TankDriveSubsystem tankDriveSubsystem, TrajectoryTrackerDriveBase driveBase, Supplier<TimedTrajectory<Pose2dWithCurvature>> trajectorySource) {
        this(tankDriveSubsystem, driveBase, trajectorySource, false);
    }

    public TrajectoryTrackerCommand(TankDriveSubsystem tankDriveSubsystem, TrajectoryTrackerDriveBase driveBase, Supplier<TimedTrajectory<Pose2dWithCurvature>> trajectorySource, boolean reset) {
        requires(tankDriveSubsystem);

        this.trajectoryTracker = driveBase.getTrajectoryTracker();
        this.trajectorySource = trajectorySource;
        this.driveBase = driveBase;
        this.tankDriveSubsystem = tankDriveSubsystem;
        this.localization = tankDriveSubsystem.getLocalization();
        this.reset = reset;
    }

    @Override
    protected void initialize() {
        trajectoryTracker.reset(trajectorySource.get());

        if (reset) {
            localization.reset(trajectorySource.get().getFirstState().component1().getPose());
        }

        LiveDashboard.INSTANCE.setFollowingPath(true);
    }

    @Override
    protected void execute() {
        nextState = trajectoryTracker.nextState(driveBase.getRobotPosition(), TimeUnitsKt.getMillisecond(System.currentTimeMillis()));
        robotTranslation = driveBase.getRobotPosition().getTranslation();

        if(Constants.AREAS.ALL_AREAS.stream().anyMatch(rectangle2d -> rectangle2d.contains(robotTranslation))) {
            if (Camera.getInstance().hasLine()) {
                Velocity<Rotation2d> correction = VelocityKt.getVelocity(Rotation2dKt.getRadian(Constants.DRIVETRAIN.VISION_CORRECTION_KP * (39 - Camera.getInstance().getLineX())));
                nextState = new TrajectoryTrackerOutput(nextState.getLinearVelocity(), nextState.getLinearAcceleration(), nextState.getAngularVelocity().plus(correction), nextState.getAngularAcceleration());
            }
        }

        driveDynamics = tankDriveSubsystem.getDifferentialDrive().solveInverseDynamics(nextState.getDifferentialDriveVelocity(), nextState.getDifferentialDriveAcceleration());
        leftVelocity = VelocityKt.getFeetPerSecond(VelocityKt.getVelocity(LengthKt.getMeter(driveDynamics.getWheelVelocity().getLeft() * tankDriveSubsystem.getDifferentialDrive().getWheelRadius())));
        rightVelocity = VelocityKt.getFeetPerSecond(VelocityKt.getVelocity(LengthKt.getMeter(driveDynamics.getWheelVelocity().getRight() * tankDriveSubsystem.getDifferentialDrive().getWheelRadius())));

        driveBase.setOutput(nextState);

        referencePoint = trajectoryTracker.getReferencePoint();

        if (referencePoint != null) {
            referencePose = referencePoint.getState().getState().getPose();

            LiveDashboard.INSTANCE.setPathX(referencePose.getTranslation().getX().getFeet());
            LiveDashboard.INSTANCE.setPathY(referencePose.getTranslation().getY().getFeet());
            LiveDashboard.INSTANCE.setPathHeading(referencePose.getRotation().getRadian());

            SmartDashboard.putNumber("Left Path Velocity", leftVelocity);
            SmartDashboard.putNumber("Right Path Velocity", rightVelocity);
        }
    }

    @Override
    protected void end() {
        driveBase.zeroOutputs();
        LiveDashboard.INSTANCE.setFollowingPath(false);
    }

    @Override
    protected boolean isFinished() {
        return trajectoryTracker.isFinished();
    }
}