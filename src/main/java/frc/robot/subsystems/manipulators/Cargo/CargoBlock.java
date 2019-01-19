package frc.robot.subsystems.manipulators.Cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UseCargoBlock;
import frc.robot.enums.CargoBlockState;


public class CargoBlock extends Subsystem {

    private DoubleSolenoid cargoBlock;
    private static CargoBlock instance;

    private CargoBlock() {
        cargoBlock = new DoubleSolenoid(RobotMap.CARGO_BLOCK_FORWARD, RobotMap.CARGO_BLOCK_REVERSE);
    }

    public void setUp() {
        cargoBlock.set(DoubleSolenoid.Value.kForward);
    }
    public void setDown() {
        cargoBlock.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isBlockUp(){
        return cargoBlock.get() == DoubleSolenoid.Value.kForward;
    }


    public synchronized static CargoBlock getInstance(){
        if(instance == null){
            instance = new CargoBlock();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseCargoBlock(CargoBlockState.UP));
    }
}
