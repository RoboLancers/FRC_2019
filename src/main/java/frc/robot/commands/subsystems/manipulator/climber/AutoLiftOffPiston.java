package frc.robot.commands.subsystems.manipulator.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Sensors;

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
