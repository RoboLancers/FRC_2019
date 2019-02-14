package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.robolancers.lib.auto.LancerPID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Sensors;

public class Turning extends Command {
    private double angle, output;
    private LancerPID lancerPID;

    public Turning(double angle) {
        requires(Drivetrain.getInstance());

        this.angle = angle;
        lancerPID = Drivetrain.getInstance().getTurningPID();
    }

    @Override
    protected void initialize() {
        lancerPID.reset();
        lancerPID.setTarget(Sensors.getInstance().getAngle() + angle);
    }

    @Override
    protected void execute() {
        output = lancerPID.getOutput(Sensors.getInstance().getAngle());
        Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, output, DemandType.ArbitraryFeedForward, Math.signum(output) * Constants.DRIVETRAIN.kStaticFrictionPercentLeft);
        Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, -output, DemandType.ArbitraryFeedForward, Math.signum(-output) * Constants.DRIVETRAIN.kStaticFrictionPercentRight);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(lancerPID.getError()) < Constants.DRIVETRAIN.ALLOWABLE_ERROR;
    }
}
