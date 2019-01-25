package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.HatchPivotState;
import frc.robot.subsystems.manipulators.HatchPivot;

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
