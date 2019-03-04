package frc.robot.subsystems.manipulators.cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.manipulators.cargo.enums.CargoBlockState;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

@SuppressWarnings("unused")
public class CargoBlock extends Subsystem implements Loggable {
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

    @Log.ToString(name = "Cargo Block State", rowIndex = 0, columnIndex = 0, width = 2, height = 1)
    public CargoBlockState get() {
        return cargoBlock.get() == CargoBlockState.BLOCK.getValue() ? CargoBlockState.BLOCK : CargoBlockState.UNBLOCK;
    }

    @Override
    protected void initDefaultCommand() { }

    @Override
    public String configureLogName(){
        return "Cargo";
    }
}