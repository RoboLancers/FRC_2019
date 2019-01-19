package frc.robot.subsystems.manipulators;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Cargo extends Subsystem {

    private DoubleSolenoid block;
    private static Cargo instance;

    private Cargo(){

    }

    public synchronized static Cargo getInstance(){
        if(instance == null){
            instance = new Cargo();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
