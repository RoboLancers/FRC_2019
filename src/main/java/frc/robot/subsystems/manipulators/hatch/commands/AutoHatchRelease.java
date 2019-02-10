package frc.robot.subsystems.manipulators.hatch.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
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
    protected void end(){
        HatchEjector.getInstance().set(HatchEjectorState.RETRACT);
    }

    @Override
    protected boolean isFinished() {
        return !HatchEjector.getInstance().hasHatch();
    }
}
