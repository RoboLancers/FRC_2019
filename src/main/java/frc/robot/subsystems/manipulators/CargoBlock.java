package frc.robot.subsystems.manipulators;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoBlock extends Subsystem {

    private DoubleSolenoid block;
    private static CargoBlock instance;

    private CargoBlock(){
        shooterLeft = new TalonSRX(RobotMap.SHOOTER_LEFT);
        shooterRight = new TalonSRX(RobotMap.SHOOTER_RIGHT);

        shooterRight.setNeutralMode(NeutralMode.Brake);
        shooterLeft.setNeutralMode(NeutralMode.Brake);
    }

    public synchronized static CargoBlock getInstance(){
        if(instance == null){
            instance = new CargoBlock();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
//trapdoor and instance