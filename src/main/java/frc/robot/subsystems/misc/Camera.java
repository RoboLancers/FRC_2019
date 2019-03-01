package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.vision.JeVois;
import edu.wpi.first.wpilibj.SerialPort;
import io.github.pseudoresonance.pixy2api.Pixy2;

public class Camera {
    private static Camera instance;

    private JeVois frontJeVois, backJeVois;
    private Pixy2 pixy;

    private Camera(){
        frontJeVois = new JeVois(SerialPort.Port.kUSB1);
        backJeVois = new JeVois(SerialPort.Port.kUSB2);
        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init();
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

    public Pixy2 getPixy() {
        return pixy;
    }

    public boolean isTargetVisible(){
        return frontJeVois.isTargetVisible() || backJeVois.isTargetVisible();
    }
}
