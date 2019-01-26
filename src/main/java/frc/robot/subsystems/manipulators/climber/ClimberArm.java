package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.climber.Elevate;
import frc.robot.enums.climber.ClimberState;
import frc.robot.enums.climber.LiftoffState;

public class ClimberArm extends Subsystem {

    private TalonSRX climberMotor;
    private static ClimberArm instance;

    public ClimberArm() {
        climberMotor = new TalonSRX(RobotMap.CLIMBER_MOTOR);

        climberMotor.setNeutralMode(NeutralMode.Brake);

        climberMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }

    public void set(ClimberState climberState) {
        climberMotor.set(ControlMode.Position, climberState.getPosition());
    }

    public void stop() {
        climberMotor.set(ControlMode.PercentOutput, 0);
    }

    public synchronized static ClimberArm getInstance() {
        if (instance == null) {
            instance = new ClimberArm();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
    }
}

//mantis arms and liftoff piston