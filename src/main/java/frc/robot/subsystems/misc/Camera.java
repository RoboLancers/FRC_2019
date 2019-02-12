package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.vision.JeVois;
import edu.wpi.first.wpilibj.SerialPort;

public class Camera {
    private static Camera instance;

    private JeVois frontJeVois, backJeVois;

    private Camera(){
        frontJeVois = new JeVois(SerialPort.Port.kUSB1);
        backJeVois = new JeVois(SerialPort.Port.kUSB2);
    }

    public static synchronized Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }

    public JeVois getFrontJeVois() {
        return frontJeVois;
    }

    public JeVois getBackJeVois() {
        return backJeVois;
    }
}
