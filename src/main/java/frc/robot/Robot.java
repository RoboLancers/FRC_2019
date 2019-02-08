/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.robolancers.lib.subsystems.misc.Pneumatic;
import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.autonomous.Trajectories;
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
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.LEDs;
import frc.robot.subsystems.misc.Sensors;

public class Robot extends TimedRobot {
    @Override
    public void robotInit() {
        Drivetrain.getInstance();

        CargoBlock.getInstance();
        CargoPivot.getInstance();

        //HatchEjector.getInstance();
        //HatchPivot.getInstance();

        ClimberArm.getInstance();
        LiftoffPiston.getInstance();

        Camera.getInstance();
        Sensors.getInstance();
        Pneumatic.getInstance();
        NetworkInterface.getInstance();

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

        new RobotCharacterization().start();
    }

    @Override
    public void teleopInit() {
        ClimberArm.getInstance().resetEncoders();

        LiftoffPiston.getInstance().set(LiftoffState.UP);

        CargoPivot.getInstance().set(CargoPivotState.DOWN);
        CargoBlock.getInstance().set(CargoBlockState.BLOCK);
    }
}
