package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMaxLowLevel;
import com.robolancers.lib.subsystems.drivetrain.TankDriveSubsystem;
import com.robolancers.lib.wrappers.motors.LancerSparkMax;
import com.team254.lib.physics.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.subsystems.drivetrain.UseDrivetrain;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.wrappers.FalconMotor;
import org.jetbrains.annotations.NotNull;

public class Drivetrain extends TankDriveSubsystem {
    public static Drivetrain instance;

    private LancerSparkMax<Length> masterRight, rightSlave1, rightSlave2;
    private LancerSparkMax<Length> masterLeft, leftSlave1, leftSlave2;

    public Drivetrain() {
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
        return masterLeft;
    }

    @NotNull
    @Override
    public FalconMotor<Length> getRightMotor() {
        return masterRight;
    }

    @NotNull
    @Override
    public TrajectoryTracker getTrajectoryTracker() {
        return null;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseDrivetrain());
    }

    public static synchronized Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }

        return instance;
    }
}
