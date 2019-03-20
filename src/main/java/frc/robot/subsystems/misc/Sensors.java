package frc.robot.subsystems.misc;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.robolancers.lib.wrappers.sensors.REVAnalogPressureSensor;
import frc.robot.RobotMap;

@SuppressWarnings("unused")
public class Sensors {
    private static Sensors instance;

    private PigeonIMU pigeonIMU;
    private double[] yawPitchRoll;

    private REVAnalogPressureSensor analogPressureSensor;

    private Sensors() {
        TalonSRX pigeonTalon = new TalonSRX(RobotMap.MISC.PIGEON_TALON);
        pigeonIMU = new PigeonIMU(pigeonTalon);

        yawPitchRoll = new double[3];

        analogPressureSensor = new REVAnalogPressureSensor(0);
    }

    public synchronized static Sensors getInstance() {
        if (instance == null) {
            instance = new Sensors();
        }
        return instance;
    }

    private void updateYawPitchRoll() {
        pigeonIMU.getYawPitchRoll(yawPitchRoll);
    }

    public double getFusedHeading() {
        return pigeonIMU.getFusedHeading();
    }

    public double getYaw() {
        updateYawPitchRoll();
        return yawPitchRoll[0];
    }

    public double getPitch() {
        updateYawPitchRoll();
        return yawPitchRoll[1];
    }

    public double getRoll() {
        updateYawPitchRoll();
        return yawPitchRoll[2];
    }

    public void resetHeading() {
        pigeonIMU.setFusedHeading(0);
    }

    public double getPressure() {
        return analogPressureSensor.getPressure();
    }
}
