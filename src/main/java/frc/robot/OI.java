package frc.robot;


import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.*;
import frc.robot.enums.*;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .toggleWhenPressed(XboxController.Button.START, new UseCargoPivot(CargoPivotState.UP))
            .toggleWhenPressed(XboxController.Button.SELECT, new UseCargoBlock(CargoBlockState.UP))
            .toggleWhenPressed(XboxController.Button.RIGHT_JOYSTICK_BUTTON, new Elevate(LiftoffState.UP, ClimberState.UP))
            .toggleWhenPressed(XboxController.Button.A, new UseHatch(HatchState.IN))
            .toggleWhenPressed(XboxController.Button.X, new UseHatchPivot(HatchPivotState.DOWN));
}
