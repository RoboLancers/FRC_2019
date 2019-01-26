package frc.robot.commands.subsystems.manipulator.cargo;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;

public class ToggleCargoPivot extends InstantCommand {
    public ToggleCargoPivot() {
        requires(CargoPivot.getInstance());
    }

    @Override
    protected void initialize() {
        CargoPivot.getInstance().set(CargoPivot.getInstance().get() == CargoPivotState.UP ? CargoPivotState.DOWN : CargoPivotState.UP);
    }
}
