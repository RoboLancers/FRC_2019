package frc.robot;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import frc.robot.subsystems.misc.Sensors;

import java.util.Random;

public class Logger {

    private static Logger instance;
    private BadLog badLog;

    private Logger(){
        int session = new Random().nextInt();
        badLog = BadLog.init("/home/lvuser/log/" + Integer.toHexString(session).toUpperCase() + ".bag");

        BadLog.createValue("Match_Number", "" + DriverStation.getInstance().getMatchNumber());
        BadLog.createValue("Alliance", "" + DriverStation.getInstance().getAlliance());
        BadLog.createValue("Event_Name", "" + DriverStation.getInstance().getEventName());
        BadLog.createValue("Location", "" + DriverStation.getInstance().getLocation());
        BadLog.createValue("Match_Type", "" + DriverStation.getInstance().getMatchType());

        BadLog.createTopic("Match_Time", "seconds", () -> DriverStation.getInstance().getMatchTime());
        BadLog.createTopic("Robot_Voltage", "V", RobotController::getBatteryVoltage);
        BadLog.createTopic("Robot_Y", "degrees", () -> Sensors.getInstance().getPitch());
        BadLog.createTopic("Robot_X", "degrees", () -> Sensors.getInstance().getRoll());
        BadLog.createTopic("Robot_Z", "degrees", () -> Sensors.getInstance().getYaw());


        badLog.finishInitialization();
    }

    public void updateBadlog(){
        badLog.updateTopics();
        badLog.log();
    }

    public static synchronized Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }
}
