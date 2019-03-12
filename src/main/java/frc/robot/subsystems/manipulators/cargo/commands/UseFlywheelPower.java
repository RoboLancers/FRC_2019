package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.enums.FlyWheelPower;
import frc.robot.subsystems.manipulators.cargo.FlyWheel;

public class UseFlywheelPower extends InstantCommand {
    private FlyWheelPower flyWheelPower;

    public UseFlywheelPower(FlyWheelPower flyWheelPower) {
        requires(FlyWheel.getInstance());
        this.flyWheelPower = flyWheelPower;
    }

    @Override
    protected void initialize() {
        FlyWheel.getInstance().set(flyWheelPower);
    }
}
