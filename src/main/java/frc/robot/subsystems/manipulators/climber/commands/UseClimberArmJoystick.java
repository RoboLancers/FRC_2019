package frc.robot.subsystems.manipulators.climber.commands;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
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

//        if(OI.xboxController.getState(XboxController.Trigger.LEFT_TRIGGER)) {
//            ClimberArm.getInstance().set(OI.xboxController.getAxisValue(XboxController.Axis.LEFT_TRIGGER));
//        } else if (OI.xboxController.getState(XboxController.Trigger.RIGHT_TRIGGER)) {
//            ClimberArm.getInstance().set(-OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_TRIGGER));
//        } else{
//            ClimberArm.getInstance().set(0);
//        }
    }

    @Override
    protected boolean isFinished(){
        return false;
    }
}
