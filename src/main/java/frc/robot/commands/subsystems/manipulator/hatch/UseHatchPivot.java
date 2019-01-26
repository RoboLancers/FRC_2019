package frc.robot.commands.subsystems.manipulator.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.hatch.HatchPivotState;
import frc.robot.subsystems.manipulators.hatch.HatchPivot;

public class UseHatchPivot extends InstantCommand {

    private HatchPivotState hatchPivotState;

    public UseHatchPivot(HatchPivotState hatchPivotState) {
        requires(HatchPivot.getInstance());
        this.hatchPivotState = hatchPivotState;
    }

    @Override
    protected void initialize() {
        HatchPivot.getInstance().set(hatchPivotState);
    }
}
