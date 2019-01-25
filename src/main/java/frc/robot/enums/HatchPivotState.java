package frc.robot.enums;

public enum HatchPivotState {
    UP(0), DOWN(1200), IN(3000);

    public double getPosition() {
        return power;
    }

    private double power;

    HatchPivotState(double power) {
      this.power = power;
    }

}
