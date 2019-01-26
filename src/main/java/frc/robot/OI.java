package frc.robot;

import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoPivot;
import frc.robot.commands.subsystems.manipulator.climber.UseClimberArm;
import frc.robot.commands.subsystems.manipulator.climber.UseLiftoffPiston;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPiston;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPivot;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.enums.hatch.HatchPistonState;
import frc.robot.enums.hatch.HatchPivotState;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .toggleWhenPressed(XboxController.Button.START, new UseCargoPivot(CargoPivotState.UP))
            .toggleWhenPressed(XboxController.Button.SELECT, new UseCargoBlock(CargoBlockState.UP))
            .toggleWhenPressed(XboxController.Button.A, new UseHatchPiston(HatchPistonState.IN))
            .toggleWhenPressed(XboxController.Button.X, new UseHatchPivot(HatchPivotState.DOWN))
            .toggleWhenPressed(XboxController.Button.LEFT_JOYSTICK_BUTTON, new UseLiftoffPiston(LiftoffState.UP))
            .toggleWhenPressed(XboxController.Button.RIGHT_JOYSTICK_BUTTON, new UseClimberArm(ClimberState.UP));
}
