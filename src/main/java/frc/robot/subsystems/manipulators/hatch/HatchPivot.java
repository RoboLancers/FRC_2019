package frc.robot.subsystems.manipulators.hatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.hatch.HatchPivotState;

public class HatchPivot extends Subsystem {
    private static HatchPivot instance;
    private TalonSRX pivotMotor;

    private HatchPivot() {
        pivotMotor = new TalonSRX(RobotMap.HATCH.PIVOT);

        pivotMotor.setNeutralMode(NeutralMode.Brake);
        pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    public synchronized static HatchPivot getInstance() {
        if (instance == null) {
            instance = new HatchPivot();
        }
        return instance;
    }

    public void set(HatchPivotState hatchPivotState) {
        pivotMotor.set(ControlMode.Position, hatchPivotState.getPosition());
    }

    @Override
    protected void initDefaultCommand() {
    }
}
