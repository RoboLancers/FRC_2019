package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.enums.cargo.CargoBlockState;

@SuppressWarnings("unused")
public class CargoBlock extends Subsystem {
    private static CargoBlock instance;
    private Solenoid cargoBlock;

    private CargoBlock() {
        cargoBlock = new Solenoid(RobotMap.CARGO.BLOCK_FORWARD, RobotMap.CARGO.BLOCK_REVERSE);
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
        if (cargoBlock.get() == CargoBlockState.UP.getValue()) {
            return CargoBlockState.UP;
        } else {
            return CargoBlockState.DOWN;
        }
    }

    @Override
    protected void initDefaultCommand() {
    }
}
