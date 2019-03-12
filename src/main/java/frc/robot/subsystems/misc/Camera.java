package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.vision.JeVois;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.SerialPort;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

@SuppressWarnings("unused")
public class Camera implements Loggable {
    private static Camera instance;

    //private JeVois frontJeVois, backJeVois;
    //private CvSource defaultCamera;

    private LancerPixy lancerPixy;

    private Camera(){
        //frontJeVois = new JeVois(SerialPort.Port.kUSB1);
        //backJeVois = new JeVois(SerialPort.Port.kUSB2);

        //defaultCamera = new CvSource("", new VideoMode(VideoMode.PixelFormat.kUnknown, 0, 0, 0));

        lancerPixy = new LancerPixy();
    }

    public static synchronized Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }

    /*
    public JeVois getFrontJeVois() {
        return frontJeVois;
    }

    @Log.CameraStream(name = "Front JeVois", rowIndex = 1, columnIndex = 0, width = 4, height = 4)
    public VideoSource getFrontJeVoisVideoSource() {
        return frontJeVois.getVisionCam() == null ? defaultCamera : frontJeVois.getVisionCam();
    }

    @Log.BooleanBox(name = "Front Target Detected", rowIndex = 0, columnIndex = 0, width = 2, height = 1)
    public boolean isFrontTargetDetected(){
        return frontJeVois.isTargetVisible();
    }

    @Log(name = "Front Target Distance", rowIndex = 0, columnIndex = 2, width = 2, height = 1)
    public double getFrontTargetDistance(){
        return frontJeVois.getTargetDistance();
    }

    public JeVois getBackJeVois() {
        return backJeVois;
    }

    @Log.CameraStream(name = "Back JeVois", rowIndex = 1, columnIndex = 4, width = 4, height = 4)
    public VideoSource getBackJeVoisVideoSource(){
        return backJeVois.getVisionCam() == null ? defaultCamera : backJeVois.getVisionCam();
    }

    @Log.BooleanBox(name = "Back Target Detected", rowIndex = 0, columnIndex = 4, width = 2, height = 1)
    public boolean isBackTargetDetected(){
        return backJeVois.isTargetVisible();
    }

    @Log(name = "Back Target Distance", rowIndex = 0, columnIndex = 6, width = 2, height = 1)
    public double backTargetDistance(){
        return backJeVois.getTargetDistance();
    }

    public boolean isTargetVisible(){
        return frontJeVois.isTargetVisible() || backJeVois.isTargetVisible();
    }*/

    public LancerPixy getLancerPixy() {
        return lancerPixy;
    }

    @Log.BooleanBox(name = "Line Detected", rowIndex = 0, columnIndex = 8, width = 2, height = 1)
    public boolean hasLine(){
        return lancerPixy.hasLine();
    }
}
