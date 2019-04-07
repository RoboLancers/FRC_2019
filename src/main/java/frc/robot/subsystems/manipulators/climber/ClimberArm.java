package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.climber.commands.UseClimberArmJoystick;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

@SuppressWarnings("unused")
public class ClimberArm extends Subsystem {
    private static ClimberArm instance;

    private FalconSRX<Rotation2d> climberArm;

    private ClimberArm() {
        climberArm = new FalconSRX<>(RobotMap.CLIMBER.ARM, Constants.CLIMBER.NATIVE_UNIT_MODEL, Constants.TIMEOUT);
        climberArm.setNeutralMode(NeutralMode.Brake);
        climberArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        climberArm.setSensorPhase(true);
        climberArm.setInverted(true);

        climberArm.setKF(Constants.CLIMBER.ARM_kF);
        climberArm.setKP(Constants.CLIMBER.ARM_kP);
        climberArm.setKI(Constants.CLIMBER.ARM_kI);
        climberArm.setKD(Constants.CLIMBER.ARM_kD);

        climberArm.setOpenLoopRamp(TimeUnitsKt.getSecond(Constants.CLIMBER.RAMP_RATE));

        climberArm.enableCurrentLimit(false);

        climberArm.configAllowableClosedloopError(Constants.CLIMBER.PID_SLOT_INDEX, Constants.CLIMBER.ALLOWABLE_ARM_ERROR);
    }

    public synchronized static ClimberArm getInstance() {
        if (instance == null) {
            instance = new ClimberArm();
        }
        return instance;
    }

    public void set(ClimberState climberState) {
        climberArm.set(ControlMode.Position, climberState.getRotation2d());
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

    public void resetEncoders() {
        climberArm.setSensorPosition(Rotation2dKt.getDegree(0));
    }

    public FalconSRX getMaster() {
        return climberArm;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseClimberArmJoystick());
    }
}