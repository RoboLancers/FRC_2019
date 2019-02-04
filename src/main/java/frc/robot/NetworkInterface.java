package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.misc.Sensors;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab mainShuffleboardDisplay;
    private NetworkTableEntry leftEncoderCountEntry, rightEncoderCountEntry, armEncoderEntry, armAngleEntry, robotAngleEntry;

    private NetworkInterface(){
        mainShuffleboardDisplay = Shuffleboard.getTab("Main Display");

        leftEncoderCountEntry = mainShuffleboardDisplay.add("Left Encoder Count", 0.0).getEntry();
        rightEncoderCountEntry = mainShuffleboardDisplay.add("Right Encoder Count", 0.0).getEntry();
        armEncoderEntry = mainShuffleboardDisplay.add("Arm Encoder Count", 0.0).getEntry();
        armAngleEntry = mainShuffleboardDisplay.add("Arm Angle", 0.0).getEntry();
        robotAngleEntry = mainShuffleboardDisplay.add("Robot Angle", 0.0).getEntry();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());
        armEncoderEntry.setDouble(ClimberArm.getInstance().getPosition());
        armAngleEntry.setDouble(ClimberArm.getInstance().getAngle());
        robotAngleEntry.setDouble(Sensors.getInstance().getAngle());
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
