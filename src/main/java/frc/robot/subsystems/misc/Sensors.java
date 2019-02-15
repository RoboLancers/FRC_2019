package frc.robot.subsystems.misc;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

@SuppressWarnings("WeakerAccess")
public class Sensors{
    private PigeonIMU pigeonIMU;
    private double[] yawPitchRoll;

    private static Sensors instance;

    private Sensors() {
        TalonSRX pigeonTalon = new TalonSRX(RobotMap.MISC.PIGEON_TALON);
        pigeonIMU = new PigeonIMU(pigeonTalon);

        pigeonTalon.configFactoryDefault();
        pigeonIMU.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR, 10);

        calibratePigeon();

        yawPitchRoll = new double[3];
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }

    public double getAngle() {
        return pigeonIMU.getFusedHeading();
    }

    public double getPitch() {
        pigeonIMU.getYawPitchRoll(yawPitchRoll);
        return yawPitchRoll[1];
    }

    public void resetHeading() {
        pigeonIMU.setFusedHeading(0);
    }

    public void calibratePigeon(){
        pigeonIMU.enterCalibrationMode(PigeonIMU.CalibrationMode.Temperature);
    }
}
