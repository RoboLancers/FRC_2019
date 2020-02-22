package frc.robot.subsystems.misc;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    //read values periodically
    double target, x, yAxis, area;

    Limelight() {
        target = table.getEntry("tv").getDouble(0.0);
        x = table.getEntry("tx").getDouble(0.0);
        yAxis = table.getEntry("ty").getDouble(0.0);
        area = table.getEntry("ta").getDouble(0.0);
    }

    public double getTarget() {
        target = table.getEntry("tv").getDouble(0.0);
        return target;
    }

}
