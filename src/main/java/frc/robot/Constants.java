package frc.robot;

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

    public static final class ROBOT {
        public static final double MASS = 27;
        public static final double MOMENT_OF_INTERTIA = 10;
        public static final double ANGULAR_DRAG = 12;
    }

    @SuppressWarnings("WeakerAccess")
    public static final class DRIVETRAIN {
        public static final int PEAK_CURRENT_LIMIT = 80;
        public static final int CONTINUOUS_CURRENT_LIMIT = 36;
        public static final int PEAK_CURRENT_DURATION = 10;

        public static final int VOLTAGE_COMPENSATION = 12;
        public static final int VOLTAGE_MEASUREMENT_FILTER = 32;

        public static final double kF = 0.0;
        public static final double kP = 1.7;
        public static final double kI = 0.0;
        public static final double kD = 2.0;

        public static final double kStaticFrictionVoltage = 1.8;
        public static final double kV = 1 / 12.0;
        public static final double kA = 1 / 30.0;

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getSTU(1024);
        public static final Length WHEEL_RADIUS = LengthKt.getInch(3);
        public static final Length TRACK_WIDTH = LengthKt.getMeter(0.634);

        public static NativeUnitModel<Length> NATIVE_UNIT_MODEL = new NativeUnitLengthModel(
                SENSOR_UNIT_PER_ROTATION,
                WHEEL_RADIUS
        );
    }

    public static final class CLIMBER {
        public static final Volt MINIMUM_VOLTAGE = VoltKt.getVolt(2);

        public static final NativeUnit SENSOR_UNIT_PER_ROTATION = NativeUnitKt.getSTU(1024);

        public static NativeUnitModel<Rotation2d> NATIVE_UNIT_MODEL = new NativeUnitRotationModel(
                SENSOR_UNIT_PER_ROTATION
        );
    }

    @SuppressWarnings("WeakerAccess")
    public static final class RECTANGLES {
        public static final Length CARGOSHIP_X = LengthKt.getFeet(16.0);
        public static final Length CARGOSHIP_Y = LengthKt.getFeet(9.5);
        public static final Length CARGOSHIP_WIDTH = LengthKt.getFeet(11.0);
        public static final Length CARGOSHIP_HEIGHT = LengthKt.getFeet(8.0);

        public static final Length TOP_ROCKET_X = LengthKt.getFeet(16.0);
        public static final Length TOP_ROCKET_Y = LengthKt.getFeet(3.0);

        public static final Length BOTTOM_ROCKET_X = LengthKt.getFeet(16.0);
        public static final Length BOTTOM_ROCKET_Y = LengthKt.getFeet(0.0);

        public static final Length ROCKET_WIDTH = LengthKt.getFeet(6.0);
        public static final Length ROCKET_HEIGHT = LengthKt.getFeet(4.0);

        public static final Rectangle2d CARGOSHIP = new Rectangle2d(CARGOSHIP_X, CARGOSHIP_Y, CARGOSHIP_WIDTH, CARGOSHIP_HEIGHT);
        public static final Rectangle2d TOP_ROCKET = new Rectangle2d(TOP_ROCKET_X, TOP_ROCKET_Y, ROCKET_WIDTH, ROCKET_HEIGHT);
        public static final Rectangle2d BOTTOM_ROCKET = new Rectangle2d(BOTTOM_ROCKET_X, BOTTOM_ROCKET_Y, ROCKET_WIDTH, ROCKET_HEIGHT);

    }
}
