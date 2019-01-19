package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.misc.Pneumatic;

public class RegulateCompressor extends Command {

    public RegulateCompressor(){
        requires(Pneumatic.getInstance());
        setInterruptible(false);
    }

    @Override
    protected void execute(){
        Pneumatic.getInstance().regulateCompressor();
    }

    @Override
    protected void end(){
        Pneumatic.getInstance().stopCompressor();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
