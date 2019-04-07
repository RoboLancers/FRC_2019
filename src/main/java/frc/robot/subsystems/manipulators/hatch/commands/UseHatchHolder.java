package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class UseHatchHolder extends InstantCommand {
    private HatchHolderState holderState;

    public UseHatchHolder(HatchHolderState hatchHolderState) {
        requires(HatchHolder.getInstance());
        this.holderState = hatchHolderState;
    }

    @Override
    protected void initialize() {
        HatchHolder.getInstance().set(holderState);
    }
}