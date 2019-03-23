package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

@SuppressWarnings("FieldCanBeLocal")
public class AutoAlign extends Command {
    private double error, leftPower, rightPower;

    public AutoAlign() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        if (Camera.getInstance().hasLine()) {
            error = Constants.CAMERA.MIDDLE_X - Camera.getInstance().getLineX();
            leftPower = (error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.FORWARD;
            rightPower = (-error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.FORWARD;

            Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, leftPower, DemandType.ArbitraryFeedForward, Constants.DRIVETRAIN.kStaticFrictionPercentLeft);
            Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, rightPower, DemandType.ArbitraryFeedForward, Constants.DRIVETRAIN.kStaticFrictionPercentRight);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
