package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;

public class UseCargoPivot extends InstantCommand {
    private CargoPivotState cargoPivotState;

    public UseCargoPivot(CargoPivotState cargoPivotState) {
        requires(CargoPivot.getInstance());
        this.cargoPivotState = cargoPivotState;
    }

    @Override
    protected void initialize() {
        CargoPivot.getInstance().set(cargoPivotState);
        SmartDashboard.putString("This works", "Yes.");
    }
}
