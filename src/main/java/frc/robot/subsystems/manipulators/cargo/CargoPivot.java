package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoPivot;
import frc.robot.enums.cargo.CargoPivotState;


public class CargoPivot extends Subsystem {

    private DoubleSolenoid cargoPivot;
    private static CargoPivot instance;

    private CargoPivot() {
        cargoPivot = new DoubleSolenoid(RobotMap.CARGO_PIVOT_FORWARD, RobotMap.CARGO_PIVOT_REVERSE);
    }

    public void set(CargoPivotState cargoPivotState) {
        cargoPivot.set(cargoPivotState.getValue());
    }

    public CargoPivotState get() {
        if (cargoPivot.get() == CargoPivotState.UP.value) {
            return CargoPivotState.UP;
        } else {
            return CargoPivotState.DOWN;
        }
    }

    public synchronized static CargoPivot getInstance() {
        if (instance == null) {
            instance = new CargoPivot();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
    }
}