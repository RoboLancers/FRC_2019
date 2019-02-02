/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.robolancers.lib.subsystems.misc.Pneumatic;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.autonomous.Trajectories;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchPivot;
import frc.robot.subsystems.misc.Sensors;

public class Robot extends TimedRobot {
    @Override
    public void robotInit() {
        Drivetrain.getInstance();

        //CargoBlock.getInstance();
        //CargoPivot.getInstance();

        //HatchEjector.getInstance();
        //HatchPivot.getInstance();

        ClimberArm.getInstance();
        //LiftoffPiston.getInstance();

        Sensors.getInstance();
        //Pneumatic.getInstance();
        NetworkInterface.getInstance();

        //Trajectories.generateTrajectories();
    }

    @Override
    public void robotPeriodic() {
        NetworkInterface.getInstance().updateShuffleboard();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        Sensors.getInstance().resetNavX();

        Drivetrain.getInstance().resetEncoders();
        ClimberArm.getInstance().resetEncoders();
    }

    @Override
    public void teleopInit() { }
}
