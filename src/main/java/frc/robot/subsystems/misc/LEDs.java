package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class LEDs extends Subsystem {
    private Blinkin blinkin;

    private static LEDs leds;

   private LEDs() {
       blinkin = new Blinkin(RobotMap.CARGO.LEDS);
   }

   public void setPattern(Blinkin.PatternType pattern) {
       blinkin.setPattern(pattern);
   }

   public static LEDs getInstance() {
       if (leds == null) {
           leds = new LEDs();
       }
       return leds;
   }

    @Override
    protected void initDefaultCommand() {

    }
}
