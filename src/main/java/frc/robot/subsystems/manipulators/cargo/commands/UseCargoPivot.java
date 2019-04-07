package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;

public class UseCargoPivot extends InstantCommand {
    private CargoPivotState cargoPivotState;

    public UseCargoPivot(CargoPivotState cargoPivotState) {
        requires(CargoPivot.getInstance());
        this.cargoPivotState = cargoPivotState;
    }

    @Override
    protected void initialize() {
        CargoPivot.getInstance().set(cargoPivotState);
    }
}
