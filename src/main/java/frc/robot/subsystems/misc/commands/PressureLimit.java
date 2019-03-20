package frc.robot.subsystems.misc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.misc.Pneumatics;

public class PressureLimit extends Command {
    public PressureLimit() {
        requires(Pneumatics.getInstance());

        setInterruptible(false);
    }

    protected void execute() {
        if(Pneumatics.getInstance().getPressure() < Constants.PNEUMATICS.MINIMUM_PRESSURE) {
            Pneumatics.getInstance().start();
        } else if(Pneumatics.getInstance().getPressure() > Constants.PNEUMATICS.MAXIMUM_PRESSURE) {
            Pneumatics.getInstance().stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
