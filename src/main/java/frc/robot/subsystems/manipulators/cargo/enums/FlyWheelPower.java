package frc.robot.subsystems.manipulators.cargo.enums;

@SuppressWarnings("unused")
public enum FlyWheelPower {
    OUTTAKE(1), INTAKE(1), STOP(0);

    private double power;

    FlyWheelPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }
}