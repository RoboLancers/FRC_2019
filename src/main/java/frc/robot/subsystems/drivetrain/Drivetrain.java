package frc.robot.subsystems.drivetrain;

import com.robolancers.lib.subsystems.drivetrain.TankDriveSubsystem;
import com.team254.lib.physics.DCMotorTransmission;
import com.team254.lib.physics.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.drivetrain.UseDrivetrain;
import frc.robot.subsystems.misc.Sensors;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.localization.TankEncoderLocalization;
import org.ghrobotics.lib.mathematics.twodim.control.RamseteTracker;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.wrappers.FalconMotor;
import org.jetbrains.annotations.NotNull;

public class Drivetrain extends TankDriveSubsystem {
    public static Drivetrain instance;
    Transmission leftTransmission, rightTransmission;

    private Localization localization;
    private DCMotorTransmission dcMotorTransmission;
    private DifferentialDrive differentialDrive;

    private RamseteTracker trajectoryTracker;

    public Drivetrain() {
        leftTransmission = new Transmission(RobotMap.MASTER_LEFT, RobotMap.LEFT_SLAVE_1, RobotMap.LEFT_SLAVE_2);
        rightTransmission = new Transmission(RobotMap.MASTER_RIGHT, RobotMap.RIGHT_SLAVE_1, RobotMap.RIGHT_SLAVE_2);

        localization = new TankEncoderLocalization(
                () -> Rotation2dKt.getDegree(Sensors.getInstance().getAngle()),
                () -> leftTransmission.getMaster().getSensorPosition(),
                () -> rightTransmission.getMaster().getSensorPosition()
        );

        dcMotorTransmission = new DCMotorTransmission(
                1/ Constants.kDrivetrainKV,
                Math.pow(Constants.kDrivetrainWheelRadius.getValue(), 2) * Constants.kRobotMass / (2.0 * Constants.kDrivetrainKA),
                Constants.kDrivetrainStaticFrictionVolt
        );

        differentialDrive = new DifferentialDrive(
                Constants.kRobotMass,
                Constants.kMomentOfInertia,
                Constants.kRobotAngularDrag,
                Constants.kDrivetrainWheelRadius.getValue(),
                Constants.kTrackWidth.getValue() / 2.0,
                dcMotorTransmission,
                dcMotorTransmission
        );

        trajectoryTracker = new RamseteTracker(Constants.kDrivetrainBeta, Constants.kDrivetrainZeta);
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
        return leftTransmission.getMaster();
    }

    @NotNull
    @Override
    public FalconMotor<Length> getRightMotor() {
        return rightTransmission.getMaster();
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

    public static synchronized Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }

        return instance;
    }
}
