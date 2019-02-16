package frc.robot.subsystems.misc;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

@SuppressWarnings("WeakerAccess")
public class Sensors{
    private AHRS gyro;

    private static Sensors instance;

    private Sensors() {
        gyro = new AHRS(SerialPort.Port.kMXP);
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }

    public synchronized double getAngle() {
        return gyro.getAngle();
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
