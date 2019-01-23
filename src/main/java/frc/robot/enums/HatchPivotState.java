package frc.robot.enums;

public enum HatchPivotState {

    UP(254), DOWN(512), IN(0);

    public double position;

    HatchPivotState(double position){
        this.position = position;
    }

}
