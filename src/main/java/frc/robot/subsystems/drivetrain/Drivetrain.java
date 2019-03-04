package frc.robot.subsystems.drivetrain;

import com.robolancers.lib.auto.LancerPID;
import com.robolancers.lib.subsystems.drivetrain.TankDriveSubsystem;
import com.team254.lib.physics.DifferentialDrive;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.drivetrain.enums.TransmissionSide;
import frc.robot.subsystems.misc.Sensors;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;
import org.ghrobotics.lib.debug.LiveDashboard;
import org.ghrobotics.lib.localization.Localization;
import org.ghrobotics.lib.localization.TankEncoderLocalization;
import org.ghrobotics.lib.mathematics.twodim.control.RamseteTracker;
import org.ghrobotics.lib.mathematics.twodim.control.TrajectoryTracker;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;
import org.ghrobotics.lib.wrappers.FalconMotor;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Drivetrain extends TankDriveSubsystem implements Loggable {
    private static Drivetrain instance;

    private Transmission left, right;

    private Localization localization;

    private TrajectoryTracker trajectoryTracker;

    private LancerPID turningPID;

    private Drivetrain() {
        left = new Transmission(TransmissionSide.LEFT, RobotMap.DRIVETRAIN.MASTER_LEFT, RobotMap.DRIVETRAIN.LEFT_SLAVE_1, RobotMap.DRIVETRAIN.LEFT_SLAVE_2);
        right = new Transmission(TransmissionSide.RIGHT, RobotMap.DRIVETRAIN.MASTER_RIGHT, RobotMap.DRIVETRAIN.RIGHT_SLAVE_1, RobotMap.DRIVETRAIN.RIGHT_SLAVE_2);

        localization = new TankEncoderLocalization(
                () -> Rotation2dKt.getDegree(Sensors.getInstance().getFusedHeading()),
                () -> left.getMaster().getSensorPosition(),
                () -> right.getMaster().getSensorPosition()
        );

        new Notifier(() -> {
            localization.update();
            LiveDashboard.INSTANCE.setRobotX(localization.getRobotPosition().getTranslation().getX().getFeet());
            LiveDashboard.INSTANCE.setRobotY(localization.getRobotPosition().getTranslation().getY().getFeet());
            LiveDashboard.INSTANCE.setRobotHeading(localization.getRobotPosition().getRotation().getRadian());
        }).startPeriodic(0.01);

        trajectoryTracker = new RamseteTracker(Constants.PATH_FOLLOWING.RAMSETE_BETA, Constants.PATH_FOLLOWING.RAMSETE_ZETA);

        turningPID = new LancerPID(Constants.DRIVETRAIN.TURNING_kP, Constants.DRIVETRAIN.TURNING_kI, Constants.DRIVETRAIN.TURNING_kD);

        setkStatic(0);
    }

    public static synchronized Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }

        return instance;
    }

    @Override
    public Localization getLocalization() {
        return localization;
    }

    @NotNull
    @Override
    public DifferentialDrive getDifferentialDrive() {
        return Constants.DRIVETRAIN.DIFFERENTIAL_DRIVE;
    }

    @NotNull
    @Override
    public FalconMotor<Length> getLeftMotor() {
        return left.getMaster();
    }

    public Transmission getLeftTransmission() {
        return left;
    }

    @NotNull
    @Override
    public FalconMotor<Length> getRightMotor() {
        return right.getMaster();
    }

    public Transmission getRightTransmission() {
        return right;
    }

    @NotNull
    @Override
    public TrajectoryTracker getTrajectoryTracker() {
        return trajectoryTracker;
    }

    public void resetEncoders(){
        left.getMaster().setSelectedSensorPosition(0);
        right.getMaster().setSelectedSensorPosition(0);
    }

    public LancerPID getTurningPID(){
        return turningPID;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseDrivetrain());
    }

    //Oblog
    @Log(name = "Left Encoder Distance", rowIndex = 0, columnIndex = 0, width = 2, height = 1)
    public double leftEncoderDistance(){
        return left.getMaster().getSensorPosition().getFeet();
    }

    @Log(name = "Right Encoder Distance", rowIndex = 0, columnIndex = 2, width = 2, height = 1)
    public double rightEncoderDistance(){
        return right.getMaster().getSensorPosition().getFeet();
    }

    @Log.Graph(name = "Left Velocity", rowIndex = 1, columnIndex = 0, width = 2, height = 2)
    public double leftVelocity(){
        return VelocityKt.getFeetPerSecond(left.getMaster().getVelocity());
    }

    @Log.Graph(name = "Right Velocity", rowIndex = 1, columnIndex = 2, width = 2, height = 2)
    public double rightVelocity(){
        return VelocityKt.getFeetPerSecond(right.getMaster().getVelocity());
    }

    @Config(name = "Drivetrain P", defaultValueNumeric = Constants.DRIVETRAIN.TALON_kP, rowIndex = 0, columnIndex = 4, width = 2, height = 1)
    public void setP(double p){
        left.getAllMotors().forEach(falconSRX -> falconSRX.setKP(p));
        right.getAllMotors().forEach(falconSRX -> falconSRX.setKP(p));
    }

    @Config(name = "Drivetrain I", defaultValueNumeric = Constants.DRIVETRAIN.TALON_kI, rowIndex = 0, columnIndex = 6, width = 2, height = 1)
    public void setI(double i){
        left.getAllMotors().forEach(falconSRX -> falconSRX.setKI(i));
        right.getAllMotors().forEach(falconSRX -> falconSRX.setKI(i));
    }

    @Config(name = "Drivetrain D", defaultValueNumeric = Constants.DRIVETRAIN.TALON_kD, rowIndex = 0, columnIndex = 8, width = 2, height = 1)
    public void setD(double d){
        System.out.println(d);
        left.getAllMotors().forEach(falconSRX -> falconSRX.setKD(d));
        right.getAllMotors().forEach(falconSRX -> falconSRX.setKD(d));
    }
}
