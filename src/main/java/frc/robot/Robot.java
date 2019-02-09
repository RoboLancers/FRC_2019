/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.robolancers.lib.subsystems.misc.Pneumatic;
import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.subsystems.drivetrain.RobotCharacterization;
import frc.robot.enums.cargo.CargoBlockState;
import frc.robot.enums.cargo.CargoPivotState;
import frc.robot.enums.climber.LiftoffState;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchPivot;
import frc.robot.subsystems.misc.LED;
import frc.robot.subsystems.misc.Sensors;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

public class Robot extends TimedRobot {
    @Override
    public void robotInit() {
        Drivetrain.getInstance();

        CargoBlock.getInstance();
        CargoPivot.getInstance();

        HatchEjector.getInstance();
        HatchPivot.getInstance();

        ClimberArm.getInstance();
        LiftoffPiston.getInstance();

        //Camera.getInstance();
        Sensors.getInstance();
        Pneumatic.getInstance();
        NetworkInterface.getInstance();
        LED.getInstance();

        //Trajectories.generateTrajectories();
    }

    @Override
    public void robotPeriodic() {
        NetworkInterface.getInstance().updateShuffleboard();
        Scheduler.getInstance().run();
        Shuffleboard.startRecording();
    }

    @Override
    public void autonomousInit() {
        Sensors.getInstance().resetNavX();

        Drivetrain.getInstance().resetEncoders();
        ClimberArm.getInstance().resetEncoders();
    }

    private NetworkTableEntry autoSpeedEntry = NetworkTableInstance.getDefault().getEntry("/robot/autospeed");
    private NetworkTableEntry telemetryEntry = NetworkTableInstance.getDefault().getEntry("/robot/telemetry");

    private Number[] numberArray = new Number[9];
    private double priorAutoSpeed;

    @Override
    public void autonomousPeriodic(){
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
    public void teleopInit() {
        ClimberArm.getInstance().resetEncoders();

        LiftoffPiston.getInstance().set(LiftoffState.UP);

        CargoPivot.getInstance().set(CargoPivotState.DOWN);
        CargoBlock.getInstance().set(CargoBlockState.BLOCK);

        LED.getInstance().setPattern(Blinkin.PatternType.WAVE_PARTY);
    }
}
