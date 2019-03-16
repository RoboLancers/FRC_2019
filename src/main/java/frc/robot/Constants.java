package frc.robot;

import com.team254.lib.physics.DCMotorTransmission;
import com.team254.lib.physics.DifferentialDrive;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.geometry.Rectangle2d;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.CentripetalAccelerationConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.DifferentialDriveDynamicsConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.TimingConstraint;
import org.ghrobotics.lib.mathematics.twodim.trajectory.constraints.VelocityLimitRegionConstraint;
import org.ghrobotics.lib.mathematics.units.*;
import org.ghrobotics.lib.mathematics.units.derivedunits.*;
import org.ghrobotics.lib.mathematics.units.nativeunits.*;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Constants {
    public static Time TIMEOUT = TimeUnitsKt.getMillisecond(0);

    public static final class PATH_FOLLOWING {
        public static final Velocity<Length> MAX_VELOCITY = VelocityKt.getVelocity(LengthKt.getFeet(8));
        public static final Acceleration<Length> MAX_ACCELERATION = AccelerationKt.getAcceleration(LengthKt.getFeet(4));
        public static final Volt MAX_VOLTAGE = VoltKt.getVolt(10);

        public static List<TimingConstraint<Pose2dWithCurvature>> CONSTRAINTS = Arrays.asList(
                new DifferentialDriveDynamicsConstraint(DRIVETRAIN.DIFFERENTIAL_DRIVE, MAX_VOLTAGE),
                new CentripetalAccelerationConstraint(AccelerationKt.getAcceleration(LengthKt.getFeet(10))),
                new VelocityLimitRegionConstraint(new Rectangle2d(LengthKt.getFeet(0.0), LengthKt.getFeet(7.0), LengthKt.getFeet(8.0), LengthKt.getFeet(13.0)), VelocityKt.getVelocity(LengthKt.getFeet(2)))
        );

        public static final double RAMSETE_BETA = 1.7;
        public static final double RAMSETE_ZETA = 0.8;
    }

    public static final class ROBOT {
        public static final double MASS = 56.7;
        public static final double MOMENT_OF_INERTIA = 10;
        public static final double ANGULAR_DRAG = 12;

        public static final Length ROBOT_LENGTH = LengthKt.getInch(33);
        public static final Length ROBOT_WIDTH = LengthKt.getInch(25.25);
        public static final Length BUMPER_THICKNESS = LengthKt.getInch(2.0);

        public static final Length MIDDLE_OF_ROBOT_X = ROBOT_LENGTH.div(2.0).plus(BUMPER_THICKNESS);
        public static final Length MIDDLE_OF_ROBOT_Y = ROBOT_WIDTH.div(2.0).plus(BUMPER_THICKNESS);

        public static final Length LEVEL_1_START_X = LengthKt.getFeet(4);
        public static final Length ROBOT_LEVEL_1_START_X = LEVEL_1_START_X.plus(MIDDLE_OF_ROBOT_X);

        public static final Length LEFT_LEVEL_1_START_Y = LengthKt.getFeet(18.8).minus(MIDDLE_OF_ROBOT_Y);
        public static final Length CENTER_LEVEL_1_START_Y = LengthKt.getFeet(15.5).minus(MIDDLE_OF_ROBOT_Y);
        public static final Length RIGHT_LEVEL_1_START_Y = LengthKt.getFeet(11.5).minus(MIDDLE_OF_ROBOT_Y);

        public static final Pose2d LEVEL_1_LEFT_START = new Pose2d(ROBOT_LEVEL_1_START_X, LEFT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0));
        public static final Pose2d LEVEL_1_CENTER_START = new Pose2d(ROBOT_LEVEL_1_START_X, CENTER_LEVEL_1_START_Y, Rotation2dKt.getDegree(0));
        public static final Pose2d LEVEL_1_RIGHT_START = new Pose2d(ROBOT_LEVEL_1_START_X, RIGHT_LEVEL_1_START_Y, Rotation2dKt.getDegree(0));
    }

    public static final class DRIVETRAIN {
        public static final int PEAK_CURRENT_LIMIT = 30;
        public static final int CONTINUOUS_CURRENT_LIMIT = 25;
        public static final int PEAK_CURRENT_DURATION = 10;

        public static final int VOLTAGE_COMPENSATION = 10;
        public static final int VOLTAGE_MEASUREMENT_FILTER = 32;

        public static final double RAMP_RATE = 0.15;

        //public static final double LEFT_KF = 1.08;
        //public static final double RIGHT_KF = 1.26;

        public static final double LEFT_KF = 0;
        public static final double RIGHT_KF = 0;

        public static final double TALON_kP = 1.7;
        public static final double TALON_kI = 0.0;
        public static final double TALON_kD = 0.0;

        public static final double TURNING_kP = 0.0035;
        public static final double TURNING_kI = 0.0;
        public static final double TURNING_kD = 0.0;

        public static final double VISION_CORRECTION_KP = 0.01;

        public static final double ALLOWABLE_ERROR = 2.5;

        public static final double LEFT_MAX_VELOCITY = 14;
        public static final double RIGHT_MAX_VELOCITY = 12;

        public static final double kStaticFrictionVoltage = 1.8;
        public static final double kStaticFrictionVoltageLeft = 1.8;
        public static final double kStaticFrictionVoltageRight = 1.8;

        public static final double kStaticFrictionPercent = 0.15;
        public static final double kStaticFrictionPercentLeft = 0.15;
        public static final double kStaticFrictionPercentRight = 0.15;

        // Half theoretical, half empirical
        //public static final double kVLeft = 0.2127;
        //public static final double kVRight = 0.2459;

        public static final double kVLeft = 0.11;
        public static final double kVRight = 0.12;

        // Theoretical
        //public static final double kALeft = 0.108;
        //public static final double kARight = 0.108;

        public static final double kALeft = 0.08;
        public static final double kARight = 0.06;

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getNativeUnits(1024);
        public static final Length WHEEL_RADIUS = LengthKt.getInch(3);

        public static final Length TRACK_WIDTH = LengthKt.getMeter(0.59055);

        public static final NativeUnitModel<Length> NATIVE_UNIT_MODEL = new NativeUnitLengthModel(
                SENSOR_UNIT_PER_ROTATION,
                WHEEL_RADIUS
        );

        public static final DCMotorTransmission leftDCMotorTransmission = new DCMotorTransmission(
                1 / Constants.DRIVETRAIN.kVLeft,
                Math.pow(Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(), 2) * Constants.ROBOT.MASS / (2 * Constants.DRIVETRAIN.kALeft),
                Constants.DRIVETRAIN.kStaticFrictionVoltageLeft
        );

        public static final DCMotorTransmission rightDCMotorTransmission = new DCMotorTransmission(
                1 / DRIVETRAIN.kVRight,
                Math.pow(Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(), 2) * Constants.ROBOT.MASS / (2 * DRIVETRAIN.kARight),
                DRIVETRAIN.kStaticFrictionVoltageRight
        );

        public static final DifferentialDrive DIFFERENTIAL_DRIVE = new DifferentialDrive(
                Constants.ROBOT.MASS,
                Constants.ROBOT.MOMENT_OF_INERTIA,
                Constants.ROBOT.ANGULAR_DRAG,
                Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(),
                Constants.DRIVETRAIN.TRACK_WIDTH.getValue() / 2.0,
                leftDCMotorTransmission,
                rightDCMotorTransmission
        );
    }

    public static final class CLIMBER {
        public static final int CLIMBING_ANGLE = 45;
        public static final int CLIMBING_MARGIN = 5;

        public static final int PID_SLOT_INDEX = 0;
        public static final int ALLOWABLE_ARM_ERROR = 10;

        public static final double ARM_kF = 0.0;
        public static final double ARM_kP = 1.0;
        public static final double ARM_kI = 0.0;
        public static final double ARM_kD = 0.5;

        public static final double RAMP_RATE = 0.25;

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getNativeUnits(1024);

        public static NativeUnitModel<Rotation2d> NATIVE_UNIT_MODEL = new NativeUnitRotationModel(
                SENSOR_UNIT_PER_ROTATION
        );
    }

    public static final class AREAS {
        public static final Length CARGOSHIP_BOX_X = LengthKt.getFeet(16.0);
        public static final Length CARGOSHIP_BOX_Y = LengthKt.getFeet(9.5);

        public static final Length CARGOSHIP_BOX_WIDTH = LengthKt.getFeet(11.0);
        public static final Length CARGOSHIP_BOX_HEIGHT = LengthKt.getFeet(8.0);

        public static final Length TOP_ROCKET_BOX_X = LengthKt.getFeet(16.0);
        public static final Length TOP_ROCKET_BOX_Y = LengthKt.getFeet(3.0);

        public static final Length BOTTOM_ROCKET_BOX_X = LengthKt.getFeet(16.0);
        public static final Length BOTTOM_ROCKET_BOX_Y = LengthKt.getFeet(0.0);

        public static final Length ROCKET_BOX_WIDTH = LengthKt.getFeet(6.0);
        public static final Length ROCKET_BOX_HEIGHT = LengthKt.getFeet(4.0);

        public static final Length LOADING_STATION_TOP_BOX_X = LengthKt.getFeet(0);
        public static final Length LOADING_STATION_TOP_BOX_Y = LengthKt.getFeet(23);

        public static final Length LOADING_STATION_BOTTOM_BOX_X = LengthKt.getFeet(0);
        public static final Length LOADING_STATION_BOTTOM_BOX_Y = LengthKt.getFeet(0);

        public static final Length LOADING_STATION_BOX_WIDTH = LengthKt.getFeet(4);
        public static final Length LOADING_STATION_BOX_HEIGHT = LengthKt.getFeet(4);

        public static final Rectangle2d CARGOSHIP_BOX = new Rectangle2d(CARGOSHIP_BOX_X, CARGOSHIP_BOX_Y, CARGOSHIP_BOX_WIDTH, CARGOSHIP_BOX_HEIGHT);

        public static final Rectangle2d TOP_ROCKET_BOX = new Rectangle2d(TOP_ROCKET_BOX_X, TOP_ROCKET_BOX_Y, ROCKET_BOX_WIDTH, ROCKET_BOX_HEIGHT);
        public static final Rectangle2d BOTTOM_ROCKET_BOX = new Rectangle2d(BOTTOM_ROCKET_BOX_X, BOTTOM_ROCKET_BOX_Y, ROCKET_BOX_WIDTH, ROCKET_BOX_HEIGHT);

        public static final Rectangle2d TOP_LOADING_STATION_BOX = new Rectangle2d(LOADING_STATION_TOP_BOX_X, LOADING_STATION_TOP_BOX_Y, LOADING_STATION_BOX_WIDTH, LOADING_STATION_BOX_HEIGHT);
        public static final Rectangle2d BOTTOM_LOADING_STATION_BOX = new Rectangle2d(LOADING_STATION_BOTTOM_BOX_X, LOADING_STATION_BOTTOM_BOX_Y, LOADING_STATION_BOX_WIDTH, LOADING_STATION_BOX_HEIGHT);

        public static final List<Rectangle2d> ALL_AREAS = Arrays.asList(CARGOSHIP_BOX, TOP_ROCKET_BOX, BOTTOM_ROCKET_BOX, TOP_LOADING_STATION_BOX, BOTTOM_LOADING_STATION_BOX);
    }

    public static final class CAMERA{
        public static final int MAX_X = 78;
        public static final int MAX_Y = 51;

        public static final double TURNING_kP = 0.001;
        public static final double FORWARD = 0.15;
    }
}
