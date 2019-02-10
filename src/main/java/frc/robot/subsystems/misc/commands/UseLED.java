package frc.robot.subsystems.misc.commands;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.misc.LED;

public class UseLED extends InstantCommand {

    private Blinkin.PatternType patternType;

    public UseLED(Blinkin.PatternType patternType) {
        requires(LED.getInstance());
        this.patternType = patternType;
    }

    @Override
    protected void initialize() {
        LED.getInstance().setPattern(patternType);
    }
}
