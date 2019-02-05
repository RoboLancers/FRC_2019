package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.cargo.CargoAdjustment;
import frc.robot.enums.cargo.CargoPivotState;

@SuppressWarnings("unused")
public class CargoPivot extends Subsystem {
    private static CargoPivot instance;
    private DoubleSolenoid cargoPivot;

    private CargoPivot() {
        cargoPivot = new DoubleSolenoid(RobotMap.CARGO.PIVOT_UP, RobotMap.CARGO.PIVOT_DOWN);
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
        return cargoPivot.get() == CargoPivotState.UP.getValue() ? CargoPivotState.UP : CargoPivotState.DOWN;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CargoAdjustment());
    }
}