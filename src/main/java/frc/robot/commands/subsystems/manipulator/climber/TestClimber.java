package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.climber.ClimberArm;

public class TestClimber extends InstantCommand {
    double power;

    public TestClimber(double power) {
        requires(ClimberArm.getInstance());
        this.power = power;
    }

    protected void execute() {
        ClimberArm.getInstance().set(power);
    }

}
