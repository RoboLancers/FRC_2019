package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Sensors;

public class Turning extends Command {
    private double angle, output;
    private double target, error;

    public Turning(double angle) {
        requires(Drivetrain.getInstance());

        this.angle = angle;
    }

    @Override
    protected void initialize() {
        target = Sensors.getInstance().getFusedHeading() + angle;
    }

    @Override
    protected void execute() {
        error = target - Sensors.getInstance().getFusedHeading();
        output = error * Constants.DRIVETRAIN.TURNING_kP;
        Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, -output, DemandType.ArbitraryFeedForward, Math.signum(-output) * Constants.DRIVETRAIN.kStaticFrictionPercentLeft);
        Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, output, DemandType.ArbitraryFeedForward, Math.signum(output) * Constants.DRIVETRAIN.kStaticFrictionPercentRight);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < Constants.DRIVETRAIN.ALLOWABLE_ERROR;
    }
}
