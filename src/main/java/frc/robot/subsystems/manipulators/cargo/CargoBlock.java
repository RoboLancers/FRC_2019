package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.cargo.CargoBlockState;

@SuppressWarnings("unused")
public class CargoBlock extends Subsystem {
    private static CargoBlock instance;

    private DoubleSolenoid cargoBlock;

    private CargoBlock() {
        cargoBlock = new DoubleSolenoid(RobotMap.CARGO.BLOCK_UP, RobotMap.CARGO.BLOCK_DOWN);
    }

    public synchronized static CargoBlock getInstance() {
        if (instance == null) {
            instance = new CargoBlock();
        }
        return instance;
    }

    public void set(CargoBlockState cargoBlockState) {
        cargoBlock.set(cargoBlockState.getValue());
    }

    public CargoBlockState get() {
        return cargoBlock.get() == CargoBlockState.BLOCK.getValue() ? CargoBlockState.BLOCK : CargoBlockState.UNBLOCK;
    }

    @Override
    protected void initDefaultCommand() { }
}