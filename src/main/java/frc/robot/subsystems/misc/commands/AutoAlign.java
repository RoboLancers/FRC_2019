package frc.robot.subsystems.misc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

public class AutoAlign extends Command {
    double error, leftPower, rightPower;

    public AutoAlign() {
        requires(Drivetrain.getInstance());
    }

    @Override
    public void execute() {
        if(Camera.getInstance().hasLine()){

        }
    }



    @Override
    protected boolean isFinished() {
        return false;
    }
}
