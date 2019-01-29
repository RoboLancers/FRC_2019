package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.commands.subsystems.manipulator.climber.AutoClimb;
import frc.robot.commands.subsystems.manipulator.climber.UseClimberArm;
import frc.robot.commands.subsystems.manipulator.climber.UseLiftoffPiston;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchEjector;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPivot;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.enums.hatch.HatchEjectorState;
import frc.robot.enums.hatch.HatchPivotState;

public class OI {
    public static XboxController xboxController = new XboxController(0)
            .whenPressed(XboxController.Trigger.LEFT_TRIGGER_BUTTON, new UseHatchPivot(HatchPivotState.FLOOR))
            .whenPressed(XboxController.Trigger.RIGHT_TRIGGER_BUTTON, new UseHatchPivot(HatchPivotState.DEFENSE))
            .whenPressed(XboxController.Button.LEFT_BUMPER, new UseHatchPivot(HatchPivotState.SCORING))
            .whenPressed(XboxController.Button.RIGHT_BUMPER, new UseHatchEjector(HatchEjectorState.OUT))
            .whenPressed(XboxController.Button.START, new AutoClimb());

    public static FlightController flightController = new FlightController(1)
            .whenPressed(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.UNBLOCK))
            .whenReleased(FlightController.Button.TRIGGER, new UseCargoBlock(CargoBlockState.BLOCK))
            .whenPressed(FlightController.Button.INNER_BOTTOM, new UseClimberArm(ClimberState.DOWN))
            .whenPressed(FlightController.Button.FAR_BOTTOM, new UseClimberArm(ClimberState.UP))
            .whenPressed(FlightController.Button.INNER_MIDDLE, new UseLiftoffPiston(LiftoffState.UP))
            .whenPressed(FlightController.Button.FAR_MIDDLE, new UseLiftoffPiston(LiftoffState.DOWN));
}
