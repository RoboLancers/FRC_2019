package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.CargoBlockState;
import frc.robot.subsystems.manipulators.Cargo.CargoBlock;

public class UseCargoBlock extends InstantCommand {

    private CargoBlockState cargoBlockState;

    public UseCargoBlock(CargoBlockState cargoBlockState){
        requires(CargoBlock.getInstance());
        this.cargoBlockState =  cargoBlockState;
    }

    @Override
    protected void initialize(){
        if(cargoBlockState == CargoBlockState.UP){
            CargoBlock.getInstance().setUp();
        }else{
            CargoBlock.getInstance().setDown();
        }
    }
}
