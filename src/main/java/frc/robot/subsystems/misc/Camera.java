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

@SuppressWarnings("unused")
public class Camera implements Loggable {
    private static Camera instance;

    private JeVois frontJeVois, backJeVois;
    private Pixy2 pixy2;

    private CvSource defaultCamera;

    private Camera(){
        frontJeVois = new JeVois(SerialPort.Port.kUSB1);
        backJeVois = new JeVois(SerialPort.Port.kUSB2);

        pixy2 = Pixy2.createInstance(Pixy2.LinkType.SPI);
        pixy2.init();

        defaultCamera = new CvSource("", new VideoMode(VideoMode.PixelFormat.kUnknown, 0, 0, 0));
    }

    public Pixy2 getPixy2() {
        return pixy2;
    }

    public boolean hasLine() {
        return pixy2.getLine().getVectors()[0] != null;
    }

    public int getLine() {
        int maximum = 0;
        for(int i = 0; i < pixy2.getLine().getVectors().length; i++) {
            if(pixy2.getLine().getVectors()[i].getY1() - pixy2.getLine().getVectors()[i].getY0() > (pixy2.getLine().getVectors()[maximum].getY1() - pixy2.getLine().getVectors()[maximum].getY0())) {
                maximum = i;
            }
        }
        return maximum;
    }

    public int getX(int index) {
        return (pixy2.getLine().getVectors()[index].getX1() - pixy2.getLine().getVectors()[index].getX0()) / 2;
    }

    public int getY(int index) {
        return (pixy2.getLine().getVectors()[index].getY1() - pixy2.getLine().getVectors()[index].getY0()) / 2;
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

    public Pixy2 getPixy() {
        return pixy2;
    }

    public boolean isTargetVisible(){
        return frontJeVois.isTargetVisible() || backJeVois.isTargetVisible();
    }
}
