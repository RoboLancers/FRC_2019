package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.subsystems.drivetrain.commands.Turning;
import frc.robot.subsystems.manipulators.cargo.commands.ToggleCargoPivot;
import frc.robot.subsystems.manipulators.cargo.commands.UseCargoPivot;
import frc.robot.subsystems.manipulators.cargo.commands.UseFlywheel;
import frc.robot.subsystems.manipulators.cargo.enums.FlywheelPower;
import frc.robot.subsystems.manipulators.climber.commands.*;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchRelease;
import frc.robot.subsystems.manipulators.hatch.commands.ToggleHatchHolder;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;

@SuppressWarnings("unused")
public class OI {
    public static XboxController xboxController = new XboxController(0)
            .whenPressed(XboxController.Button.LEFT_BUMPER, new ToggleHatchHolder())
            .whenPressed(XboxController.Button.RIGHT_BUMPER, new AutoHatchRelease())

            .whenPressed(XboxController.Trigger.LEFT_TRIGGER, new UseClimberArmPower(0.5))
            .whenReleased(XboxController.Trigger.LEFT_TRIGGER, new UseClimberArmPower(0))

            .whenPressed(XboxController.Trigger.RIGHT_TRIGGER, new UseClimberArmPower(-0.5))
            .whenReleased(XboxController.Trigger.RIGHT_TRIGGER, new UseClimberArmPower(0))
            .whenPressed(XboxController.Button.START, new UseLiftoffPiston(LiftoffState.DOWN))
            .whenPressed(XboxController.Button.SELECT, new UseLiftoffPiston(LiftoffState.UP))

            .whenPressed(XboxController.Button.Y, new ToggleCargoPivot())
            .whenPressed(XboxController.Button.A, new UseFlywheel(FlywheelPower.OUTTAKE))
            .whenReleased(XboxController.Button.A, new UseFlywheel(FlywheelPower.STOP));

    public static FlightController flightController = new FlightController(1)
            .whenPressed(FlightController.Button.TRIGGER, new UseFlywheel(FlywheelPower.OUTTAKE))
            .whenReleased(FlightController.Button.TRIGGER, new UseFlywheel(FlywheelPower.STOP))

            .whenPressed(FlightController.Button.THUMB, new ToggleCargoPivot())

            .whenPressed(FlightController.Button.INNER_TOP, new UseClimberArmPower(-0.5))
            .whenReleased(FlightController.Button.INNER_TOP, new UseClimberArmPower(0.0))

            .whenPressed(FlightController.Button.OUTER_TOP, new UseClimberArmPower(0.5))
            .whenReleased(FlightController.Button.OUTER_TOP, new UseClimberArmPower(0.0))

            .whenPressed(FlightController.Button.INNER_BOTTOM, new UseInstantLiftoff(LiftoffState.UP))
            .whenPressed(FlightController.Button.OUTER_BOTTOM, new UseInstantLiftoff(LiftoffState.DOWN))
            .whenPressed(FlightController.Button.INNER_MIDDLE, new UseAutoClimb());
}
