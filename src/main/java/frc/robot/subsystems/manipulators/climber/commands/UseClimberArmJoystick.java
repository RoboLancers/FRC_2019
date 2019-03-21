package frc.robot.subsystems.manipulators.climber.commands;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArmJoystick extends Command {
    public UseClimberArmJoystick() {
        requires(ClimberArm.getInstance());
    }

    @Override
    protected void execute() {
        double power;

        if (Math.abs(OI.flightController.getAxisValue(FlightController.Axis.Y)) > 0.1) {
            power = OI.flightController.getAxisValue(FlightController.Axis.Y);
        } else if (OI.xboxController.getAxisValue(XboxController.Axis.LEFT_TRIGGER) > 0.1) {
            power = OI.xboxController.getAxisValue(XboxController.Axis.LEFT_TRIGGER);
        } else if (OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_TRIGGER) > 0.1) {
            power = -OI.xboxController.getAxisValue(XboxController.Axis.RIGHT_TRIGGER);
        } else {
            power = 0;
        }

        double rpm = Constants.rpm2rads(Constants.ticksPer100msToRPM(ClimberArm.getInstance().getMaster().getSelectedSensorVelocity()));
        double voltage = Constants.CLIMBER.currentLimit(power * Constants.CLIMBER.NOMINAL_VOLTAGE, rpm);

        ClimberArm.getInstance().set(voltage / Constants.DRIVETRAIN.NOMINAL_VOLTAGE);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
