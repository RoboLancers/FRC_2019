package frc.robot;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.twodim.geometry.Rectangle2d;
import org.ghrobotics.lib.mathematics.units.*;
import org.ghrobotics.lib.mathematics.units.derivedunits.*;
import org.ghrobotics.lib.mathematics.units.nativeunits.*;

public class Constants {
    public static final double EPSILON = 1E-9;
    public static Time TIMEOUT = TimeUnitsKt.getMillisecond(0);

    public static final class PATH_FOLLOWING {
        public static final Velocity<Length> MAX_VELOCITY = VelocityKt.getVelocity(LengthKt.getFeet(10));
        public static final Acceleration<Length> MAX_ACCELERATION = AccelerationKt.getAcceleration(LengthKt.getFeet(10));

        public static final double RAMSETE_BETA = 1.4;
        public static final double RAMSETE_ZETA = 0.9;
    }

    @SuppressWarnings("WeakerAccess")
    public static final class ROBOT {
        public static final double MASS = 27;
        public static final double MOMENT_OF_INTERTIA = 10;
        public static final double ANGULAR_DRAG = 12;

        public static final Length ROBOT_LENGTH = LengthKt.getInch(33);
        public static final Length ROBOT_WIDTH = LengthKt.getInch(25.25);
        public static final Length BUMPER_THICKNESS = LengthKt.getInch(2.0);

        public static final Length MIDDLE_OF_ROBOT_X = ROBOT_LENGTH.div(2.0).plus(BUMPER_THICKNESS);

        public static final Length LEVEL_1_START_X = LengthKt.getFeet(5.25);

        public static final Pose2d LEVEL_1_LEFT = new Pose2d(LEVEL_1_START_X, LengthKt.getFeet(16.8), Rotation2dKt.getDegree(0));
        public static final Pose2d LEVEL_1_CENTER = new Pose2d(LEVEL_1_START_X, LengthKt.getFeet(13.5), Rotation2dKt.getDegree(0));
        public static final Pose2d LEVEL_1_RIGHT = new Pose2d(LEVEL_1_START_X, LengthKt.getFeet(10.3), Rotation2dKt.getDegree(0));

        public static final Length ROBOT_START_X_LEVEL_1 = LEVEL_1_START_X.plus(MIDDLE_OF_ROBOT_X);

        public static final Length LEFT_ROBOT_START_Y_LEVEL_1 = LEVEL_1_LEFT.getTranslation().getY().minus(ROBOT_WIDTH.div(2.0)).minus(BUMPER_THICKNESS);
        public static final Length CENTER_ROBOT_START_Y_LEVEL_1 = LEVEL_1_CENTER.getTranslation().getY();
        public static final Length RIGHT_ROBOT_START_Y_LEVEL_1 = LEVEL_1_RIGHT.getTranslation().getY().minus(ROBOT_WIDTH.div(2.0)).minus(BUMPER_THICKNESS);
    }

    @SuppressWarnings("WeakerAccess")
    public static final class DRIVETRAIN {
        public static final int PEAK_CURRENT_LIMIT = 40;
        public static final int CONTINUOUS_CURRENT_LIMIT = 36;
        public static final int PEAK_CURRENT_DURATION = 10;

        public static final int VOLTAGE_COMPENSATION = 12;
        public static final int VOLTAGE_MEASUREMENT_FILTER = 32;

        public static final double MAX_VELOCITY = 13;

        public static final double RAMP_RATE = 0.25;

        public static final double TALON_kF = 0.0;
        public static final double TALON_kP = 1.7;
        public static final double TALON_kI = 0.0;
        public static final double TALON_kD = 2.0;

        public static final double TURNING_kP = 0.01;
        public static final double ALLOWABLE_ERROR = 5;

        public static final double kStaticFrictionVoltage = 1.8;
        public static final double kV = 1 / 12.0;
        public static final double kA = 1 / 30.0;

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getNativeUnits(1024 * 9.76);
        public static final Length WHEEL_RADIUS = LengthKt.getInch(3);
        public static final Length TRACK_WIDTH = LengthKt.getMeter(0.634);

        public static NativeUnitModel<Length> NATIVE_UNIT_MODEL = new NativeUnitLengthModel(
                SENSOR_UNIT_PER_ROTATION,
                WHEEL_RADIUS
        );
    }

    @SuppressWarnings("WeakerAccess")
    public static final class CLIMBER {
        public static final Volt MINIMUM_VOLTAGE = VoltKt.getVolt(2);
        public static final double MINIMUM_PERCENT_OUT = MINIMUM_VOLTAGE.getValue() / 12.0;

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getNativeUnits(1024);

        public static NativeUnitModel<Rotation2d> NATIVE_UNIT_MODEL = new NativeUnitRotationModel(
                SENSOR_UNIT_PER_ROTATION
        );
    }

    @SuppressWarnings("WeakerAccess")
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

        public static final Rectangle2d CARGOSHIP_BOX = new Rectangle2d(CARGOSHIP_BOX_X, CARGOSHIP_BOX_Y, CARGOSHIP_BOX_WIDTH, CARGOSHIP_BOX_HEIGHT);
        public static final Rectangle2d TOP_ROCKET_BOX = new Rectangle2d(TOP_ROCKET_BOX_X, TOP_ROCKET_BOX_Y, ROCKET_BOX_WIDTH, ROCKET_BOX_HEIGHT);
        public static final Rectangle2d BOTTOM_ROCKET_BOX = new Rectangle2d(BOTTOM_ROCKET_BOX_X, BOTTOM_ROCKET_BOX_Y, ROCKET_BOX_WIDTH, ROCKET_BOX_HEIGHT);
    }
}
