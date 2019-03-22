package frc.robot.subsystems.misc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Camera {
    private static Camera instance;

    private PixyArduino pixyArduino;
    private UsbCamera frontLifecam, backLifecam;

    private Camera() {
        pixyArduino = new PixyArduino();

        frontLifecam = CameraServer.getInstance().startAutomaticCapture();
        frontLifecam.setResolution(320, 240);
        frontLifecam.setFPS(15);

        backLifecam = CameraServer.getInstance().startAutomaticCapture();
        backLifecam.setResolution(160, 120);
        backLifecam.setFPS(15);
    }

    public static synchronized Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }

    public PixyArduino getPixyArduino() {
        return pixyArduino;
    }

    public boolean hasLine() {
        return pixyArduino.hasLine();
    }

    public double getLineX() {
        return pixyArduino.getLineX();
    }
}
