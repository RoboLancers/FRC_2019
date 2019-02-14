package frc.robot.subsystems.misc.commands;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.misc.LED;

public class LEDAdjustment extends Command {
    public LEDAdjustment(){
        requires(LED.getInstance());
    }

    @Override
    protected void execute(){
        if(HatchEjector.getInstance().hasHatch()){
            LED.getInstance().setPattern(Blinkin.PatternType.COLORWAVES_RAINBOW_PALETTE);
        } else{
            LED.getInstance().setPattern(Blinkin.PatternType.HEARTBEAT_FASTER2);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
