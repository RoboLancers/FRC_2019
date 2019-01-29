package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.hatch.HatchPistonState;
import frc.robot.subsystems.manipulators.hatch.HatchPiston;

public class AutoHatchPiston extends Command {
    private HatchPistonState hatchState;

    public AutoHatchPiston(HatchPistonState hatchPistonState) {
        requires(HatchPiston.getInstance());
        this.hatchState = hatchPistonState;
    }

    @Override
    protected void initialize() {
        if (hatchState == HatchPistonState.IN && HatchPiston.getInstance().getLimitSwitch()) {
            HatchPiston.getInstance().set(HatchPistonState.IN);
        } else {
            HatchPiston.getInstance().set(HatchPistonState.OUT);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
