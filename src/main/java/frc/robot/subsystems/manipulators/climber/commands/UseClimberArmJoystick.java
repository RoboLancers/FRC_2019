package frc.robot.subsystems.manipulators.climber.commands;

import com.robolancers.lib.wrappers.hid.FlightController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArmJoystick extends Command {
    public UseClimberArmJoystick(){
        requires(ClimberArm.getInstance());
    }

    @Override
    protected void execute(){
        ClimberArm.getInstance().set(OI.flightController.getAxisValue(FlightController.Axis.Y));
    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}
