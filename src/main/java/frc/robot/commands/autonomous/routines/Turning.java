package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Sensors;

public class Turning extends Command {
    private double angle, targetAngle, error;

    public Turning(double angle) {
        requires(Drivetrain.getInstance());
        this.angle = angle;
    }

    @Override
    protected void initialize() {
        targetAngle = Sensors.getInstance().getAngle() + angle;
        error = targetAngle;
    }

    @Override
    protected void execute() {
        error = targetAngle - Sensors.getInstance().getAngle();
        Drivetrain.getInstance().getLeftMotor().setPercentOutput(error * Constants.DRIVETRAIN.TURNING_kP);
        Drivetrain.getInstance().getRightMotor().setPercentOutput(-error * Constants.DRIVETRAIN.TURNING_kP);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < 5;
    }
}
