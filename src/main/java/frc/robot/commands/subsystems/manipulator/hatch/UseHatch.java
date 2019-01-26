package frc.robot.commands.subsystems.manipulator.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.hatch.HatchState;
import frc.robot.subsystems.manipulators.hatch.Hatch;

public class UseHatch extends InstantCommand {
    private HatchState hatchState;

    public UseHatch(HatchState hatchState) {
        requires(Hatch.getInstance());
        this.hatchState = hatchState;
    }

    @Override
    protected void initialize() {
        if (hatchState == HatchState.IN) {
            Hatch.getInstance().setForward();
        } else {
            Hatch.getInstance().setBack();
        }
    }
}
