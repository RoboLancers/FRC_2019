package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.enums.climber.ClimberState;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

public class ClimberArm extends Subsystem {
    private static ClimberArm instance;
    private FalconSRX<Rotation2d> climberArm;

    private ClimberArm() {
        climberArm = new FalconSRX<>(RobotMap.CLIMBER.ARM, Constants.CLIMBER.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        climberArm.setNeutralMode(NeutralMode.Brake);
        climberArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        climberArm.setKP(0.1);
    }

    public synchronized static ClimberArm getInstance() {
        if (instance == null) {
            instance = new ClimberArm();
        }
        return instance;
    }

    public void set(ClimberState climberState) {
        climberArm.set(ControlMode.Position, climberState.getPosition(), DemandType.ArbitraryFeedForward, Math.sin(getAngle()) * Constants.CLIMBER.MINIMUM_PERCENT_OUT);
    }

    public void set(double power) {
        climberArm.set(ControlMode.PercentOutput, power);
    }

    public double getPosition() {
        return climberArm.getSelectedSensorPosition();
    }

    public double getAngle() {
        return climberArm.getSensorPosition().getDegree();
    }

    public void resetEncoders(){
        climberArm.setSelectedSensorPosition(0);
    }

    @Override
    protected void initDefaultCommand() {
    }
}