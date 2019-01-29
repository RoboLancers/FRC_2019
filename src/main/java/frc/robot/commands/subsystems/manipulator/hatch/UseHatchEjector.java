package frc.robot.commands.subsystems.manipulator.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;

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
