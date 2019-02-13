package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;

public class HatchHolder extends Subsystem {
    private static HatchHolder instance;
    private DoubleSolenoid hatchHolder;

    private HatchHolder(){
        hatchHolder = new DoubleSolenoid(RobotMap.HATCH.MODULE, RobotMap.HATCH.HATCH_HOLDER_UP, RobotMap.HATCH.HATCH_HOLDER_DOWN);
    }

    public void set(HatchHolderState hatchHolderState){
        hatchHolder.set(hatchHolderState.getValue());
    }

    @Override
    protected void initDefaultCommand() {

    }

    public static HatchHolder getInstance(){
        if(instance == null){
            instance = new HatchHolder();
        }
        return instance;
    }
}