package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;

public class UseHatchEjector extends InstantCommand {
    private HatchEjectorState hatchState;

    public UseHatchEjector(HatchEjectorState hatchEjectorState) {
        requires(HatchEjector.getInstance());
        this.hatchState = hatchEjectorState;
    }

    @Override
    protected void initialize() {
        HatchEjector.getInstance().set(hatchState);
    }
}
