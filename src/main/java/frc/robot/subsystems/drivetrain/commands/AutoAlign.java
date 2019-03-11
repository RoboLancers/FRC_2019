package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;
import io.github.pseudoresonance.pixy2api.Pixy2Line;

public class AutoAlign extends Command {
    private double error, leftPower, rightPower;

    public AutoAlign() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        Pixy2Line.Vector vector = Camera.getInstance().getLancerPixy().getVector();

        if (vector != null) {
            error = (Constants.CAMERA.MAX_X / 2.0) - Camera.getInstance().getLancerPixy().getAverageX(vector);
            leftPower = (error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.FORWARD;
            rightPower = (-error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.FORWARD;

            Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, leftPower);
            Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, rightPower);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
