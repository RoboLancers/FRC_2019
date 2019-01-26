package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.manipulator.cargo.UseCargoBlock;
import frc.robot.enums.cargo.CargoBlockState;

public class CargoBlock extends Subsystem {
    private DoubleSolenoid cargoBlock;
    private static CargoBlock instance;

    private CargoBlock() {
        cargoBlock = new DoubleSolenoid(RobotMap.CARGO_BLOCK_FORWARD, RobotMap.CARGO_BLOCK_REVERSE);
    }

    public void set(CargoBlockState cargoBlockState) {
        cargoBlock.set(cargoBlockState.getValue());
    }

    public CargoBlockState get() {
        if (cargoBlock.get() == CargoBlockState.UP.value) {
            return CargoBlockState.UP;
        } else {
            return CargoBlockState.DOWN;
        }
    }

    public synchronized static CargoBlock getInstance() {
        if (instance == null) {
            instance = new CargoBlock();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
    }
}
