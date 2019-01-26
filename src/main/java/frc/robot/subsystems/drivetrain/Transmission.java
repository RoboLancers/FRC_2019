package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.wrappers.ctre.FalconSRX;
import java.util.Arrays;
import java.util.List;

public class Transmission extends Subsystem {
    private FalconSRX<Length> master, slave1, slave2;
    private List<FalconSRX<Length>> allFalcons;

    Transmission(int masterPort, int slave1Port, int slave2Port) {
        master = new FalconSRX<>(masterPort, Constants.nativeUnitModel, Constants.TIMEOUT_MS);
        slave1 = new FalconSRX<>(slave1Port, Constants.nativeUnitModel, Constants.TIMEOUT_MS);
        slave2 = new FalconSRX<>(slave2Port, Constants.nativeUnitModel, Constants.TIMEOUT_MS);

        slave1.follow(master);
        slave2.follow(master);

        allFalcons = Arrays.asList(master, slave1, slave2);

        for(FalconSRX<Length> falconSRX : allFalcons) {
            falconSRX.setKP(Constants.kDrivetrainKP);
            falconSRX.setKF(Constants.kDrivetrainF);
            falconSRX.configContinuousCurrentLimit(Constants.kCurrentLimit);
            falconSRX.setBrakeMode(NeutralMode.Brake);
        }
    }

    public FalconSRX<Length> getMaster() {
        return master;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
