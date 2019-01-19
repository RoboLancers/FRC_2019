package frc.robot.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class UseDrivetrain extends Command {

    public UseDrivetrain(){
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute(){
        Drivetrain.getInstance().arcadeDrive(
                OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_X),
                OI.xboxController.getAxisValue(XboxController.Axis.LEFT_Y)
        );
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
