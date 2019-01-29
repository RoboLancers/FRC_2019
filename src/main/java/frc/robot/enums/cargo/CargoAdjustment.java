package frc.robot.enums.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;

public class CargoAdjustment extends Command {
    public CargoAdjustment() {
        requires(CargoPivot.getInstance());
    }

    @Override
    protected void execute() {
        Pose2d robotPosition = Drivetrain.getInstance().getLocalization().getRobotPosition();

        if(Constants.RECTANGLES.CARGOSHIP.contains(robotPosition.getTranslation())) {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        } else if(Constants.RECTANGLES.TOP_ROCKET.contains(robotPosition.getTranslation()) || Constants.RECTANGLES.BOTTOM_ROCKET.contains(robotPosition.getTranslation())) {
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
