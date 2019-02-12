package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import frc.robot.subsystems.manipulators.hatch.HatchPivot;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab mainShuffleboardDisplay;

    private NetworkTableEntry
            leftEncoderCountEntry, rightEncoderCountEntry, leftVelocityEntry, rightVelocityEntry,
            armEncoderEntry, armAngleEntry, armErrorEntry, liftOffPistonStateEntry, armLimitSwitchVoltage,
            cargoBlockStateEntry, cargoPivotStateEntry,
            hatchPivotEncoderCountEntry, hatchEjectorStateEntry, hatchLimitSwitchEntry,
            robotXEntry, robotYEntry, robotHeadingEntry;

    private ShuffleboardLayout drivetrainList, cargoList, climberList, hatchList, localizationList;

    private NetworkInterface(){
        mainShuffleboardDisplay = Shuffleboard.getTab("Main Display");

        drivetrainList = mainShuffleboardDisplay.getLayout("Drivetrain", BuiltInLayouts.kList).withPosition(0, 0).withSize(2, 5);
        cargoList = mainShuffleboardDisplay.getLayout("Cargo", BuiltInLayouts.kList).withPosition(2, 0).withSize(2, 3);

        climberList = mainShuffleboardDisplay.getLayout("Climber", BuiltInLayouts.kList).withPosition(4, 0).withSize(2, 5);
        hatchList = mainShuffleboardDisplay.getLayout("Hatch", BuiltInLayouts.kList).withPosition(6, 0).withSize(2, 3);

        localizationList = mainShuffleboardDisplay.getLayout("Localization", BuiltInLayouts.kList).withPosition(8, 0).withSize(2, 4);

        leftEncoderCountEntry = drivetrainList.add("Left Encoder Count", 0.0).getEntry();
        rightEncoderCountEntry = drivetrainList.add("Right Encoder Count", 0.0).getEntry();

        leftVelocityEntry = drivetrainList.add("Left Velocity", 0.0).getEntry();
        rightVelocityEntry = drivetrainList.add("Right Velocity", 0.0).getEntry();

        cargoBlockStateEntry = cargoList.add("Cargo Block State", "").getEntry();
        cargoPivotStateEntry = cargoList.add("Cargo Pivot State", "").getEntry();

        armEncoderEntry = climberList.add("Arm Encoder Count", 0.0).getEntry();
        armAngleEntry = climberList.add("Arm Angle", 0.0).getEntry();

        armErrorEntry = climberList.add("Arm Closed Loop Error", 0.0).getEntry();
        armLimitSwitchVoltage = climberList.add("Arm Voltage", 0.0).getEntry();

        liftOffPistonStateEntry = climberList.add("Liftoff Piston State", "").getEntry();

        hatchPivotEncoderCountEntry = hatchList.add("Hatch Pivot Encoder Count", 0.0).getEntry();
        hatchEjectorStateEntry = hatchList.add("Hatch Ejector State", "").getEntry();
        hatchLimitSwitchEntry = hatchList.add("Hatch Limit Switch", false).getEntry();

        robotXEntry = localizationList.add("Robot X", 0.0).getEntry();
        robotYEntry = localizationList.add("Robot Y", 0.0).getEntry();
        robotHeadingEntry = localizationList.add("Robot Angle", 0.0).getEntry();

        NetworkTableInstance.getDefault()
                .getEntry("/CameraPublisher/FrontJeVois/streams")
                .setStringArray(new String[]{"mjpeg:http://roborio-321-frc.local:1180/?action=stream"});

        NetworkTableInstance.getDefault()
                .getEntry("/CameraPublisher/BackJeVois/streams")
                .setStringArray(new String[]{"mjpeg:http://roborio-321-frc.local:1181/?action=stream"});

        Shuffleboard.startRecording();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());

        leftVelocityEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getLeftMotor().getVelocity()));
        rightVelocityEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getRightMotor().getVelocity()));

//        leftVelocityEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSelectedSensorVelocity());
//        rightVelocityEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSelectedSensorVelocity());

        cargoBlockStateEntry.setString(CargoBlock.getInstance().get().toString());
        cargoPivotStateEntry.setValue(CargoPivot.getInstance().get().toString());

        armEncoderEntry.setDouble(ClimberArm.getInstance().getPosition());
        armAngleEntry.setDouble(ClimberArm.getInstance().getAngle());
        armErrorEntry.setDouble(ClimberArm.getInstance().getClosedLoopError());
        armLimitSwitchVoltage.setDouble(ClimberArm.getInstance().getVoltage());

        liftOffPistonStateEntry.setString(LiftoffPiston.getInstance().get().toString());

        hatchPivotEncoderCountEntry.setDouble(HatchPivot.getInstance().getMaster().getSelectedSensorPosition());
        hatchLimitSwitchEntry.setBoolean(HatchEjector.getInstance().hasHatch());

        robotXEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getX().getFeet());
        robotYEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getY().getFeet());
        robotHeadingEntry.setDouble(Drivetrain.getInstance().getLocalization().getRobotPosition().getRotation().getDegree());
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
