package frc.robot.subsystems.drivetrain.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class AimLock extends InstantCommand {
    private double forward = 0.25, leftPower, rightPower;

    public AimLock() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void initialize(){
        double error;

        if(Camera.getInstance().isTargetVisible()){
            if(Camera.getInstance().getFrontJeVois().isTargetVisible()){
                error = Camera.getInstance().getFrontJeVois().getTargetAngle() * Constants.DRIVETRAIN.TURNING_kP;
                leftPower = forward + error;
                rightPower = forward - error;
            }else if(Camera.getInstance().getBackJeVois().isTargetVisible()){
                error = Camera.getInstance().getBackJeVois().getTargetAngle() * Constants.DRIVETRAIN.TURNING_kP;
                leftPower = -forward - error;
                rightPower = -forward + error;
            }

            Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, leftPower);
            Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, rightPower);
        } else{
            Drivetrain.getInstance().zeroOutputs();
        }
    }
}
