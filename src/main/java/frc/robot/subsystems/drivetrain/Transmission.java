package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.team254.lib.physics.DCMotorTransmission;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.enums.TransmissionSide;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class Transmission {
    private FalconSRX<Length> master;
    private List<FalconSRX<Length>> allMotors;

    private DCMotorTransmission dcMotorTransmission;

    Transmission(TransmissionSide side, int masterPort, int slave1Port, int slave2Port) {
        master = new FalconSRX<>(masterPort, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        FalconSRX<Length> slave1 = new FalconSRX<>(slave1Port, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        FalconSRX<Length> slave2 = new FalconSRX<>(slave2Port, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);

        master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        slave1.follow(master);
        slave2.follow(master);

        if(side == TransmissionSide.RIGHT) {
            dcMotorTransmission = new DCMotorTransmission(
                    1 / Constants.DRIVETRAIN.kVRight,
                    Math.pow(Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(), 2) * Constants.ROBOT.MASS / (2 * Constants.DRIVETRAIN.kARight),
                    Constants.DRIVETRAIN.kStaticFrictionVoltageRight
            );
        }else{
            dcMotorTransmission = new DCMotorTransmission(
                    1 / Constants.DRIVETRAIN.kVLeft,
                    Math.pow(Constants.DRIVETRAIN.WHEEL_RADIUS.getValue(), 2) * Constants.ROBOT.MASS / (2 * Constants.DRIVETRAIN.kALeft),
                    Constants.DRIVETRAIN.kStaticFrictionVoltageLeft
            );
        }

        allMotors = Arrays.asList(master, slave1, slave2);

        for (FalconSRX<Length> motor : allMotors) {
            if(side == TransmissionSide.RIGHT) {
                motor.setInverted(true);

                motor.setKF(Constants.DRIVETRAIN.RIGHT_KF);
            }else{
                motor.setSensorPhase(true);

                motor.setKF(Constants.DRIVETRAIN.LEFT_kF);
            }

            motor.setOpenLoopRamp(TimeUnitsKt.getSecond(Constants.DRIVETRAIN.RAMP_RATE));

            motor.setKP(Constants.DRIVETRAIN.TALON_kP);
            motor.setKI(Constants.DRIVETRAIN.TALON_kI);
            motor.setKD(Constants.DRIVETRAIN.TALON_kD);

            motor.configPeakCurrentLimit(Constants.DRIVETRAIN.PEAK_CURRENT_LIMIT);
            motor.configContinuousCurrentLimit(Constants.DRIVETRAIN.CONTINUOUS_CURRENT_LIMIT);
            motor.configPeakCurrentDuration(Constants.DRIVETRAIN.PEAK_CURRENT_DURATION);
            motor.enableCurrentLimit(true);

            motor.configVoltageCompSaturation(Constants.DRIVETRAIN.VOLTAGE_COMPENSATION);
            motor.configVoltageMeasurementFilter(Constants.DRIVETRAIN.VOLTAGE_MEASUREMENT_FILTER);
            motor.enableVoltageCompensation(true);
        }

        master.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10);
        master.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    }

    public FalconSRX<Length> getMaster() {
        return master;
    }

    public DCMotorTransmission getDcMotorTransmission(){
        return dcMotorTransmission;
    }
}
