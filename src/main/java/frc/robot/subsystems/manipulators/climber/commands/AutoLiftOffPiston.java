package frc.robot.subsystems.manipulators.climber.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Sensors;

@SuppressWarnings("WeakerAccess")
public class AutoLiftOffPiston extends Command {
    public AutoLiftOffPiston() {
        requires(LiftoffPiston.getInstance());
    }

    @Override
    public void initialize() {
        LiftoffPiston.getInstance().set(LiftoffState.DOWN);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Sensors.getInstance().getRoll()) < 5;
    }
}
