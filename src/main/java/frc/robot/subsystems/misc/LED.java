package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class LED extends Subsystem {
    private Blinkin blinkin;

    private static LED leds;

   private LED() {
       blinkin = new Blinkin(RobotMap.LED);
   }

   public void setPattern(Blinkin.PatternType pattern) {
       blinkin.setPattern(pattern);
   }

   public static LED getInstance() {
       if (leds == null) {
           leds = new LED();
       }
       return leds;
   }

    @Override
    protected void initDefaultCommand() {}
}
