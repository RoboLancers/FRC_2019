package frc.robot.subsystems.manipulators.cargo.commands;

import com.robolancers.lib.wrappers.hid.FlightController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;

public class ToggleCargoPivot extends Command {
    CargoPivotState cargoPivotState;

    public ToggleCargoPivot() {
        requires(CargoBlock.getInstance());

        if(cargoPivotState == CargoPivotState.DOWN && OI.flightController.getState(FlightController.Button.THUMB)) {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        }else {
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
