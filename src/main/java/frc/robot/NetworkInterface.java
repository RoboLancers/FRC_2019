package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.drivetrain.Drivetrain;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab mainShuffleboardDisplay;
    private NetworkTableEntry leftEncoderCountEntry, rightEncoderCountEntry;

    private NetworkInterface(){
        mainShuffleboardDisplay = Shuffleboard.getTab("Main Display");

        leftEncoderCountEntry = mainShuffleboardDisplay.add("Left Encoder Count", 0.0).getEntry();
        rightEncoderCountEntry = mainShuffleboardDisplay.add("Right Encoder Count", 0.0).getEntry();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
