package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.NetworkInterface;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routines.ConfigureStartingPosition;
import frc.robot.autonomous.routines.LevelOneBothFrontCargo;
import frc.robot.autonomous.routines.LevelOneBothRocket;
import frc.robot.autonomous.routines.LevelOneCargoRocket;

@SuppressWarnings("FieldCanBeLocal")
public class Autonomous {
    private static Autonomous instance;

    private Command autonomousCommand;

    private StartingPosition previouslySelectedStartingPosition, selectedStartingPosition;
    private Objective previouslySelectedObjective, selectedObjective;

    public static synchronized Autonomous getInstance() {
        if (instance == null) {
            instance = new Autonomous();
        }

        return instance;
    }

    public void update() {
        selectedStartingPosition = NetworkInterface.getInstance().getStartingPositionChooser().getSelected();
        selectedObjective = NetworkInterface.getInstance().getObjectiveChooser().getSelected();

        if (selectedObjective != previouslySelectedObjective || selectedStartingPosition != previouslySelectedStartingPosition) {
            if (selectedObjective == Objective.BOTH_FRONT_CARGO_LEFT || selectedObjective == Objective.BOTH_FRONT_CARGO_RIGHT) {
                autonomousCommand = new LevelOneBothFrontCargo(selectedStartingPosition, selectedObjective);
            } else if (selectedObjective == Objective.BOTH_LEFT_ROCKET || selectedObjective == Objective.BOTH_RIGHT_ROCKET) {
                autonomousCommand = new LevelOneBothRocket(selectedStartingPosition, selectedObjective);
            } else if (selectedObjective == Objective.CARGO_ROCKET_LEFT || selectedObjective == Objective.CARGO_ROCKET_RIGHT) {
                autonomousCommand = new LevelOneCargoRocket(selectedStartingPosition, selectedObjective);
            } else if (selectedObjective == Objective.MANUAL) {
                autonomousCommand = new ConfigureStartingPosition(selectedStartingPosition);
            }

            if (autonomousCommand != null) {
                NetworkInterface.getInstance().getCurrentlySelectedAutonomousEntry().setString(autonomousCommand.getName());
            }
        }

        previouslySelectedObjective = selectedObjective;
        previouslySelectedStartingPosition = selectedStartingPosition;
    }

    public Command getAutonomousCommand() {
        return autonomousCommand;
    }
}