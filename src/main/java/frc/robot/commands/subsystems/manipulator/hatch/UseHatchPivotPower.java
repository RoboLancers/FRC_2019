package frc.robot.commands.subsystems.manipulator.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.HatchPivot;

public class UseHatchPivotPower extends InstantCommand {
    private double power;

    public UseHatchPivotPower(double power) {
        requires(HatchPivot.getInstance());
        this.power = power;
    }

    @Override
    protected void initialize() {
        HatchPivot.getInstance().setPower(power);
    }
}
