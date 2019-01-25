package frc.robot.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.Hatch;

public class UseHatch extends InstantCommand {

    public UseHatch() {
        requires(Hatch.getInstance());
    }


    @Override
    protected boolean isFinished() {
        return false;
    }
}
