package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;

public class UseLiftoffPiston extends InstantCommand {
    private LiftoffState liftoffState;

    public UseLiftoffPiston(LiftoffState liftoffState) {
        requires(LiftoffPiston.getInstance());
        this.liftoffState = liftoffState;
    }

    @Override
    public void initialize() {
        LiftoffPiston.getInstance().set(liftoffState);
    }
}
