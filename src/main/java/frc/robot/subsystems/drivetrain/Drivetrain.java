package frc.robot.subsystems.drivetrain;

import com.robolancers.lib.subsystems.drivetrain.TankDriveSubsystem;
import com.team254.lib.physics.DCMotorTransmission;
import com.team254.lib.physics.DifferentialDrive;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.drivetrain.UseDrivetrain;
import frc.robot.enums.drivetrain.TransmissionSide;
import frc.robot.subsystems.misc.Sensors;
import org.ghrobotics.lib.debug.LiveDashboard;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.localization.TankEncoderLocalization;
import org.ghrobotics.lib.mathematics.twodim.control.RamseteTracker;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.wrappers.FalconMotor;
import org.jetbrains.annotations.NotNull;

public class Drivetrain extends TankDriveSubsystem {
    private static Drivetrain instance;

    private Transmission left, right;

    private Localization localization;
    private DifferentialDrive differentialDrive;

    private TrajectoryTracker trajectoryTracker;

    private Drivetrain() {
        left = new Transmission(TransmissionSide.LEFT, RobotMap.DRIVETRAIN.MASTER_LEFT, RobotMap.DRIVETRAIN.LEFT_SLAVE_1, RobotMap.DRIVETRAIN.LEFT_SLAVE_2);
        right = new Transmission(TransmissionSide.RIGHT, RobotMap.DRIVETRAIN.MASTER_RIGHT, RobotMap.DRIVETRAIN.RIGHT_SLAVE_1, RobotMap.DRIVETRAIN.RIGHT_SLAVE_2);

        localization = new TankEncoderLocalization(
                () -> Rotation2dKt.getDegree(Sensors.getInstance().getAngle()),
                () -> left.getMaster().getSensorPosition(),
                () -> right.getMaster().getSensorPosition()
        );

        new Notifier(() -> {
            localization.update();
            LiveDashboard.INSTANCE.setRobotX(localization.getRobotPosition().getTranslation().getX().getFeet());
            LiveDashboard.INSTANCE.setRobotY(localization.getRobotPosition().getTranslation().getY().getFeet());
            LiveDashboard.INSTANCE.setRobotHeading(localization.getRobotPosition().getRotation().getRadian());
        }).startPeriodic(0.01);

        DCMotorTransmission dcMotorTransmission = new DCMotorTransmission(
                1 / Constants.DRIVETRAIN.kV,
                Math.pow(Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(), 2) * Constants.ROBOT.MASS / (2 * Constants.DRIVETRAIN.kA),
                Constants.DRIVETRAIN.kStaticFrictionVoltage
        );

        differentialDrive = new DifferentialDrive(
                Constants.ROBOT.MASS,
                Constants.ROBOT.MOMENT_OF_INTERTIA,
                Constants.ROBOT.ANGULAR_DRAG,
                Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(),
                Constants.DRIVETRAIN.TRACK_WIDTH.getValue() / 2.0,
                dcMotorTransmission,
                dcMotorTransmission
        );

        trajectoryTracker = new RamseteTracker(Constants.PATH_FOLLOWING.RAMSETE_BETA, Constants.PATH_FOLLOWING.RAMSETE_ZETA);
    }

    public static synchronized Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }

        return instance;
    }

    @Override
    public Localization getLocalization() {
        return localization;
    }

    @NotNull
    @Override
    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }

    @NotNull
    @Override
    public FalconMotor<Length> getLeftMotor() {
        return left.getMaster();
    }

    @NotNull
    @Override
    public FalconMotor<Length> getRightMotor() {
        return right.getMaster();
    }

    @NotNull
    @Override
    public TrajectoryTracker getTrajectoryTracker() {
        return trajectoryTracker;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseDrivetrain());
    }
}
