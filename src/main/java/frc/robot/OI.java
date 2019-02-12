package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.cargo.commands.UseCargoBlock;
import frc.robot.subsystems.manipulators.cargo.commands.UseCargoPivot;
import frc.robot.subsystems.manipulators.climber.commands.UseClimberArm;
import frc.robot.subsystems.manipulators.climber.commands.UseClimberArmPower;
import frc.robot.subsystems.manipulators.climber.commands.UseLiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchEjector;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchHolder;
import frc.robot.subsystems.manipulators.hatch.commands.UseHatchPivotPower;
import frc.robot.subsystems.manipulators.cargo.enums.CargoBlockState;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

@SuppressWarnings("unused")
public class OI {
    public static XboxController xboxController = new XboxController(0)
            .whenPressed(XboxController.Trigger.LEFT_TRIGGER, new UseHatchHolder(HatchHolderState.HOLD))
            .whenPressed(XboxController.Button.LEFT_BUMPER, new UseHatchHolder(HatchHolderState.RELEASE))

            .whenPressed(XboxController.Button.RIGHT_BUMPER, new UseHatchEjector(HatchEjectorState.EJECT))
            .whenReleased(XboxController.Button.RIGHT_BUMPER, new UseHatchEjector(HatchEjectorState.RETRACT))

            .whenPressed(XboxController.Button.Y, new Turning(-90))

            .whenPressed(XboxController.Button.START, new UseClimberArmPower(0.5))
            .whenReleased(XboxController.Button.START, new UseClimberArmPower(0))

            .whenPressed(XboxController.Button.SELECT, new UseClimberArmPower(-0.5))
            .whenReleased(XboxController.Button.SELECT, new UseClimberArmPower(0));

    public static FlightController flightController = new FlightController(1)
            .whenPressed(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.UNBLOCK))
            .whenReleased(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.BLOCK))

            .whenPressed(FlightController.Button.BOTTOM_LEFT, new UseCargoPivot(CargoPivotState.DOWN))
            .whenPressed(FlightController.Button.TOP_LEFT, new UseCargoPivot(CargoPivotState.UP))

            .whenPressed(FlightController.Button.INNER_TOP, new UseClimberArm(ClimberState.DOWN))
            .whenPressed(FlightController.Button.OUTER_TOP, new UseClimberArm(ClimberState.UP))

            .whenPressed(FlightController.Button.INNER_BOTTOM, new UseLiftoffPiston(LiftoffState.UP))
            .whenPressed(FlightController.Button.OUTER_BOTTOM, new UseLiftoffPiston(LiftoffState.DOWN));
}
