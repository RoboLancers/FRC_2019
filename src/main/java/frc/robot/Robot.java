/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.Autonomous;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.manipulators.cargo.Flywheel;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;
import frc.robot.subsystems.manipulators.cargo.enums.FlywheelPower;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.LED;
import frc.robot.subsystems.misc.Sensors;

@SuppressWarnings("unused")
public class Robot extends TimedRobot {
    @Override
    public void robotInit() {
        Drivetrain.getInstance();

        Flywheel.getInstance();
        CargoPivot.getInstance();

        HatchHolder.getInstance();
        HatchEjector.getInstance();

        ClimberArm.getInstance();
        LiftoffPiston.getInstance();

        Camera.getInstance();
        Sensors.getInstance();
        LED.getInstance();

        Trajectories.generateTrajectories();

        Shuffleboard.setRecordingFileNameFormat(DriverStation.getInstance().getEventName() + " " + DriverStation.getInstance().getMatchType() + ":" + DriverStation.getInstance().getMatchNumber() + "-{date}");
        LiveWindow.disableAllTelemetry();
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("Turn Modifier", UseDrivetrain.turnModifier);
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledPeriodic(){
        Autonomous.getInstance().update();
    }

    @Override
    public void autonomousInit() {
        Sensors.getInstance().resetHeading();

        Drivetrain.getInstance().resetEncoders();
        ClimberArm.getInstance().resetEncoders();

        HatchEjector.getInstance().set(HatchEjectorState.RETRACT);
        HatchHolder.getInstance().set(HatchHolderState.HOLD);

        if(Autonomous.getInstance().getAutonomousCommand() != null){
            Autonomous.getInstance().getAutonomousCommand().start();
        }

    }

    @Override
    public void autonomousPeriodic(){
        if(OI.flightController.getState(FlightController.Button.INNER_MIDDLE)){
            Autonomous.getInstance().getAutonomousCommand().cancel();
        }
    }

    @Override
    public void teleopInit() {
        if(Autonomous.getInstance().getAutonomousCommand() != null){
            Autonomous.getInstance().getAutonomousCommand().cancel();
        }

        LiftoffPiston.getInstance().set(LiftoffState.UP);

        CargoPivot.getInstance().set(CargoPivotState.DOWN);

        HatchEjector.getInstance().set(HatchEjectorState.RETRACT);
        HatchHolder.getInstance().set(HatchHolderState.HOLD);
    }
}
