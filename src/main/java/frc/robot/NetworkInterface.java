package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.misc.Camera;
import frc.robot.subsystems.misc.Sensors;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab competitionTab, drivetrainTab, cargoTab, hatchTab, climberTab, localizationTab;

    //Getters
    private NetworkTableEntry
            leftEncoderCountEntry, rightEncoderCountEntry, leftVelocityNativeEntry, rightVelocityNativeEntry, leftVelocityImperialEntry, rightVelocityImperialEntry,
            armEncoderEntry, armAngleEntry, armErrorEntry, liftOffPistonStateEntry, armLimitSwitchVoltage,
            cargoBlockStateEntry, cargoPivotStateEntry,
            hatchEjectorStateEntry, hatchLimitSwitchEntry,
            robotXEntry, robotYEntry, robotHeadingEntry,
            gyroHeading;

    //Setters
    private NetworkTableEntry
            drivetrainVelocityLeftF, drivetrainVelocityRightF, drivetrainVelocityP, drivetrainVelocityI, drivetrainVelocityD;

    private SendableChooser<StartingPosition> startingPositionChooser;
    private SendableChooser<Objective> objectiveChooser;

    private NetworkInterface(){
        competitionTab = Shuffleboard.getTab("Main Display");

        competitionTab.add("Front JeVois", Camera.getInstance().getFrontJeVois().getVisionCam()).withWidget(BuiltInWidgets.kCameraStream);
        competitionTab.add("Back JeVois", Camera.getInstance().getBackJeVois().getVisionCam()).withWidget(BuiltInWidgets.kCameraStream);

        startingPositionChooser = new SendableChooser<>();
        objectiveChooser = new SendableChooser<>();

        for(StartingPosition startingPosition : StartingPosition.values()){
            startingPositionChooser.addOption(startingPosition.name(), startingPosition);
        }

        for(Objective objective : Objective.values()){
            objectiveChooser.addOption(objective.name(), objective);
        }

        competitionTab.add("Starting Position Chooser", startingPositionChooser);
        competitionTab.add("Objective Chooser", objectiveChooser);

        drivetrainTab = Shuffleboard.getTab("Drivetrain");
        cargoTab = Shuffleboard.getTab("Cargo");
        climberTab = Shuffleboard.getTab("Climber");
        hatchTab = Shuffleboard.getTab("Hatch");
        localizationTab = Shuffleboard.getTab("Localization");

        leftEncoderCountEntry = drivetrainTab.add("Left Encoder Count", 0.0).getEntry();
        rightEncoderCountEntry = drivetrainTab.add("Right Encoder Count", 0.0).getEntry();

        leftVelocityNativeEntry = drivetrainTab.add("Left Velocity Native", 0.0).getEntry();
        rightVelocityNativeEntry = drivetrainTab.add("Right Velocity Native", 0.0).getEntry();

        leftVelocityImperialEntry = drivetrainTab.add("Left Velocity Imperial", 0.0).getEntry();
        rightVelocityImperialEntry = drivetrainTab.add("Right Velocity Imperial", 0.0).getEntry();

        drivetrainVelocityLeftF = drivetrainTab.add("Drivetrain Velocity Left F", Constants.DRIVETRAIN.LEFT_KF).getEntry();
        drivetrainVelocityRightF = drivetrainTab.add("Drivetrain Velocity Right F", Constants.DRIVETRAIN.RIGHT_KF).getEntry();

        drivetrainVelocityP = drivetrainTab.add("Drivetrain Talon P", Constants.DRIVETRAIN.TALON_kP).getEntry();
        drivetrainVelocityI = drivetrainTab.add("Drivetrain Talon I", Constants.DRIVETRAIN.TALON_kI).getEntry();
        drivetrainVelocityD = drivetrainTab.add("Drivetrain Talon D", Constants.DRIVETRAIN.TALON_kD).getEntry();

        drivetrainVelocityLeftF.addListener(entryNotification -> Drivetrain.getInstance().getLeftTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKF(entryNotification.value.getDouble())), EntryListenerFlags.kUpdate);
        drivetrainVelocityRightF.addListener(entryNotification -> Drivetrain.getInstance().getRightTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKF(entryNotification.value.getDouble())), EntryListenerFlags.kUpdate);

        drivetrainVelocityP.addListener(entryNotification -> {
            Drivetrain.getInstance().getLeftTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKP(entryNotification.value.getDouble()));
            Drivetrain.getInstance().getRightTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKP(entryNotification.value.getDouble()));
        }, EntryListenerFlags.kUpdate);

        drivetrainVelocityI.addListener(entryNotification -> {
            Drivetrain.getInstance().getLeftTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKI(entryNotification.value.getDouble()));
            Drivetrain.getInstance().getRightTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKI(entryNotification.value.getDouble()));
        }, EntryListenerFlags.kUpdate);

        drivetrainVelocityD.addListener(entryNotification -> {
            Drivetrain.getInstance().getLeftTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKD(entryNotification.value.getDouble()));
            Drivetrain.getInstance().getRightTransmission().getAllMotors().forEach(falconSRX -> falconSRX.setKD(entryNotification.value.getDouble()));
        }, EntryListenerFlags.kUpdate);

        cargoBlockStateEntry = cargoTab.add("Cargo Block State", "").getEntry();
        cargoPivotStateEntry = cargoTab.add("Cargo Pivot State", "").getEntry();

        hatchEjectorStateEntry = hatchTab.add("Hatch Ejector State", "").getEntry();
        hatchLimitSwitchEntry = hatchTab.add("Hatch Limit Switch", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry();

        armEncoderEntry = climberTab.add("Arm Encoder Count", 0.0).getEntry();
        armAngleEntry = climberTab.add("Arm Angle", 0.0).getEntry();

        armErrorEntry = climberTab.add("Arm Closed Loop Error", 0.0).getEntry();
        armLimitSwitchVoltage = climberTab.add("Arm Voltage", 0.0).getEntry();

        liftOffPistonStateEntry = climberTab.add("Liftoff Piston State", "").getEntry();

        robotXEntry = localizationTab.add("Robot X", 0.0).getEntry();
        robotYEntry = localizationTab.add("Robot Y", 0.0).getEntry();
        robotHeadingEntry = localizationTab.add("Robot Angle", 0.0).getEntry();

        gyroHeading = localizationTab.add("Gyro Heading", 0.0).getEntry();

        Shuffleboard.startRecording();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());

        leftVelocityImperialEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getLeftMotor().getVelocity()));
        rightVelocityImperialEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getRightMotor().getVelocity()));

        cargoBlockStateEntry.setString(CargoBlock.getInstance().get().toString());
        cargoPivotStateEntry.setValue(CargoPivot.getInstance().get().toString());

        armEncoderEntry.setDouble(ClimberArm.getInstance().getPosition());
        armAngleEntry.setDouble(ClimberArm.getInstance().getAngle());
        armErrorEntry.setDouble(ClimberArm.getInstance().getClosedLoopError());
        armLimitSwitchVoltage.setDouble(ClimberArm.getInstance().getVoltage());

        liftOffPistonStateEntry.setString(LiftoffPiston.getInstance().get().toString());

        hatchEjectorStateEntry.setString(HatchEjector.getInstance().get().toString());
        hatchLimitSwitchEntry.setBoolean(HatchEjector.getInstance().hasHatch());

        robotXEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getX().getFeet());
        robotYEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getY().getFeet());
        robotHeadingEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getRotation().getDegree());

        gyroHeading.setDouble(Sensors.getInstance().getFusedHeading());

        SmartDashboard.putNumber("Number of Lines", Camera.getInstance().getPixy().getLine().getVectors().length);
    }

    public SendableChooser<StartingPosition> getStartingPositionChooser(){
        return startingPositionChooser;
    }

    public SendableChooser<Objective> getObjectiveChooser(){
        return objectiveChooser;
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
