package frc.robot.subsystems.manipulators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.robolancers.lib.Utilities;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UseHatchPivot;
import frc.robot.enums.HatchPivotState;

public class HatchPivot extends Subsystem {

    private TalonSRX pivotMotor;
    public static HatchPivot instance;

    public HatchPivot() {
        pivotMotor = new TalonSRX(RobotMap.PIVOT_MOTOR);
        pivotMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setPosition(int position) {
        pivotMotor.set(ControlMode.MotionMagic, position);
    }


    public void stop() {
        pivotMotor.set(ControlMode.PercentOutput, 0);
    }

    public synchronized static HatchPivot getInstance() {
        if(instance == null) {
            instance = new HatchPivot();
        }
        return instance;
    }
    @Override
    protected void initDefaultCommand() {

    }
}
