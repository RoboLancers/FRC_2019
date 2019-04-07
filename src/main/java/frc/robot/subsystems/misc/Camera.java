package frc.robot.subsystems.misc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Camera {
    private static Camera instance;

    private UsbCamera frontLifecam, backLifecam;

    private Camera() {
        frontLifecam = CameraServer.getInstance().startAutomaticCapture();
        frontLifecam.setResolution(320, 240);
        frontLifecam.setFPS(15);
        backLifecam = CameraServer.getInstance().startAutomaticCapture();
        backLifecam.setResolution(240, 180);
        backLifecam.setFPS(15);
    }

    public static synchronized Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }

        return instance;
    }
}
