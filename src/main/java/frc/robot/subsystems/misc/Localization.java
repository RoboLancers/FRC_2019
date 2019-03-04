package frc.robot.subsystems.misc;

import frc.robot.subsystems.drivetrain.Drivetrain;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

@SuppressWarnings("unused")
public class Localization implements Loggable {
    private static Localization instance;

    public synchronized static Localization getInstance() {
        if (instance == null) {
            instance = new Localization();
        }
        return instance;
    }

    @Log(name = "Robot X")
    public double robotX(){
        return Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getX().getFeet();
    }

    @Log(name = "Robot Y")
    public double robotY(){
        return Drivetrain.getInstance().getLocalization().getRobotPosition().getTranslation().getY().getFeet();
    }

    @Log(name = "Robot Heading")
    public double robotHeading(){
        return Drivetrain.getInstance().getLocalization().getRobotPosition().getRotation().getDegree();
    }
}
