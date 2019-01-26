package frc.robot;


import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.subsystems.manipulator.cargo.ToggleCargoPivot;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoPivot;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPiston;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPivot;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.hatch.HatchPivotState;
import frc.robot.enums.hatch.HatchPistonState;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .whenPressed(XboxController.Button.START, new ToggleCargoPivot())
            .whenPressed(XboxController.Button.SELECT, new UseCargoBlock(CargoBlockState.UP))
            .whenPressed(XboxController.Button.A, new UseHatchPiston(HatchPistonState.IN))
            .whenPressed(XboxController.Button.X, new UseHatchPivot(HatchPivotState.DOWN));
    public static FlightController flightController = new FlightController(1);
}