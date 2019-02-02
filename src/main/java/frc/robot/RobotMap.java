package frc.robot;

public class RobotMap {
    public static final class DRIVETRAIN {
        // Motors
        public static final int MASTER_LEFT = 14;
        public static final int LEFT_SLAVE_1 = 12;//
        public static final int LEFT_SLAVE_2 = 13;

        public static final int MASTER_RIGHT = 0;
        public static final int RIGHT_SLAVE_1 = 1;
        public static final int RIGHT_SLAVE_2 = 2;//
    }

    public static final class HATCH {
        // Motors
        public static final int PIVOT = 7;

        // Pneumatics
        public static final int EJECTOR = 0;

        // Sensors
        public static final int LIMIT_SWITCH_PORT = 0;
    }

    public static final class CARGO {
        // Pneumatics
        public static final int BLOCK_FORWARD = 1;
        public static final int BLOCK_REVERSE = 2;

        public static final int PIVOT_FORWARD = 3;
        public static final int PIVOT_REVERSE = 4;
    }

    public static final class CLIMBER {
        // Motors
        public static final int ARM = 15;

        //Pneumatics
        public static final int LIFTOFF_PISTON_FORWARD = 5;
        public static final int LIFTOFF_PISTON_REVERSE = 6;
    }
}
