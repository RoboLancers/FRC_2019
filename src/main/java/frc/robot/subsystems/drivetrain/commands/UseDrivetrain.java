package frc.robot.subsystems.drivetrain.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

public class UseDrivetrain extends Command {
    public static double turnModifier = 0.4;

    public UseDrivetrain() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        if(OI.xboxController.getState(XboxController.Button.B)){
            if(turnModifier == 0.4){
                turnModifier = 1;
            }else{
                turnModifier = 0.4;
            }
        }

        double throttle = OI.xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        double turn = OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_X) * 0.4;

        double leftVelocity = (throttle + turn);
        double rightVelocity = (throttle - turn);

        Drivetrain.getInstance().getLeftMotor().setPercentOutput(leftVelocity);
        Drivetrain.getInstance().getRightMotor().setPercentOutput(rightVelocity * 0.95);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
