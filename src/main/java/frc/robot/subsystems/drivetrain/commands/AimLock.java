package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

public class AimLock extends InstantCommand {
    private double forward = 0.25;

    public AimLock() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void initialize(){
        double error;

        if(Camera.getInstance().getFrontJeVois().isTargetVisible()){
            error = Camera.getInstance().getFrontJeVois().getTargetAngle();
            forward *= 1;

            Drivetrain.getInstance().getLeftMotor().setPercentOutput(forward + error);
            Drivetrain.getInstance().getRightMotor().setPercentOutput(forward - error);
        }else if(Camera.getInstance().getBackJeVois().isTargetVisible()){
            error = Camera.getInstance().getBackJeVois().getTargetAngle();
            forward *= -1;

            Drivetrain.getInstance().getLeftMotor().setPercentOutput(forward - error);
            Drivetrain.getInstance().getRightMotor().setPercentOutput(forward + error);
        }else{
            Drivetrain.getInstance().zeroOutputs();
        }
    }
}
