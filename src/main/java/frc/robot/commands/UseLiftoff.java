package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.Liftoff;
import org.ghrobotics.lib.commands.InstantCommand;

public class UseLiftoff extends Command {

    public UseLiftoff(){
        requires(Liftoff.getInstance());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
