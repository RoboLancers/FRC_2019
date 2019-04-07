package frc.robot.subsystems.manipulators.cargo.enums;

@SuppressWarnings("unused")
public enum FlywheelPower {
    OUTTAKE(-1), INTAKE(0.75), STOP(0), SLOW_OUTTAKE(-0.1);

    private double power;

    FlywheelPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}