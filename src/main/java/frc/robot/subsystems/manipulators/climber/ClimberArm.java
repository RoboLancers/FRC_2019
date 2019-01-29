package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.climber.ClimberState;

public class ClimberArm extends Subsystem {
    private static ClimberArm instance;
    private TalonSRX climberArm;

    private ClimberArm() {
        climberArm = new TalonSRX(RobotMap.CLIMBER.ARM);
        climberArm.setNeutralMode(NeutralMode.Brake);
        climberArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    public synchronized static ClimberArm getInstance() {
        if (instance == null) {
            instance = new ClimberArm();
        }
        return instance;
    }

    public void set(ClimberState climberState) {
        climberArm.set(ControlMode.Position, climberState.getPosition());
    }

    public double getPosition() {
        return climberArm.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
    }
}