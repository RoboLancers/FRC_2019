package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.HatchPivot;

public class UseHatchPivot extends Command {

    private double power;

    public UseHatchPivot(double power) {
        requires(HatchPivot.getInstance());
        this.power = power;
    }

    @Override
    protected void initialize() {
        HatchPivot.getInstance().stop();
    }


    @Override
    protected void execute() {
        HatchPivot.getInstance().setAll(power);

    }

    @Override
    protected void end() {
        HatchPivot.getInstance().stop();
    }


    @Override
    protected boolean isFinished() {
        return false;
    }
}
