package frc.robot;

import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitKt;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitLengthModel;
import org.ghrobotics.lib.mathematics.units.nativeunits.NativeUnitModel;

public class Constants {
    //NativeUnitModel tells me how many motor turns per wheel rotation 9.76:1
    public static NativeUnitModel<Length> nativeUnitModel = new NativeUnitLengthModel(
            NativeUnitKt.getSTU(9.76),
            LengthKt.getFeet(5)
    );

    public static final int DRIVETRAIN_PID_SLOT_INDEX = 0;
    public static final int DRIVETRAIN_PRIMARY_PID_LOOP = 0;

    public static final int DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION = 1024;
    public static final double DRIVETRAIN_MAX_RPM = 527.34;

    public static final double DRIVETRAIN_MOTION_PROFILE_P = 0.1;
    public static final double DRIVETRAIN_MOTION_PROFILE_I = 0;
    public static final double DRIVETRAIN_MOTION_PROFILE_D = 0.1;

    public static final double DRIVETRAIN_WHEEL_DIAMETER_FEETS = 0.51;

    //public static final double DRIVETRAIN_WHEELBASE = 6.75;
    public static final double DRIVETRAIN_WHEELBASE = 2.25;

    public static final double DRIVETRAIN_MAX_VELOCITY = 12; //12 feet per second

    public static final double DRIVETRAIN_KV = 1/DRIVETRAIN_MAX_VELOCITY;
    public static final double DRIVETRAIN_KA = 1.0/30.0;
    public static final double DRIVETRAIN_LEFT_KF = 1.21;
    public static final double DRIVETRAIN_RIGHT_KF = 1.17;

    public static final double DRIVETRAIN_ROTATE_P = 0.005;
    public static final double DRIVETRAIN_KP = 0;

    public static final int DRIVETRAIN_PEAK_CURRENT_LIMIT = 80;
    public static final int DRIVETRAIN_SUSTAINED_CURRENT_LIMIT = 36;
    public static final int DRIVETRAIN_PEAK_CURRENT_TIME_LIMIT = 10; //ms

    public static final int DRIVETRAIN_VOLTAGE_COMPENSATION = 12;
    public static final int DRIVETRAIN_FILTER_WINDOW_SAMPLE = 32;

    // Linear Slide Constants
    public static final int SLIDE_PID_SLOT_INDEX = 0;
    public static final int SLIDE_PROFILE_SLOT_INDEX = 0;

    public static final double SLIDE_NOMINAL_OUTPUT = 0.05;
    public static final double SLIDE_PEAK_OUTPUT = 0.7;

    public static final double SLIDE_OPEN_LOOP_RAMP = 0.5;

    public static final double SLIDE_KF = 0.21765957446;
    public static final double SLIDE_KP = 1;

    public static final int SLIDE_MAX_VELOCITY = 3525;
    public static final int SLIDE_MAX_ACCELERATION = 3525;
    public static final int SLIDE_ALLOWABLE_CLOSED_LOOP_ERROR = 1000;

    public static final int SLIDE_FORWARD_SOFT_LIMIT = 100000;
    public static final int SLIDE_REVERSE_SOFT_LIMIT = -1000;

    public static final int SLIDE_VOLTAGE_COMPENSATION = 12;
    public static final int SLIDE_FILTER_WINDOW_SAMPLE = 32;

    public static final int SLIDE_MAX_CURRENT = 20;

    //Intake Constants
    public static final int INTAKE_VOLTAGE_COMPENSATION = 12;
    public static final int INTAKE_FILTER_WINDOW_SAMPLE = 32;

    public static final int TIMEOUT_MS = 0;
}
