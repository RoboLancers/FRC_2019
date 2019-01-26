package frc.robot.subsystems.manipulators.hatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.hatch.UseHatchPivot;
import frc.robot.enums.hatch.HatchPivotState;

public class HatchPivot extends Subsystem {

    private TalonSRX pivotMotor;
    public static HatchPivot instance;

    public HatchPivot() {
        pivotMotor = new TalonSRX(RobotMap.PIVOT_MOTOR);
        pivotMotor.setNeutralMode(NeutralMode.Brake);
        pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    public void setPivotMotorUp() {
        pivotMotor.set(ControlMode.PercentOutput, HatchPivotState.UP.getPosition());
    }

    public void setPivotMotorDown() {
        pivotMotor.set(ControlMode.PercentOutput, HatchPivotState.DOWN.getPosition());
    }

    public void setPivotMotorIn() {
        pivotMotor.set(ControlMode.PercentOutput, HatchPivotState.IN.getPosition());
    }

    public void set(HatchPivotState hatchPivotState) {
        pivotMotor.set(ControlMode.Position, hatchPivotState.getPosition());
    }

    public void stop() {
        pivotMotor.set(ControlMode.PercentOutput, 0);
    }

    public synchronized static HatchPivot getInstance() {
        if (instance == null) {
            instance = new HatchPivot();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseHatchPivot(HatchPivotState.DOWN));

    }
}
