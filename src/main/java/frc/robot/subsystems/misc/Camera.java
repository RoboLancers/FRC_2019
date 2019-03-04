package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.vision.JeVois;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;
import io.github.pseudoresonance.pixy2api.Pixy2;

public class Camera implements Loggable {
    private static Camera instance;

    private JeVois frontJeVois, backJeVois;
    private Pixy2 pixy;

    private CvSource defaultCamera;

    private Camera(){
        frontJeVois = new JeVois(SerialPort.Port.kUSB1);
        backJeVois = new JeVois(SerialPort.Port.kUSB2);

        pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy.init();

        defaultCamera = new CvSource("", new VideoMode(VideoMode.PixelFormat.kUnknown, 0, 0, 0));
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

    @Log.CameraStream(name = "Front JeVois")
    public VideoSource getFrontJeVoisVideoSource() {
        return frontJeVois.getVisionCam() == null ? defaultCamera : frontJeVois.getVisionCam();
    }

    @Log.BooleanBox(name = "Front Target Detected")
    public boolean isFrontTargetDetected(){
        return frontJeVois.isTargetVisible();
    }

    @Log(name = "Front Target Distance")
    public double getFrontTargetDistance(){
        return frontJeVois.getTargetDistance();
    }

    public JeVois getBackJeVois() {
        return backJeVois;
    }

    @Log.CameraStream(name = "Back JeVois")
    public VideoSource getBackJeVoisVideoSource(){
        return backJeVois.getVisionCam() == null ? defaultCamera : backJeVois.getVisionCam();
    }

    @Log.BooleanBox(name = "Back Target Detected")
    public boolean isBackTargetDetected(){
        return backJeVois.isTargetVisible();
    }

    @Log(name = "Back Target Distance")
    public double backTargetDistance(){
        return backJeVois.getTargetDistance();
    }

    public Pixy2 getPixy() {
        return pixy;
    }

    public boolean isTargetVisible(){
        return frontJeVois.isTargetVisible() || backJeVois.isTargetVisible();
    }
}
