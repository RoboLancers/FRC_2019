package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.enums.FlywheelPower;
import frc.robot.subsystems.manipulators.cargo.Flywheel;

public class UseFlywheelPower extends InstantCommand {
    private FlywheelPower flyWheelPower;

    public UseFlywheelPower(FlywheelPower flyWheelPower) {
        requires(Flywheel.getInstance());
        this.flyWheelPower = flyWheelPower;
    }

    @Override
    protected void initialize() {
        Flywheel.getInstance().set(flyWheelPower);
    }
}
