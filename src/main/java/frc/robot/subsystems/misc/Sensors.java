package frc.robot.subsystems.misc;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors{
    private AHRS gyro;
    public volatile double angle;

    private static Sensors instance;

    private Sensors() {
        gyro = new AHRS(SerialPort.Port.kMXP);

        new Notifier(() -> angle = -gyro.getAngle()).startPeriodic(0.01);
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }

    public double getPitch() {
        return gyro.getPitch();
    }

    public double getRoll() {
        return gyro.getRoll();
    }

    public void resetHeading() {
        gyro.reset();
    }
}
