package frc.robot.subsystems.manipulators.cargo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.cargo.enums.FlywheelPower;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class Flywheel extends Subsystem {
    private static Flywheel instance;

    private TalonSRX flyWheelLeft, flyWheelRight;

    private Flywheel() {
        flyWheelLeft = new TalonSRX(RobotMap.CARGO.INTAKE_LEFT);
        flyWheelRight = new TalonSRX(RobotMap.CARGO.INTAKE_RIGHT);

        flyWheelLeft.setNeutralMode(NeutralMode.Brake);
        flyWheelRight.setNeutralMode(NeutralMode.Brake);

        flyWheelLeft.setInverted(true);

        flyWheelRight.follow(flyWheelLeft);
    }

    public synchronized static Flywheel getInstance() {
        if (instance == null) {
            instance = new Flywheel();
        }
        return instance;
    }

    public void set(FlywheelPower power) {
        flyWheelLeft.set(ControlMode.PercentOutput, power.getPower());
    }

    @Override
    protected void initDefaultCommand() {
    }
}