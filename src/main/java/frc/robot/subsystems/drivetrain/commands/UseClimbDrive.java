package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Sensors;

public class UseClimbDrive extends Command {
    public UseClimbDrive() {
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        Drivetrain.getInstance().getLeftMotor().setPercentOutput(-1);
        Drivetrain.getInstance().getRightMotor().setPercentOutput(-1);
    }

    @Override
    protected boolean isFinished() {
        return Sensors.getInstance().getPitch() <= Constants.CLIMBER.CLIMBING_MARGIN;
    }
}
