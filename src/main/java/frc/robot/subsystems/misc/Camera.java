package frc.robot.subsystems.misc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Camera{
    private static Camera instance;

    private PixyArduino pixyArduino;
    private UsbCamera lifecam;

    private Camera(){
        pixyArduino = new PixyArduino();

        lifecam = CameraServer.getInstance().startAutomaticCapture();
        lifecam.setResolution(320, 240);
        lifecam.setFPS(15);
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

    public boolean hasLine(){
        return pixyArduino.hasLine();
    }

    public double getLineX(){
        return pixyArduino.getLineX();
    }
}
