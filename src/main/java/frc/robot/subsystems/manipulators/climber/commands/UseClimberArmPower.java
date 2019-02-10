package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class UseClimberArmPower extends InstantCommand {
    private double power;

    public UseClimberArmPower(double power) {
        requires(ClimberArm.getInstance());
        this.power = power;
    }

    protected void execute() {
        ClimberArm.getInstance().set(power);
    }
}
