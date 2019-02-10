package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import org.ghrobotics.lib.mathematics.twodim.geometry.Translation2d;

public class CargoAdjustment extends Command {
    public CargoAdjustment() {
        requires(CargoPivot.getInstance());
    }

    @Override
    protected void execute() {
        Translation2d robotTranslation = Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation();

        if(Constants.AREAS.CARGOSHIP_BOX.contains(robotTranslation)) {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        } else if(Constants.AREAS.TOP_ROCKET_BOX.contains(robotTranslation) || Constants.AREAS.BOTTOM_ROCKET_BOX.contains(robotTranslation)) {
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
