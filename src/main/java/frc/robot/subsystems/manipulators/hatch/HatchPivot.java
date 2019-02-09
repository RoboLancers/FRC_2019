package frc.robot.subsystems.manipulators.hatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchPivotState;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

public class HatchPivot extends Subsystem {
    private static HatchPivot instance;

    private FalconSRX<Rotation2d> pivotMotor;

    private HatchPivot() {
        pivotMotor = new FalconSRX<>(RobotMap.HATCH.PIVOT, Constants.HATCH.NATIVE_UNIT_MODEL, Constants.TIMEOUT);

        pivotMotor.setNeutralMode(NeutralMode.Brake);
        pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        pivotMotor.setKF(Constants.HATCH.PIVOT_kF);
        pivotMotor.setKP(Constants.HATCH.PIVOT_kP);
        pivotMotor.setKI(Constants.HATCH.PIVOT_kI);
        pivotMotor.setKD(Constants.HATCH.PIVOT_kD);
    }

    public synchronized static HatchPivot getInstance() {
        if (instance == null) {
            instance = new HatchPivot();
        }
        return instance;
    }

    public void set(HatchPivotState hatchPivotState) {
        set(ControlMode.Position, hatchPivotState.getPosition());
    }

    public void set(ControlMode mode, double value){
        pivotMotor.set(mode, value);
    }

    public FalconSRX getMaster(){
        return pivotMotor;
    }

    @Override
    protected void initDefaultCommand() { }
}
