package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.enums.CargoBlockState;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;

public class UseCargoBlock extends InstantCommand {
    private CargoBlockState cargoBlockState;

    public UseCargoBlock(CargoBlockState cargoBlockState) {
        requires(CargoBlock.getInstance());
        this.cargoBlockState = cargoBlockState;
    }

    @Override
    protected void initialize() {
        CargoBlock.getInstance().set(cargoBlockState);
    }
}
