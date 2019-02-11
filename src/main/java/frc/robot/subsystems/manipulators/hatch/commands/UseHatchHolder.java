package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class UseHatchHolder extends InstantCommand {
    private HatchHolderState hatchHolderState;

    public UseHatchHolder(HatchHolderState hatchHolderState){
        requires(HatchHolder.getInstance());
        this.hatchHolderState = hatchHolderState;
    }

    @Override
    protected void initialize() {
        HatchHolder.getInstance().set(hatchHolderState);
    }
}
