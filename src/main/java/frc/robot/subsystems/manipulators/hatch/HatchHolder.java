package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class HatchHolder extends Subsystem {
    private static HatchHolder instance;

    private DoubleSolenoid holder;

    private HatchHolder() {
       holder = new DoubleSolenoid(RobotMap.HATCH.HOLDER1_OUT, RobotMap.HATCH.HOLDER1_IN);

    }

    public synchronized static HatchHolder getInstance() {
        if(instance == null) {
            instance = new HatchHolder();
        }
        return instance;
    }

    public void set(HatchHolderState hatchHolderState) {
        holder.set(hatchHolderState.getValue());
    }

    public HatchHolderState get() {
        return holder.get() == HatchHolderState.HOLD.getValue() ? HatchHolderState.HOLD : HatchHolderState.RELEASE;
    }


    @Override
    protected void initDefaultCommand() {

    }
}
