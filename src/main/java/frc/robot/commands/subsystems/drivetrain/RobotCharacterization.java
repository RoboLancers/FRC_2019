package frc.robot.commands.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.subsystems.drivetrain.Drivetrain;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

public class RobotCharacterization extends Command {
    private NetworkTableEntry autoSpeedEntry = NetworkTableInstance.getDefault().getEntry("/robot/autospeed");
    private NetworkTableEntry telemetryEntry = NetworkTableInstance.getDefault().getEntry("/robot/telemetry");

    private Number[] numberArray = new Number[9];
    private double priorAutoSpeed;

    public RobotCharacterization(){
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        double now = Timer.getFPGATimestamp();

        double leftPosition = Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet();
        double leftVelocity = VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorVelocity());

        double rightPosition = Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet();
        double rightVelocity = VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorVelocity());

        double battery = RobotController.getBatteryVoltage();

        double leftMotorVolts = Drivetrain.getInstance().getLeftTransmission().getMaster().getMotorOutputVoltage();
        double rightMotorVolts = Drivetrain.getInstance().getRightTransmission().getMaster().getMotorOutputVoltage();

        double autospeed = autoSpeedEntry.getDouble(0);
        priorAutoSpeed = autospeed;

        Drivetrain.getInstance().getLeftTransmission().getMaster().set(ControlMode.PercentOutput, autospeed);
        Drivetrain.getInstance().getRightTransmission().getMaster().set(ControlMode.PercentOutput, autospeed);

        numberArray[0] = now;
        numberArray[1] = battery;
        numberArray[2] = autospeed;
        numberArray[3] = leftMotorVolts;
        numberArray[4] = rightMotorVolts;
        numberArray[5] = leftPosition;
        numberArray[6] = rightPosition;
        numberArray[7] = leftVelocity;
        numberArray[8] = rightVelocity;

        telemetryEntry.setNumberArray(numberArray);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
