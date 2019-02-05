package frc.robot.commands.subsystems.drivetrain;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

public class UseDrivetrain extends Command {
    public UseDrivetrain() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        double throttle = OI.xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        double turn = OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_X) * 0.35;

        double leftPower = throttle + turn;
        double rightPower = throttle - turn;

        Drivetrain.getInstance().getLeftMotor().setVelocity(VelocityKt.getVelocity(LengthKt.getFeet(leftPower * Constants.DRIVETRAIN.MAX_VELOCITY)));
        Drivetrain.getInstance().getRightMotor().setVelocity(VelocityKt.getVelocity(LengthKt.getFeet(rightPower * Constants.DRIVETRAIN.MAX_VELOCITY)));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
