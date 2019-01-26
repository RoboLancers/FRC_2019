package frc.robot.subsystems.misc;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {

    private AHRS navX;
    public static Sensors instance;

    Sensors() {
        navX = new AHRS(SerialPort.Port.kUSB);
    }

    public double getAngle() {
        return -navX.getAngle();
    }

    public double getHeading() {
        return navX.getFusedHeading();
    }

    public void resetNavX() {
        navX.reset();
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }


    @Override
    protected void initDefaultCommand() {

    }
}
