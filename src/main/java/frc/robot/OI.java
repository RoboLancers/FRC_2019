package frc.robot;


import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoPivot;
import frc.robot.commands.subsystems.manipulator.climber.Elevate;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatch;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPivot;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.enums.hatch.HatchPivotState;
import frc.robot.enums.hatch.HatchState;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .toggleWhenPressed(XboxController.Button.START, new UseCargoPivot(CargoPivotState.UP))
            .toggleWhenPressed(XboxController.Button.SELECT, new UseCargoBlock(CargoBlockState.UP))
            .toggleWhenPressed(XboxController.Button.RIGHT_JOYSTICK_BUTTON, new Elevate(LiftoffState.UP, ClimberState.UP))
            .toggleWhenPressed(XboxController.Button.A, new UseHatch(HatchState.IN))
            .toggleWhenPressed(XboxController.Button.X, new UseHatchPivot(HatchPivotState.DOWN));
}
