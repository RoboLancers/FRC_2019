package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class ConfigureStartingPosition extends InstantCommand {
    private StartingPosition startingPosition;

    public ConfigureStartingPosition(StartingPosition startingPosition) {
        this.startingPosition = startingPosition;
    }

    @Override
    protected void initialize() {
        switch (startingPosition) {
            case LEVEL_1_LEFT:
                Drivetrain.getInstance().getLocalization().reset(Constants.ROBOT.LEVEL_1_LEFT_START);
                break;
            case LEVEL_1_CENTER:
                Drivetrain.getInstance().getLocalization().reset(Constants.ROBOT.LEVEL_1_CENTER_START);
                break;
            case LEVEL_1_RIGHT:
                Drivetrain.getInstance().getLocalization().reset(Constants.ROBOT.LEVEL_1_RIGHT_START);
                break;
        }
    }
}
