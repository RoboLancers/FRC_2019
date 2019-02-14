package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routines.LevelOneFrontCargoLeftStation;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.manipulators.cargo.CargoBlock;
import frc.robot.subsystems.manipulators.cargo.CargoPivot;
import frc.robot.subsystems.manipulators.climber.ClimberArm;
import frc.robot.subsystems.manipulators.climber.LiftoffPiston;
import frc.robot.subsystems.manipulators.hatch.HatchEjector;
import org.ghrobotics.lib.mathematics.units.derivedunits.VelocityKt;

@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
public class NetworkInterface {
    private static NetworkInterface instance;

    private ShuffleboardTab debugDisplay, mainDisplay;

    private NetworkTableEntry
            leftEncoderCountEntry, rightEncoderCountEntry, leftVelocityEntry, rightVelocityEntry,
            armEncoderEntry, armAngleEntry, armErrorEntry, liftOffPistonStateEntry, armLimitSwitchVoltage,
            cargoBlockStateEntry, cargoPivotStateEntry,
            hatchEjectorStateEntry, hatchLimitSwitchEntry,
            robotXEntry, robotYEntry, robotHeadingEntry;

    private ShuffleboardLayout drivetrainList, cargoList, climberList, hatchList, localizationList;

    private SendableChooser<StartingPosition> startingPositionChooser;
    private SendableChooser<Objective> objectiveChooser;

    private NetworkInterface(){
        mainDisplay = Shuffleboard.getTab("Main Display");

        startingPositionChooser = new SendableChooser<>();
        objectiveChooser = new SendableChooser<>();

        for(StartingPosition startingPosition : StartingPosition.values()){
            startingPositionChooser.addOption(startingPosition.name(), startingPosition);
        }

        for(Objective objective : Objective.values()){
            objectiveChooser.addOption(objective.name(), objective);
        }

        mainDisplay.add(startingPositionChooser).withPosition(0, 0).withSize(2, 1);
        mainDisplay.add(objectiveChooser).withPosition(0, 1).withSize(2, 1);

        debugDisplay = Shuffleboard.getTab("Debug Display");

        drivetrainList = debugDisplay.getLayout("Drivetrain", BuiltInLayouts.kList).withPosition(0, 0).withSize(2, 5);
        cargoList = debugDisplay.getLayout("Cargo", BuiltInLayouts.kList).withPosition(2, 0).withSize(2, 3);

        climberList = debugDisplay.getLayout("Climber", BuiltInLayouts.kList).withPosition(4, 0).withSize(2, 5);
        hatchList = debugDisplay.getLayout("Hatch", BuiltInLayouts.kList).withPosition(6, 0).withSize(2, 3);

        localizationList = debugDisplay.getLayout("Localization", BuiltInLayouts.kList).withPosition(8, 0).withSize(2, 4);

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

        hatchEjectorStateEntry = hatchList.add("Hatch Ejector State", "").getEntry();
        hatchLimitSwitchEntry = hatchList.add("Hatch Limit Switch", false).getEntry();

        robotXEntry = localizationList.add("Robot X", 0.0).getEntry();
        robotYEntry = localizationList.add("Robot Y", 0.0).getEntry();
        robotHeadingEntry = localizationList.add("Robot Angle", 0.0).getEntry();

        Shuffleboard.startRecording();
    }

    public void updateShuffleboard(){
        leftEncoderCountEntry.setDouble(Drivetrain.getInstance().getLeftTransmission().getMaster().getSensorPosition().getFeet());
        rightEncoderCountEntry.setDouble(Drivetrain.getInstance().getRightTransmission().getMaster().getSensorPosition().getFeet());

        leftVelocityEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getLeftMotor().getVelocity()));
        rightVelocityEntry.setDouble(VelocityKt.getFeetPerSecond(Drivetrain.getInstance().getRightMotor().getVelocity()));

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
    }

    public static synchronized NetworkInterface getInstance() {
        if (instance == null) {
            instance = new NetworkInterface();
        }

        return instance;
    }
}
