package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.NetworkInterface;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routines.ConfigureStartingPosition;
import frc.robot.autonomous.routines.LevelOneBothFrontCargo;
import frc.robot.autonomous.routines.LevelOneBothRocket;
import frc.robot.autonomous.routines.LevelOneSideCargo;

@SuppressWarnings("FieldCanBeLocal")
public class Autonomous {
    private static Autonomous instance;

    private Command autonomousCommand;

    private StartingPosition previousSelectedStartingPosition;
    private Objective previousSelectedObjective;

    private StartingPosition selectedStartingPosition;
    private Objective selectedObjective;

    public static synchronized Autonomous getInstance() {
        if (instance == null) {
            instance = new Autonomous();
        }

        return instance;
    }

    public void update() {
        selectedStartingPosition = NetworkInterface.getInstance().getStartingPositionChooser().getSelected();
        selectedObjective = NetworkInterface.getInstance().getObjectiveChooser().getSelected();

        if(previousSelectedStartingPosition != null && previousSelectedObjective != null && previousSelectedStartingPosition != selectedStartingPosition && previousSelectedObjective != selectedObjective) {
            if (selectedObjective == Objective.BOTH_FRONT_CARGO_LEFT || selectedObjective == Objective.BOTH_FRONT_CARGO_RIGHT) {
                autonomousCommand = new LevelOneBothFrontCargo(selectedStartingPosition, selectedObjective);
            } else if (selectedObjective == Objective.SIDE_CARGO_FRONT || selectedObjective == Objective.SIDE_CARGO_MIDDLE || selectedObjective == Objective.SIDE_CARGO_BACK) {
                autonomousCommand = new LevelOneSideCargo(selectedStartingPosition, selectedObjective);
            } else if (selectedObjective == Objective.BOTH_LEFT_ROCKET || selectedObjective == Objective.BOTH_RIGHT_ROCKET) {
                autonomousCommand = new LevelOneBothRocket(selectedStartingPosition);
            } else if (selectedObjective == Objective.MANUAL) {
                autonomousCommand = new ConfigureStartingPosition(selectedStartingPosition);
            }

            previousSelectedStartingPosition = selectedStartingPosition;
            previousSelectedObjective = selectedObjective;
        }
    }

    public Command getAutonomousCommand() {
        return autonomousCommand;
    }
}