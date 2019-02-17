package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class AutoHatchClamp extends Command {
    public AutoHatchClamp() {
        requires(HatchHolder.getInstance());
    }

    protected void execute() {
        if(HatchEjector.getInstance().hasHatch()) {
            HatchHolder.getInstance().set(HatchHolderState.HOLD);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
