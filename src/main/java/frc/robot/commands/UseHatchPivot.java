package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.HatchPivot;

public class UseHatchPivot extends Command {

    private int position;

    public UseHatchPivot(int position){
        requires(HatchPivot.getInstance());
        this.position = position;
    }

    @Override
    protected void initialize(){
        HatchPivot.getInstance().setPosition(position);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
