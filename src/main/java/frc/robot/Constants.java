package frc.robot;

import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Time;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.Acceleration;
import org.ghrobotics.lib.mathematics.units.derivedunits.AccelerationKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.Velocity;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitLengthModel;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitModel;

public class Constants {
    //NativeUnitModel tells me how many motor turns per wheel rotation 9.76:1
    public static final Time TIMEOUT_MS = TimeUnitsKt.getSecond(0);

    public static final double kRobotMass = 54.53 /* Robot */ + 5.669 /* Battery */ + 7; /* Bumpers */ // kg
    public static final double kMomentOfInertia = 10.0;
    public static final double kRobotAngularDrag = 12.0;

    public static final Velocity<Length> maxVelocity = VelocityKt.getVelocity(LengthKt.getFeet(10));
    public static final Acceleration<Length> maxAcceleration = AccelerationKt.getAcceleration(LengthKt.getFeet(4));

    // Drivetrain Constants
    public static final Length kTrackWidth = LengthKt.getMeter(0.6858);

    public static final int kDrivetrainEncoderTicksPerRev = 1024;

    public static final double kDrivetrainWheelDiameter = 0.51;
    public static final Length kDrivetrainWheelRadius = LengthKt.getFeet(kDrivetrainWheelDiameter / 2);

    public static final double kDrivetrainWheelbase = 2.25;

    public static final double kDrivetrainStaticFrictionVolt = 1.8;

    public static final double kDrivetrainKV = 0.0769;
    public static final double kDrivetrainKA = 0.0333;

    public static final double kDrivetrainBeta = 1.4;
    public static final double kDrivetrainZeta = 0.9;

    public static final double kDrivetrainF = 0;
    public static final double kDrivetrainKP = 0.0001;

    public static final int kCurrentLimit = 80;

    public static final NativeUnitModel<Length> nativeUnitModel = new NativeUnitLengthModel(
            NativeUnitKt.getSTU(kDrivetrainEncoderTicksPerRev),
            kDrivetrainWheelRadius
    );
}
