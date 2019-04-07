package frc.robot.subsystems.manipulators.cargo.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.cargo.Flywheel;
import frc.robot.subsystems.manipulators.cargo.enums.FlywheelPower;

public class UseFlywheel extends InstantCommand {
    private FlywheelPower flywheelPower;

    public UseFlywheel(FlywheelPower flywheelPower) {
        requires(Flywheel.getInstance());
        this.flywheelPower = flywheelPower;
    }

    @Override
    protected void initialize() {
        Flywheel.getInstance().set(flywheelPower);
    }
}
