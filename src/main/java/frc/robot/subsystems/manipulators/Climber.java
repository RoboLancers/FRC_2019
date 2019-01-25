package frc.robot.subsystems.manipulators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.robolancers.lib.Utilities;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Elevate;
import frc.robot.enums.LiftoffState;

public class Climber extends Subsystem {

    private TalonSRX climberMotor;
    private static Climber instance;

    public Climber() {
        climberMotor = new TalonSRX(RobotMap.CLIMBER_MOTOR);

        climberMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setClimberMotorUp(double power) {
        climberMotor.set(ControlMode.PercentOutput, Utilities.range(power, 1));
    }

    public void setClimberMotorDown(double power) {
        climberMotor.set(ControlMode.PercentOutput, Utilities.range(power, -10));
    }

    public void stop(){
        climberMotor.set(ControlMode.PercentOutput, 0);
    }

    public synchronized static Climber getInstance() {
        if (getInstance() == null) {
            instance = new Climber();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Elevate(LiftoffState.DOWN));
    }
}

//mantis arms and liftoff piston