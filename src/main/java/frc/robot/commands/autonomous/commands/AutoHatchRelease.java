package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;

public class AutoHatchRelease extends Command {
    public AutoHatchRelease() {
        requires(HatchEjector.getInstance());
    }

    @Override
    protected void execute() {
            HatchEjector.getInstance().set(HatchEjectorState.EJECT);
    }

    @Override
    protected boolean isFinished() {
        return !HatchEjector.getInstance().getLimitSwitch();
    }
}
