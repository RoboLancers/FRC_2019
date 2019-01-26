package frc.robot.subsystems.manipulators.hatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPiston;
import frc.robot.enums.hatch.HatchPistonState;

public class HatchPiston extends Subsystem {

    private DoubleSolenoid hatch1, hatch2, hatch3;
    private static HatchPiston instance;


    public HatchPiston() {
        hatch1 = new DoubleSolenoid(RobotMap.HATCH1_FORWARD, RobotMap.HATCH1_REVERSE);
        hatch2 = new DoubleSolenoid(RobotMap.HATCH2_FORWARD, RobotMap.HATCH2_REVERSE);
        hatch3 = new DoubleSolenoid(RobotMap.HATCH3_FORWARD, RobotMap.HATCH3_REVERSE);
    }

    public void set(HatchPistonState hatchPistonState) {
        hatch1.set(hatchPistonState.getValue());
        hatch2.set(hatchPistonState.getValue());
        hatch3.set(hatchPistonState.getValue());
    }

    public synchronized static HatchPiston getInstance() {
        if (instance == null) {
            instance = new HatchPiston();
        }
        return instance;
    }


    @Override
    protected void initDefaultCommand() {

    }
}
