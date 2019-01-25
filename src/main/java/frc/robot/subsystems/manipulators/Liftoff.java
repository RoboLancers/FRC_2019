package frc.robot.subsystems.manipulators;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Elevate;
import frc.robot.enums.ClimberState;
import frc.robot.enums.LiftoffState;

public class Liftoff extends Subsystem {

    private DoubleSolenoid liftOff;
    private static Liftoff instance;
    public Liftoff() {
        liftOff = new DoubleSolenoid(RobotMap.LIFTOFF_FORWARD, RobotMap.LIFTOFF_REVERSE);
    }

    public void takeOff(){
        liftOff.set(DoubleSolenoid.Value.kForward);
    }

    public void land(){
        liftOff.set(DoubleSolenoid.Value.kReverse);
    }

    public synchronized static Liftoff getInstance(){
        if(instance == null){
            instance = new Liftoff();
        }
        return instance;
    }
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Elevate(LiftoffState.DOWN, ClimberState.DOWN));
    }
}
