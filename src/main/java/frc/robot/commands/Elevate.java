package frc.robot.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.enums.LiftoffState;
import frc.robot.subsystems.manipulators.Climber;
import frc.robot.subsystems.manipulators.Liftoff;

public class Elevate extends Command {

    private LiftoffState liftoffState;

    public Elevate(LiftoffState liftoffState) {
        requires(Climber.getInstance());
        requires(Liftoff.getInstance());
        this.liftoffState = liftoffState;

    }

    protected void initialize(){
        Climber.getInstance().stop();
        if(liftoffState == LiftoffState.UP){
            Liftoff.getInstance().takeOff();
        }else{
            Liftoff.getInstance().land();
        }
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
