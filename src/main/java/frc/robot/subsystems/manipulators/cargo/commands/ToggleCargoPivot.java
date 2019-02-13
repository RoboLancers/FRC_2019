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
        if(CargoPivot.getInstance().get().getValue().equals(CargoPivotState.UP)){
            CargoPivot.getInstance().set(CargoPivotState.DOWN);
        }else {
            CargoPivot.getInstance().set(CargoPivotState.UP);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
