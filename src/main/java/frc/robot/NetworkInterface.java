package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;

@SuppressWarnings({"FieldCanBeLocal"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab competitionTab;

    private SendableChooser<StartingPosition> startingPositionChooser;
    private SendableChooser<Objective> objectiveChooser;

    private NetworkInterface(){
        competitionTab = Shuffleboard.getTab("Robot");

        startingPositionChooser = new SendableChooser<>();
        objectiveChooser = new SendableChooser<>();

        for(StartingPosition startingPosition : StartingPosition.values()){
            startingPositionChooser.addOption(startingPosition.name(), startingPosition);
        }

        for(Objective objective : Objective.values()){
            objectiveChooser.addOption(objective.name(), objective);
        }

        competitionTab.add("Starting Position Chooser", startingPositionChooser);
        competitionTab.add("Objective Chooser", objectiveChooser);
    }

    public SendableChooser<StartingPosition> getStartingPositionChooser(){
        return startingPositionChooser;
    }

    public SendableChooser<Objective> getObjectiveChooser(){
        return objectiveChooser;
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
