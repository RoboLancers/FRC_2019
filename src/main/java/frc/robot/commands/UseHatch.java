package frc.robot.commands;

import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OI;
import frc.robot.enums.HatchPivotState;
import frc.robot.enums.HatchState;
import frc.robot.subsystems.manipulators.Hatch;

public class UseHatch extends InstantCommand {
    private HatchState hatchState;

    public UseHatch(HatchState hatchState) {
        requires(Hatch.getInstance());
        this.hatchState = hatchState;
    }

    @Override
    protected void initialize(){
        if(hatchState == HatchState.IN){
            Hatch.getInstance().setForward();
        } else {
            Hatch.getInstance().setBack();
        }
    }
}
