package frc.robot;

import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Time;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.Acceleration;
import org.ghrobotics.lib.mathematics.units.derivedunits.AccelerationKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.Velocity;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnit;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitLengthModel;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitModel;

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
}
