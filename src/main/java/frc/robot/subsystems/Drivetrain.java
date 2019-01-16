package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel;
import com.robolancers.lib.subsystem.drivetrain.TankDriveSubsystem;
import com.robolancers.lib.wrappers.motors.LancerSparkMax;
import com.team254.lib.physics.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.wrappers.FalconMotor;
import org.jetbrains.annotations.NotNull;

public class Drivetrain extends TankDriveSubsystem {
    private LancerSparkMax<Length> masterRight,  rightSlave1, rightSlave2;
    private LancerSparkMax<Length> masterLeft, leftSlave1, leftSlave2;

    public Drivetrain(){
       masterRight = new LancerSparkMax<>(RobotMap.MASTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);
       rightSlave1 = new LancerSparkMax<>(RobotMap.RIGHT_SLAVE_1, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);
       rightSlave2 = new LancerSparkMax<>(RobotMap.RIGHT_SLAVE_2, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);

       masterLeft = new LancerSparkMax<>(RobotMap.MASTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);
       leftSlave1 = new LancerSparkMax<>(RobotMap.LEFT_SLAVE_1, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);
       leftSlave2 = new LancerSparkMax<>(RobotMap.LEFT_SLAVE_2, CANSparkMaxLowLevel.MotorType.kBrushless, Constants.nativeUnitModel);
    }

    @Override
    public Localization getLocalization() {
        return null;
    }

    @NotNull
    @Override
    public DifferentialDrive getDifferentialDrive() {
        return null;
    }


    @NotNull
    @Override
    public FalconMotor<Length> getLeftMotor() {
        return null;
    }

    @NotNull
    @Override
    public FalconMotor<Length> getRightMotor() {
        return null;
    }

    @NotNull
    @Override
    public TrajectoryTracker getTrajectoryTracker() {
        return null;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
