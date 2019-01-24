package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.CargoPivotState;
import frc.robot.subsystems.manipulators.Cargo.CargoPivot;

public class UseCargoPivot extends InstantCommand {
    private CargoPivotState cargoPivotState;

    public UseCargoPivot(CargoPivotState cargoPivotState){
        requires(CargoPivot.getInstance());
        this.cargoPivotState =  cargoPivotState;
    }

    @Override
    protected void initialize(){
        if(cargoPivotState == CargoPivotState.UP){
            CargoPivot.getInstance().setUp();
        }else{
            CargoPivot.getInstance().setDown();
        }
    }

}
