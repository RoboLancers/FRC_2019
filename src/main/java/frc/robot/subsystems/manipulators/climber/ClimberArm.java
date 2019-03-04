package frc.robot.subsystems.manipulators.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.climber.commands.UseClimberArmJoystick;
import frc.robot.subsystems.manipulators.climber.commands.UseClimberArmPower;
import frc.robot.subsystems.manipulators.climber.enums.ClimberState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;
import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;

@SuppressWarnings({"DefaultAnnotationParam", "unused"})
public class ClimberArm extends Subsystem implements Loggable {
    private static ClimberArm instance;

    private FalconSRX<Rotation2d> climberArm;
    private AnalogInput armLimitSwitch;

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
        climberArm.set(ControlMode.Position, climberState.getRotation2d());
    }

    public void set(double power) {
        climberArm.set(ControlMode.PercentOutput, power);
    }

    @Config(name = "Arm P", defaultValueNumeric = Constants.CLIMBER.ARM_kP, rowIndex = 1, columnIndex = 0, width = 2, height = 1)
    public void setP(double p){
        climberArm.setKP(p);
    }

    @Config(name = "Arm I", defaultValueNumeric = Constants.CLIMBER.ARM_kI, rowIndex = 1, columnIndex = 2, width = 2, height = 1)
    public void setI(double i){
        climberArm.setKI(i);
    }

    @Config(name = "Arm D", defaultValueNumeric = Constants.CLIMBER.ARM_kD, rowIndex = 1, columnIndex = 4, width = 2, height = 1)
    public void setD(double d){
        climberArm.setKD(d);
    }

    @Config(name = "Arm F", defaultValueNumeric = Constants.CLIMBER.ARM_kF, rowIndex = 1, columnIndex = 6, width = 2, height = 1)
    public void setF(double f){
        climberArm.setKF(f);
    }

    @Log(name = "Arm Position", rowIndex = 0, columnIndex = 0, width = 2, height = 1)
    public double getPosition() {
        return climberArm.getSelectedSensorPosition();
    }

    @Log(name = "Arm Angle", rowIndex = 0, columnIndex = 2, width = 2, height = 1)
    public double getAngle() {
        return climberArm.getSensorPosition().getDegree();
    }

    @Log(name = "Arm Closed Loop Error", rowIndex = 0, columnIndex = 4, width = 2, height = 1)
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
    protected void initDefaultCommand() {
        setDefaultCommand(new UseClimberArmJoystick());
    }

    @Override
    public String configureLogName(){
        return "Climber";
    }
}