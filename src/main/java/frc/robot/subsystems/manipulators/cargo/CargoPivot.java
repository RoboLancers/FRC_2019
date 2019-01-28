package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.cargo.CargoPivotState;


public class CargoPivot extends Subsystem {
    private static CargoPivot instance;
    private DoubleSolenoid cargoPivot;

    private CargoPivot() {
        cargoPivot = new DoubleSolenoid(RobotMap.CARGO.PIVOT_FORWARD, RobotMap.CARGO.PIVOT_REVERSE);
    }

    public synchronized static CargoPivot getInstance() {
        if (instance == null) {
            instance = new CargoPivot();
        }
        return instance;
    }

    public void set(CargoPivotState cargoPivotState) {
        cargoPivot.set(cargoPivotState.getValue());
    }

    public CargoPivotState get() {
        if (cargoPivot.get() == CargoPivotState.UP.getValue()) {
            return CargoPivotState.UP;
        } else {
            return CargoPivotState.DOWN;
        }
    }

    @Override
    protected void initDefaultCommand() {
    }
}