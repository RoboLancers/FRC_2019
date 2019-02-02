package frc.robot.commands.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
        double turn = OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_X) * 0.50;

        Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, throttle + turn);
        Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, throttle - turn);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
