package frc.robot.commands.autonomous;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class RobotCharacterization extends Command {

    public RobotCharacterization(){
        requires(Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        NetworkTableEntry autoSpeedEntry = NetworkTableInstance.getDefault().getEntry("/robot/autospeed");
        NetworkTableEntry telemetryEntry = NetworkTableInstance.getDefault().getEntry("/robot/telemetry");

        Number[] numberArray = new Number[9];
        double priorAutoSpeed;

        double now = Timer.getFPGATimestamp();

        double leftPosition = Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet();
        // I am not sure if .getValue() will return feet
        double leftVelocity = Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorVelocity().getValue();

        double rightPosition = Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet();
        double rightVelocity = Drivetrain.getInstance().getRightTransmission().getMaster().getSensorVelocity().getValue();

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
