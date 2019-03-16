package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.cargo.commands.CargoAdjustment;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;

@SuppressWarnings("unused")
public class CargoPivot extends Subsystem{
    private static CargoPivot instance;

    private DoubleSolenoid cargoPivot;

    private CargoPivotState state;

    private CargoPivot() {
        cargoPivot = new DoubleSolenoid(RobotMap.CARGO.PIVOT_UP, RobotMap.CARGO.PIVOT_DOWN);
        state = CargoPivotState.NEUTRAL;
    }

    public synchronized static CargoPivot getInstance() {
        if (instance == null) {
            instance = new CargoPivot();
        }
        return instance;
    }

    public void set(CargoPivotState cargoPivotState) {
        cargoPivot.set(cargoPivotState.getValue());
        state = cargoPivotState;
    }

    public CargoPivotState get() {
        return state;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CargoAdjustment());
    }
}