package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class ToggleHatchHolder extends InstantCommand {
    public ToggleHatchHolder(){
        requires(HatchHolder.getInstance());
    }

    @Override
    protected void initialize(){
        HatchHolder.getInstance().set(HatchHolder.getInstance().get() == HatchHolderState.RELEASE ? HatchHolderState.HOLD : HatchHolderState.RELEASE);
    }
}
