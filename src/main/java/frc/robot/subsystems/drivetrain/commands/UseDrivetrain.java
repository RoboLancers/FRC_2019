package frc.robot.subsystems.drivetrain.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class UseDrivetrain extends Command {
    public UseDrivetrain() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        double throttle = OI.xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        double turn = OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_X) * 0.35;

        Drivetrain.getInstance().curvatureDrive(throttle, turn, true);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}