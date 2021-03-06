package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class AutoHatchClamp extends Command {
    private boolean previousState = false;
    private boolean currentState;

    public AutoHatchClamp() {
        requires(HatchHolder.getInstance());
    }

    protected void execute() {
        /*currentState = HatchEjector.getInstance().hasHatch();

        if (!previousState && currentState) {
            HatchHolder.getInstance().set(HatchHolderState.HOLD);
        }

        previousState = currentState;*/

        if (HatchHolder.getInstance().get() == HatchHolderState.HOLD) {
            OI.xboxController.setRumble(true);
        } else {
            OI.xboxController.setRumble(false);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
