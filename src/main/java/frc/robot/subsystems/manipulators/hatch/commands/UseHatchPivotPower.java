package frc.robot.subsystems.manipulators.hatch.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
        HatchPivot.getInstance().set(ControlMode.PercentOutput, power);
    }
}
