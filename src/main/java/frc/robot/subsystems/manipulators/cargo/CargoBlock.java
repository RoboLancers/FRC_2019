/*
package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.cargo.enums.CargoBlockState;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class CargoBlock extends Subsystem{
    private static CargoBlock instance;

    private DoubleSolenoid cargoBlock;

    private CargoBlockState state;

    private CargoBlock() {
        cargoBlock = new DoubleSolenoid(RobotMap.CARGO.BLOCK_UP, RobotMap.CARGO.BLOCK_DOWN);
        state = CargoBlockState.NEUTRAL;
    }

    public synchronized static CargoBlock getInstance() {
        if (instance == null) {
            instance = new CargoBlock();
        }
        return instance;
    }

    public void set(CargoBlockState cargoBlockState) {
        cargoBlock.set(cargoBlockState.getValue());
        state = cargoBlockState;
    }

    public CargoBlockState get() {
        return state;
    }

    @Override
    protected void initDefaultCommand() { }

    public String configureLogName(){
        return "Cargo";
    }
}*/
