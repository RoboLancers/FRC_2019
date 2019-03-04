package frc.robot.subsystems.misc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Camera;

public class AutoAlign extends Command {
    Camera camera;
    public AutoAlign() {
        camera = Camera.getInstance();
    }

    @Override
    public void execute() {
        if(Camera.getInstance().hasLine()){
            Drivetrain.getInstance().
                    //turn towards line, must adjust for power to turn, must translate power into turn values which come from x
             //if x is too far left, turn left, if too far right, turn right ezpz, based on middle pixel
        }
    }



    @Override
    protected boolean isFinished() {
        return false;
    }
}
