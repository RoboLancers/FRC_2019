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
import frc.robot.autonomous.Autonomous;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.manipulators.cargo.enums.CargoBlockState;
import frc.robot.subsystems.manipulators.cargo.enums.CargoPivotState;
import frc.robot.subsystems.manipulators.climber.enums.LiftoffState;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchHolder;
import frc.robot.subsystems.manipulators.hatch.enums.HatchEjectorState;
import frc.robot.subsystems.manipulators.hatch.enums.HatchHolderState;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.LED;
import frc.robot.subsystems.misc.Localization;
import frc.robot.subsystems.misc.Sensors;
import io.github.oblarg.oblog.Logger;
import org.ghrobotics.lib.debug.LiveDashboard;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;

@SuppressWarnings("unused")
public class Robot extends TimedRobot {
    private Drivetrain drivetrain = Drivetrain.getInstance();

    private CargoBlock cargoBlock = CargoBlock.getInstance();
    private CargoPivot cargoPivot = CargoPivot.getInstance();

    private HatchHolder hatchHolder = HatchHolder.getInstance();
    private HatchEjector hatchEjector = HatchEjector.getInstance();

    private ClimberArm climberArm = ClimberArm.getInstance();
    private LiftoffPiston liftoffPiston = LiftoffPiston.getInstance();

    private Camera camera = Camera.getInstance();
    private Localization localization = Localization.getInstance();
    private Sensors sensors = Sensors.getInstance();
    private LED led = LED.getInstance();

    @Override
    public void robotInit() {
        Shuffleboard.startRecording();
        Trajectories.generateTrajectories();

        Shuffleboard.setRecordingFileNameFormat(DriverStation.getInstance().getEventName() + " " + DriverStation.getInstance().getMatchType() + ":" + DriverStation.getInstance().getMatchNumber() + "-{date}");

        Logger.configureLoggingAndConfig(this, false);
        LiveWindow.disableAllTelemetry();
    }

    @Override
    public void robotPeriodic() {
        Camera.getInstance().getLancerPixy().update();
        Logger.updateEntries();
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

        Drivetrain.getInstance().getLocalization().reset(new Pose2d(LengthKt.getFeet(LiveDashboard.INSTANCE.getPathX()), LengthKt.getFeet(LiveDashboard.INSTANCE.getPathY()), Rotation2dKt.getRadian(LiveDashboard.INSTANCE.getPathHeading())));
    }

    @Override
    public void teleopInit() {
        if(Autonomous.getInstance().getAutonomousCommand() != null){
            Autonomous.getInstance().getAutonomousCommand().cancel();
        }

        LiftoffPiston.getInstance().set(LiftoffState.UP);

        CargoPivot.getInstance().set(CargoPivotState.UP);
        CargoBlock.getInstance().set(CargoBlockState.BLOCK);

        HatchEjector.getInstance().set(HatchEjectorState.RETRACT);
        HatchHolder.getInstance().set(HatchHolderState.HOLD);
    }
}
