package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.RegulateCompressor;

public class Pneumatic extends Subsystem {
    private Compressor compressor;

    private static Pneumatic instance;

    private Pneumatic(){
        compressor = new Compressor(RobotMap.COMPRESSOR);
    }

    public void regulateCompressor(){
        if(!compressor.getPressureSwitchValue() && !compressor.enabled()
                && isCompressorSafeToUse()){
            compressor.start();
        }else if(compressor.getPressureSwitchValue() && compressor.enabled()
                || !isCompressorSafeToUse()){
            compressor.stop();
        }
    }

    private boolean isCompressorSafeToUse(){
        return !((compressor.getCompressorCurrentTooHighFault() && !compressor.getCompressorCurrentTooHighStickyFault()) ||
                (compressor.getCompressorNotConnectedFault() && !compressor.getCompressorNotConnectedStickyFault()) ||
                (compressor.getCompressorShortedFault() && !compressor.getCompressorShortedStickyFault()));
    }

    public void stopCompressor(){
        compressor.stop();
    }

    public synchronized static Pneumatic getInstance(){
        if(instance == null){
            instance = new Pneumatic();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RegulateCompressor());
    }
}
