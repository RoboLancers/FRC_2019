package frc.robot.subsystems.misc;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {
    private AHRS navX;

    private static Sensors instance;

    private Sensors() {
        navX = new AHRS(SerialPort.Port.kMXP);
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }

    public double getAngle() {
        return -navX.getAngle();
    }

    public double getRoll() {
        return navX.getRoll();
    }

    public void resetNavX() {
        navX.reset();
    }
}
