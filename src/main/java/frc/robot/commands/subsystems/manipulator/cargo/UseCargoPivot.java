package frc.robot.commands.subsystems.manipulator.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;

public class UseCargoPivot extends InstantCommand {
    private CargoPivotState cargoPivotState;

    public UseCargoPivot(CargoPivotState cargoPivotState) {
        requires(CargoPivot.getInstance());
        this.cargoPivotState = cargoPivotState;
    }

    @Override
    protected void initialize() {
        if (cargoPivotState == CargoPivotState.UP) {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        } else {
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }
    }

}
