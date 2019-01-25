package frc.robot.enums;

public enum ClimberState{
    UP(2000), DOWN(0);

    public double getPosition(){
        return power;
    }
    private double power;

    ClimberState(double power){
        this.power = power;
    }
}
