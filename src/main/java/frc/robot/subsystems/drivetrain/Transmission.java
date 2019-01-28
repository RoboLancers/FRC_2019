package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import frc.robot.Constants;
import frc.robot.enums.drivetrain.TransmissionSide;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
class Transmission {
    private FalconSRX<Length> master;
    private List<FalconSRX<Length>> allMotors;

    Transmission(TransmissionSide side, int masterPort, int slave1Port, int slave2Port) {
        master = new FalconSRX<>(masterPort, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        FalconSRX<Length> slave1 = new FalconSRX<>(slave1Port, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        FalconSRX<Length> slave2 = new FalconSRX<>(slave2Port, Constants.DRIVETRAIN.NATIVE_UNIT_MODEL, Constants.TIMEOUT);

        slave1.follow(master);
        slave2.follow(master);

        allMotors = Arrays.asList(master, slave1, slave2);

        for (FalconSRX<Length> motor : allMotors) {
            if (side == TransmissionSide.LEFT) {
                motor.setInverted(InvertType.InvertMotorOutput);
            }

            motor.setKF(Constants.DRIVETRAIN.kF);
            motor.setKP(Constants.DRIVETRAIN.kP);
            motor.setKI(Constants.DRIVETRAIN.kI);
            motor.setKD(Constants.DRIVETRAIN.kD);

            motor.configPeakCurrentLimit(Constants.DRIVETRAIN.PEAK_CURRENT_LIMIT);
            motor.configContinuousCurrentLimit(Constants.DRIVETRAIN.CONTINUOUS_CURRENT_LIMIT);
            motor.configPeakCurrentDuration(Constants.DRIVETRAIN.PEAK_CURRENT_DURATION);

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
}
