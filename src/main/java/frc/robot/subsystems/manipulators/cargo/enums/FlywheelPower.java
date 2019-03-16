package frc.robot.subsystems.manipulators.cargo.enums;

@SuppressWarnings("unused")
public enum FlywheelPower {
    OUTTAKE(-0.75), INTAKE(0.75), STOP(0);

    private double power;

    FlywheelPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}