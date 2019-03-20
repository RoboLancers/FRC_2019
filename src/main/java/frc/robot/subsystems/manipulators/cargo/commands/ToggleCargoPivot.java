package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;

public class ToggleCargoPivot extends InstantCommand {
    public ToggleCargoPivot() {
        requires(CargoPivot.getInstance());
    }

    @Override
    protected void initialize() {
        CargoPivot.getInstance().set(CargoPivot.getInstance().get() == CargoPivotState.UP ? CargoPivotState.DOWN : CargoPivotState.UP);
    }
}
