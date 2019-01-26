package frc.robot.commands.subsystems.manipulator.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.hatch.HatchPistonState;
import frc.robot.subsystems.manipulators.hatch.HatchPiston;

public class UseHatchPiston extends InstantCommand {
    private HatchPistonState hatchState;

    public UseHatchPiston(HatchPistonState hatchPistonState) {
        requires(HatchPiston.getInstance());
        this.hatchState = hatchPistonState;
    }

    @Override
    protected void initialize() {
        if (hatchState == HatchPistonState.IN) {
            HatchPiston.getInstance().set(HatchPistonState.IN);
        } else {
            HatchPiston.getInstance().set(HatchPistonState.OUT);
        }
    }
}
