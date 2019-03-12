package frc.robot.subsystems.manipulators.cargo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.manipulators.cargo.enums.FlyWheelPower;
import io.github.oblarg.oblog.Loggable;

@SuppressWarnings("unused")
public class FlyWheel extends Subsystem implements Loggable {
    private static FlyWheel instance;

    private TalonSRX flyWheelLeft, flyWheelRight;

    private FlyWheel() {
        flyWheelLeft = new TalonSRX(20);
        flyWheelRight = new TalonSRX(21);

        flyWheelRight.follow(flyWheelLeft);
    }

    public synchronized static FlyWheel getInstance() {
        if (instance == null) {
            instance = new FlyWheel();
        }
        return instance;
    }

    public void set(FlyWheelPower power){
        flyWheelLeft.set(ControlMode.PercentOutput, power.getPower());
    }

    @Override
    protected void initDefaultCommand() { }

    @Override
    public String configureLogName(){
        return "Cargo";
    }
}