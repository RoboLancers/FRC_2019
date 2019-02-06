package frc.robot;

import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoSource.Kind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.Sensors;
import org.opencv.videoio.VideoCapture;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab mainShuffleboardDisplay;

    private NetworkTableEntry
            leftEncoderCountEntry, rightEncoderCountEntry,
            armEncoderEntry, armAngleEntry, armErrorEntry, liftOffPistonStateEntry, armLimitSwitchVoltage,
            cargoBlockStateEntry, cargoPivotStateEntry,
            robotAngleEntry;

    private NetworkInterface(){
        mainShuffleboardDisplay = Shuffleboard.getTab("Main Display");

        leftEncoderCountEntry = mainShuffleboardDisplay.add("Left Encoder Count", 0.0).getEntry();
        rightEncoderCountEntry = mainShuffleboardDisplay.add("Right Encoder Count", 0.0).getEntry();

        armEncoderEntry = mainShuffleboardDisplay.add("Arm Encoder Count", 0.0).getEntry();
        armAngleEntry = mainShuffleboardDisplay.add("Arm Angle", 0.0).getEntry();
        armErrorEntry = mainShuffleboardDisplay.add("Arm Closed Loop Error", 0.0).getEntry();
        armLimitSwitchVoltage = mainShuffleboardDisplay.add("Arm Voltage", 0.0).getEntry();

        liftOffPistonStateEntry = mainShuffleboardDisplay.add("Liftoff Piston State", "").getEntry();

        cargoBlockStateEntry = mainShuffleboardDisplay.add("Cargo Block State", "").getEntry();
        cargoPivotStateEntry = mainShuffleboardDisplay.add("Cargo Pivot State", "").getEntry();

        robotAngleEntry = mainShuffleboardDisplay.add("Robot Angle", 0.0).getEntry();

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
