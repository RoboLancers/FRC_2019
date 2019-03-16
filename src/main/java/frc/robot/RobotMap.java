package frc.robot;

public class RobotMap {
    public static final class DRIVETRAIN {
        // Motors
        public static final int MASTER_LEFT = 14;
        public static final int LEFT_SLAVE_1 = 12;
        public static final int LEFT_SLAVE_2 = 13;

        public static final int MASTER_RIGHT = 0;
        public static final int RIGHT_SLAVE_1 = 1;
        public static final int RIGHT_SLAVE_2 = 2;
    }

    public static final class HATCH {
        // Pneumatics
        public static final int EJECTOR_IN = 6;
        public static final int EJECTOR_OUT = 1;

        public static final int HATCH_HOLDER_UP = 7;
        public static final int HATCH_HOLDER_DOWN = 0;

        // Sensors
        public static final int LIMIT_SWITCH = 0;

        public static final int MODULE = 1;
    }

    public static final class CARGO {
        // Motors
        public static final int INTAKE_LEFT = 9;
        public static final int INTAKE_RIGHT = 10;

        // Pneumatics
        public static final int BLOCK_DOWN = 2;
        public static final int BLOCK_UP = 5;

        public static final int PIVOT_UP = 4;
        public static final int PIVOT_DOWN = 3;
    }

    public static final class CLIMBER {
        // Motors
        public static final int ARM = 15;

        //Pneumatics
        public static final int LIFTOFF_PISTON_UP = 7;
        public static final int LIFTOFF_PISTON_DOWN = 0;

        //Sensors
        public static final int ARM_LIMIT_SWITCH = 3;
    }

    public static final class MISC {
        public static final int LED = 9;

        public static final int PIGEON_TALON = 11;

        public static final int REV_PRESSURE_SENSOR_PORT = 0;
    }
}
