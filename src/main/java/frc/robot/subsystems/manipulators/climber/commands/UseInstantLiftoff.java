package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;

public class UseInstantLiftoff extends InstantCommand {
    private LiftoffState liftoffState;

    public UseInstantLiftoff(LiftoffState liftoffState) {
        requires(LiftoffPiston.getInstance());
        this.liftoffState = liftoffState;
    }

    @Override
    public void initialize() {
        LiftoffPiston.getInstance().set(liftoffState);
    }
}
