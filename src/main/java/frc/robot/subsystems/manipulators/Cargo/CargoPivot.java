package frc.robot.subsystems.manipulators.Cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UseCargoPivot;
import frc.robot.enums.CargoPivotState;


public class CargoPivot extends Subsystem {

    private DoubleSolenoid cargoPivot;
    private static CargoPivot instance;

    private CargoPivot() {
        cargoPivot = new DoubleSolenoid(RobotMap.CARGO_PIVOT_FORWARD, RobotMap.CARGO_PIVOT_REVERSE);
    }

    public void setUp() {
        cargoPivot.set(DoubleSolenoid.Value.kForward);
    }
    public void setDown() {
        cargoPivot.set(DoubleSolenoid.Value.kReverse);
    }


    public boolean isPivotUp(){
        return cargoPivot.get() == DoubleSolenoid.Value.kForward;
    }

    public synchronized static CargoPivot getInstance(){
        if(instance == null){
            instance = new CargoPivot();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseCargoPivot(CargoPivotState.UP));
    }
}