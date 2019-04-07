package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.sensors.REVAnalogPressureSensor;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.misc.commands.PressureLimit;

public class Pneumatics extends Subsystem {
    private static Pneumatics instance;

    private REVAnalogPressureSensor revAnalogPressureSensor;

    private Compressor compressor;

    private Pneumatics() {
        revAnalogPressureSensor = new REVAnalogPressureSensor(RobotMap.MISC.REV_PRESSURE_SENSOR_PORT);

        compressor = new Compressor();
    }

    public synchronized static Pneumatics getInstance() {
        if (instance == null) {
            instance = new Pneumatics();
        }

        return instance;
    }

    public double getPressure() {
        return revAnalogPressureSensor.getPressure();
    }

    public void start() {
        compressor.start();
    }

    public void stop() {
        compressor.stop();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new PressureLimit());
    }
}
