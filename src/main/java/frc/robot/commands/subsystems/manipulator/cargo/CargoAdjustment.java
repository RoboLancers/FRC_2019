package frc.robot.commands.subsystems.manipulator.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.enums.cargo.CargoPivotState;
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

        if(Constants.AREAS.CARGOSHIP_BOX.contains(robotPosition.getTranslation())) {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        } else if(Constants.AREAS.TOP_ROCKET_BOX.contains(robotPosition.getTranslation()) || Constants.AREAS.BOTTOM_ROCKET_BOX.contains(robotPosition.getTranslation())) {
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
