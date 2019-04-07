package frc.robot.subsystems.manipulators.climber.commands;

import com.robolancers.lib.wrappers.hid.FlightController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArmJoystick extends Command {
    public UseClimberArmJoystick() {
        requires(ClimberArm.getInstance());
    }

    @Override
    protected void execute() {
        double power = OI.flightController.getAxisValue(FlightController.Axis.Y);

        ClimberArm.getInstance().set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
