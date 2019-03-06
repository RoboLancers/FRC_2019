package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

public class AutoAlign extends InstantCommand {
    private double error, leftPower, rightPower;

    public AutoAlign() {
        requires(Drivetrain.getInstance());
    }

    @Override
    public void initialize() {
        if(Camera.getInstance().hasLine()){
            error = (Constants.CAMERA.MAX_X / 2.0) - Camera.getInstance().getX(Camera.getInstance().getLine());
            leftPower = (error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.forward;
            rightPower = (-error * Constants.CAMERA.TURNING_kP) + Constants.CAMERA.forward;

            Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, leftPower);
            Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, rightPower);
        }
    }
}
