package frc.robot.subsystems.manipulators.climber.enums;

import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.ghrobotics.lib.mathematics.units.Rotation2dKt;

public enum ClimberState {
    UP(Rotation2dKt.getDegree(0)), DOWN(Rotation2dKt.getDegree(105));

    private Rotation2d rotation2d;

    ClimberState(Rotation2d rotation2d) {
        this.rotation2d = rotation2d;
    }

    public Rotation2d getRotation2d() {
        return rotation2d;
    }
}