package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;

public class ToggleCargoPivot extends Command {

    public ToggleCargoPivot() {
        requires(CargoPivot.getInstance());
    }

    @Override
    protected void execute(){
        CargoPivot.getInstance().set(CargoPivotState.UP.getValue().equals(CargoPivotState.DOWN) ? CargoPivotState.UP: CargoPivotState.DOWN);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
