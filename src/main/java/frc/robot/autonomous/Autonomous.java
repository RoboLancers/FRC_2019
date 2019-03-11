package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.NetworkInterface;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routines.LevelOneFrontCargo;
import frc.robot.autonomous.routines.LevelOneRocket;
import frc.robot.autonomous.routines.LevelOneSideCargo;
import frc.robot.autonomous.routines.ConfigureStartingPosition;

@SuppressWarnings("FieldCanBeLocal")
public class Autonomous {
    private static Autonomous instance;

    private Command autonomousCommand;

    private StartingPosition selectedStartingPosition;
    private Objective selectedObjective;

    public void update(){
        selectedStartingPosition = NetworkInterface.getInstance().getStartingPositionChooser().getSelected();
        selectedObjective = NetworkInterface.getInstance().getObjectiveChooser().getSelected();

        if(selectedObjective == Objective.FRONT_CARGO_LEFT || selectedObjective == Objective.FRONT_CARGO_RIGHT){
            autonomousCommand = new LevelOneFrontCargo(selectedStartingPosition, selectedObjective);
        }else if(selectedObjective == Objective.SIDE_CARGO_FRONT || selectedObjective == Objective.SIDE_CARGO_MIDDLE || selectedObjective == Objective.SIDE_CARGO_BACK){
            autonomousCommand = new LevelOneSideCargo(selectedStartingPosition, selectedObjective);
        }else if(selectedObjective == Objective.LEFT_ROCKET || selectedObjective == Objective.RIGHT_ROCKET){
            autonomousCommand = new LevelOneRocket(selectedStartingPosition);
        }else if(selectedObjective == Objective.MANUAL){
            autonomousCommand = new ConfigureStartingPosition(selectedStartingPosition);
        }
    }

    public Command getAutonomousCommand() {
        return autonomousCommand;
    }

    public static synchronized Autonomous getInstance() {
        if (instance == null) {
            instance = new Autonomous();
        }

        return instance;
    }
}