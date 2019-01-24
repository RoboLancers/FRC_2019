package frc.robot;


import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.UseCargoBlock;
import frc.robot.commands.UseCargoPivot;
import frc.robot.enums.CargoBlockState;
import frc.robot.enums.CargoPivotState;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .toggleWhenPressed(XboxController.Button.START, new UseCargoPivot(CargoPivotState.UP))
            .toggleWhenPressed(XboxController.Button.SELECT, new UseCargoBlock(CargoBlockState.UP));
}
