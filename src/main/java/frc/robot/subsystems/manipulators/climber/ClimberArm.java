package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.enums.climber.ClimberState;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

public class ClimberArm extends Subsystem {
    private static ClimberArm instance;
    private FalconSRX<Rotation2d> climberArm;
    private AnalogInput armLimitSwitch;

    private ClimberArm() {
        climberArm = new FalconSRX<>(RobotMap.CLIMBER.ARM, Constants.CLIMBER.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        climberArm.setNeutralMode(NeutralMode.Brake);
        climberArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        climberArm.setKF(Constants.CLIMBER.ARM_kF);
        climberArm.setKP(Constants.CLIMBER.ARM_kP);
        climberArm.setKI(Constants.CLIMBER.ARM_kI);
        climberArm.setKD(Constants.CLIMBER.ARM_kD);

        climberArm.configAllowableClosedloopError(Constants.CLIMBER.PID_SLOT_INDEX, Constants.CLIMBER.ALLOWABLE_ARM_ERROR);

        armLimitSwitch = new AnalogInput(RobotMap.CLIMBER.ARM_LIMIT_SWITCH);
    }

    public synchronized static ClimberArm getInstance() {
        if (instance == null) {
            instance = new ClimberArm();
        }
        return instance;
    }

    public void set(ClimberState climberState) {
        climberArm.set(ControlMode.Position, climberState.getRotation2d(), DemandType.ArbitraryFeedForward, -Math.sin(getAngle()) * Constants.CLIMBER.MINIMUM_PERCENT_OUT);
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

    public double getClosedLoopError() {
        return climberArm.getClosedLoopError();
    }

    public void resetEncoders(){
        climberArm.setSensorPosition(Rotation2dKt.getDegree(0));
    }

    public double getVoltage(){
        return armLimitSwitch.getVoltage();
    }

    @Override
    protected void initDefaultCommand() { }
}