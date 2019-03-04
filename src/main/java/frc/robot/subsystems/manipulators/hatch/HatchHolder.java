package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.hatch.commands.AutoHatchClamp;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class HatchHolder extends Subsystem implements Loggable {
    private static HatchHolder instance;

    private DoubleSolenoid hatchHolder;

    private HatchHolder(){
        hatchHolder = new DoubleSolenoid(RobotMap.HATCH.MODULE, RobotMap.HATCH.HATCH_HOLDER_UP, RobotMap.HATCH.HATCH_HOLDER_DOWN);
    }

    @Log.ToString(name = "Hatch Holder State")
    public HatchHolderState get(){
        return hatchHolder.get() == HatchHolderState.HOLD.getValue() ? HatchHolderState.HOLD : HatchHolderState.RELEASE;
    }

    public void set(HatchHolderState hatchHolderState){
        hatchHolder.set(hatchHolderState.getValue());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new AutoHatchClamp());
    }

    public static HatchHolder getInstance(){
        if(instance == null){
            instance = new HatchHolder();
        }
        return instance;
    }

    @Override
    public String configureLogName(){
        return "Hatch";
    }
}
