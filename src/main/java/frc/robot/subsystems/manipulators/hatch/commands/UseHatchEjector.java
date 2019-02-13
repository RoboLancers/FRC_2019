package frc.robot.subsystems.manipulators.hatch.commands;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.misc.LED;

public class UseHatchEjector extends InstantCommand {
    private HatchEjectorState hatchState;

    public UseHatchEjector(HatchEjectorState hatchEjectorState) {
        requires(HatchEjector.getInstance());
        this.hatchState = hatchEjectorState;

        if(HatchEjector.getInstance().hasHatch() == true){
            LED.getInstance().setPattern(Blinkin.PatternType.COLORWAVES_RAINBOW_PALETTE);
        } else{
            LED.getInstance().setPattern(Blinkin.PatternType.HEARTBEAT_FASTER2);
        }
    }

    @Override
    protected void initialize() {
        HatchEjector.getInstance().set(hatchState);
    }
}
