package frc.robot.autonomous.routines;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

public class TestVelocity extends Command {
    public TestVelocity(){
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void initialize(){
        Drivetrain.getInstance().getLeftTransmission().getMaster().setVelocity(VelocityKt.getVelocity(LengthKt.getFeet(5.0)));
        Drivetrain.getInstance().getRightTransmission().getMaster().setVelocity(VelocityKt.getVelocity(LengthKt.getFeet(5.0)));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
