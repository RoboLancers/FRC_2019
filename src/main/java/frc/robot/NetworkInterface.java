package frc.robot;

import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoSource.Kind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.Sensors;
import org.opencv.videoio.VideoCapture;

import java.util.Map;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab mainShuffleboardDisplay;

    private NetworkTableEntry
            leftEncoderCountEntry, rightEncoderCountEntry,
            armEncoderEntry, armAngleEntry, armErrorEntry, liftOffPistonStateEntry, armLimitSwitchVoltage,
            cargoBlockStateEntry, cargoPivotStateEntry,
            robotAngleEntry;

    private ComplexWidget frontJeVoisWidget, backJeVoisWidget;

    private NetworkInterface(){
        mainShuffleboardDisplay = Shuffleboard.getTab("Main Display");

        leftEncoderCountEntry = mainShuffleboardDisplay.add("Left Encoder Count", 0.0).withPosition(0,0).withSize(2, 1).getEntry();
        rightEncoderCountEntry = mainShuffleboardDisplay.add("Right Encoder Count", 0.0).withPosition(2, 0).withSize(2, 1).getEntry();

        armEncoderEntry = mainShuffleboardDisplay.add("Arm Encoder Count", 0.0).withPosition(0, 1).withSize(2, 1).getEntry();
        armAngleEntry = mainShuffleboardDisplay.add("Arm Angle", 0.0).withPosition(2, 1).withSize(2, 1).getEntry();
        armErrorEntry = mainShuffleboardDisplay.add("Arm Closed Loop Error", 0.0).withPosition(0, 2).withSize(2, 1).getEntry();
        armLimitSwitchVoltage = mainShuffleboardDisplay.add("Arm Voltage", 0.0).withPosition(2, 2).withSize(2, 1).getEntry();

        liftOffPistonStateEntry = mainShuffleboardDisplay.add("Liftoff Piston State", "").withPosition(0, 3).withSize(4, 1).getEntry();

        cargoBlockStateEntry = mainShuffleboardDisplay.add("Cargo Block State", "").withPosition(0, 4).withSize(2, 1).getEntry();
        cargoPivotStateEntry = mainShuffleboardDisplay.add("Cargo Pivot State", "").withPosition(2, 4).withSize(2, 1).getEntry();

        robotAngleEntry = mainShuffleboardDisplay.add("Robot Angle", 0.0).withPosition(0, 5).withSize(2, 1).getEntry();

        frontJeVoisWidget = mainShuffleboardDisplay.add(Camera.getInstance().getFrontJeVois().getVisionCam()).withPosition(4, 0).withSize(2, 2);
        backJeVoisWidget = mainShuffleboardDisplay.add(Camera.getInstance().getBackJeVois().getVisionCam()).withPosition(4, 2).withSize(2, 2);

        Shuffleboard.startRecording();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());

        armEncoderEntry.setDouble(ClimberArm.getInstance().getPosition());
        armAngleEntry.setDouble(ClimberArm.getInstance().getAngle());
        armErrorEntry.setDouble(ClimberArm.getInstance().getClosedLoopError());
        armLimitSwitchVoltage.setDouble(ClimberArm.getInstance().getVoltage());

        liftOffPistonStateEntry.setString(LiftoffPiston.getInstance().get().toString());

        cargoBlockStateEntry.setString(CargoBlock.getInstance().get().toString());
        cargoPivotStateEntry.setValue(CargoPivot.getInstance().get().toString());

        robotAngleEntry.setDouble(Sensors.getInstance().getAngle());
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
