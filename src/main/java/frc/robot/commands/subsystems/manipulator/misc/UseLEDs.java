package frc.robot.commands.subsystems.manipulator.misc;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.misc.LEDs;

public class UseLEDs extends InstantCommand {

    private Blinkin.PatternType patternType;

    public UseLEDs(Blinkin.PatternType patternType) {
        requires(LEDs.getInstance());
        this.patternType = patternType;
    }

    @Override
    protected void initialize() {
        LEDs.getInstance().setPattern(patternType);
    }
}
