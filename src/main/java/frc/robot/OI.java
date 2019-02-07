package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoPivot;
import frc.robot.commands.subsystems.manipulator.climber.AutoClimb;
import frc.robot.commands.subsystems.manipulator.climber.UseClimberArmPower;
import frc.robot.commands.subsystems.manipulator.climber.UseClimberArm;
import frc.robot.commands.subsystems.manipulator.climber.UseLiftoffPiston;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;

@SuppressWarnings("unused")
public class OI {
    public static XboxController xboxController = new XboxController(0)
            //.whenPressed(XboxController.Trigger.LEFT_TRIGGER, new UseHatchPivot(HatchPivotState.FLOOR))
            //.whenPressed(XboxController.Trigger.RIGHT_TRIGGER, new UseHatchPivot(HatchPivotState.DEFENSE))
            //.whenPressed(XboxController.Button.LEFT_BUMPER, new UseHatchPivot(HatchPivotState.SCORING))

            //.whenPressed(XboxController.Button.RIGHT_BUMPER, new UseHatchEjector(HatchEjectorState.EJECT))

            .whenPressed(XboxController.Button.START, new AutoClimb())

            //.whileHeld(XboxController.Button.A, new AimLock())

            .whenPressed(XboxController.Button.B, new UseClimberArmPower(-0.5))
            .whenReleased(XboxController.Button.B, new UseClimberArmPower(0))
            .whenPressed(XboxController.Button.X, new UseClimberArmPower(0.5))
            .whenReleased(XboxController.Button.X, new UseClimberArmPower(0));

    public static FlightController flightController = new FlightController(1)
            .whenPressed(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.UNBLOCK))
            .whenReleased(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.BLOCK))

            .whileHeld(FlightController.Button.OUTER_MIDDLE, new UseCargoPivot(CargoPivotState.DOWN))
            .whileHeld(FlightController.Button.INNER_MIDDLE, new UseCargoPivot(CargoPivotState.UP))

            .whenPressed(FlightController.Button.INNER_TOP, new UseClimberArm(ClimberState.DOWN))
            .whenPressed(FlightController.Button.OUTER_TOP, new UseClimberArm(ClimberState.UP))

            .whenPressed(FlightController.Button.INNER_BOTTOM, new UseLiftoffPiston(LiftoffState.UP))
            .whenPressed(FlightController.Button.OUTER_BOTTOM, new UseLiftoffPiston(LiftoffState.DOWN));
}
