package frc.robot;

import com.robolancers.lib.wrappers.hid.FlightController;
import com.robolancers.lib.wrappers.hid.XboxController;

public class OI {
    public static XboxController xboxController = new XboxController(0);
    public static FlightController flightController = new FlightController(1);
}
