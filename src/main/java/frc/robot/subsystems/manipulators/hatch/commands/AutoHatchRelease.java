package frc.robot.subsystems.manipulators.hatch.commands;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;
import frc.robot.subsystems.misc.commands.UseLED;

public class AutoHatchRelease extends CommandGroup {
    public AutoHatchRelease() {
        addSequential(new UseHatchHolder(HatchHolderState.RELEASE));
        addSequential(new WaitCommand(0.2));
        addSequential(new UseHatchEjector(HatchEjectorState.EJECT));
        addSequential(new WaitCommand(0.25));
        addSequential(new UseHatchEjector(HatchEjectorState.RETRACT));
    }

    @Override
    protected void initialize(){
        if(RobotController.isBrownedOut()){
            cancel();
            new UseLED(Blinkin.PatternType.STROBE_RED);
        }
    }
}