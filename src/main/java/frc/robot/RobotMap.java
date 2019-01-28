package frc.robot;

public class RobotMap {
    public static final class DRIVETRAIN {
        // Motors
        public static final int MASTER_LEFT = 1;
        public static final int LEFT_SLAVE_1 = 2;
        public static final int LEFT_SLAVE_2 = 3;

        public static final int MASTER_RIGHT = 4;
        public static final int RIGHT_SLAVE_1 = 5;
        public static final int RIGHT_SLAVE_2 = 6;
    }

    public static final class HATCH {
        // Motors
        public static final int PIVOT_MOTOR = 7;

        // Pneumatics
        public static final int HATCH_PISTON = 0;
    }

    public static final class CARGO {
        // Pneumatics
        public static final int CARGO_BLOCK_FORWARD = 1;
        public static final int CARGO_BLOCK_REVERSE = 2;

        public static final int CARGO_PIVOT_FORWARD = 3;
        public static final int CARGO_PIVOT_REVERSE = 4;
    }

    public static final class CLIMBER {
        // Motors
        public static final int CLIMBER_MOTOR = 8;

        //Pneumatics
        public static final int LIFTOFF_FORWARD = 5;
        public static final int LIFTOFF_REVERSE = 6;
    }
}
