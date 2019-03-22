package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;

@SuppressWarnings({"FieldCanBeLocal"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab competitionTab;

    private SendableChooser<StartingPosition> startingPositionChooser;
    private SendableChooser<Objective> objectiveChooser;

    private NetworkTableEntry currentlySelectedAutonomousEntry;

    private NetworkInterface() {
        competitionTab = Shuffleboard.getTab("Robot");

        startingPositionChooser = new SendableChooser<>();
        objectiveChooser = new SendableChooser<>();

        for (StartingPosition startingPosition : StartingPosition.values()) {
            startingPositionChooser.addOption(startingPosition.name(), startingPosition);
        }

        for (Objective objective : Objective.values()) {
            objectiveChooser.addOption(objective.name(), objective);
        }

        competitionTab.add("Starting Position Chooser", startingPositionChooser).withPosition(0, 0).withSize(2, 1);
        competitionTab.add("Objective Chooser", objectiveChooser).withPosition(2, 0).withSize(2, 1);

        currentlySelectedAutonomousEntry = competitionTab.add("Currently Selected Autonomous", "").withPosition(0, 1).withSize(4, 1).getEntry();
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }

    public SendableChooser<StartingPosition> getStartingPositionChooser() {
        return startingPositionChooser;
    }

    public SendableChooser<Objective> getObjectiveChooser() {
        return objectiveChooser;
    }

    public NetworkTableEntry getCurrentlySelectedAutonomousEntry(){
        return currentlySelectedAutonomousEntry;
    }
}
