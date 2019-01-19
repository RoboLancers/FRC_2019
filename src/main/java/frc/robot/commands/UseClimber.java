package frc.robot.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.ClimberState;
import frc.robot.subsystems.manipulators.Climber;

public class UseClimber extends Command {

    public UseClimber() {
        requires(Climber.getInstance());

    }

    protected void initialize(){
        Climber.getInstance().stop();
    }
    protected void execute(){
        if(OI.xboxController.getButtonState(XboxController.Button.X)){
            Climber.getInstance().setClimberMotorUp(1);
        } else if (OI.xboxController.getButtonState(XboxController.Button.Y)){
            Climber.getInstance().setClimberMotorDown(-1);
        } else {
            Climber.getInstance().stop();
        }
    }

    protected void end(){
        Climber.getInstance().stop();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
