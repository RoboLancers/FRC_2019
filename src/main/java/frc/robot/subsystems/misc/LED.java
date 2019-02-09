package frc.robot.subsystems.misc;

import com.robolancers.lib.wrappers.Blinkin;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class LED extends Subsystem {
    private Blinkin blinkin;

    private static LED instance;

   private LED() {
       blinkin = new Blinkin(RobotMap.LED);
   }

   public void setPattern(Blinkin.PatternType pattern) {
       blinkin.setPattern(pattern);
   }

   public static LED getInstance() {
       if (instance == null) {
           instance = new LED();
       }
       return instance;
   }

    @Override
    protected void initDefaultCommand() {}
}
