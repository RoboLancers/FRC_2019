package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "unused"})
public class PixyArduino {
    private static final int BAUD_RATE = 115200;
    private static final int DATA_BITS = 8;
    private static final String PACKET_START_CHAR = "{";
    private static final String PACKET_END_CHAR = "}";
    private static final String PACKET_DILEM_CHAR = ",";
    private static final int PACKET_NUM_EXPECTED_FIELDS = 2;
    private SerialPort serialPort;
    private StringBuffer packetBuffer = new StringBuffer(100);
    private boolean pixyOnline;
    private boolean hasLine;
    private double lineX;
    Thread packetListenerThread = new Thread(() -> {
        while (!Thread.interrupted()) {
            backgroundUpdate();
        }
    });

    public PixyArduino() {
        int retryCounter = 0;

        while (serialPort == null && retryCounter++ < 5) {
            try {
                serialPort = new SerialPort(BAUD_RATE, SerialPort.Port.kUSB, DATA_BITS, SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
            } catch (Exception e) {
                e.printStackTrace();
                sleep(500);
            }
        }

        if (serialPort == null) {
            DriverStation.reportError("Cannot open serial port to JeVois. Not starting vision system.", false);
            return;
        }

        packetListenerThread.setDaemon(true);
        packetListenerThread.start();
    }

    public boolean isPixyOnline() {
        return pixyOnline;
    }

    public boolean hasLine() {
        return hasLine;
    }

    public double getLineX() {
        return lineX;
    }

    public String blockAndGetPacket(double timeout) {
        String retval = null;

        if (serialPort != null) {
            double startTime = Timer.getFPGATimestamp();
            int endIdx;
            int startIdx;

            while (Timer.getFPGATimestamp() - startTime < timeout) {
                if (serialPort.getBytesReceived() > 0) {
                    packetBuffer.append(serialPort.readString());

                    if (packetBuffer.indexOf(PACKET_START_CHAR) != -1) {
                        endIdx = packetBuffer.lastIndexOf(PACKET_END_CHAR);
                        if (endIdx != -1) {
                            startIdx = packetBuffer.lastIndexOf(PACKET_START_CHAR, endIdx);

                            if (startIdx == -1) {
                                startIdx = packetBuffer.lastIndexOf(PACKET_START_CHAR);
                                packetBuffer.delete(0, startIdx);
                            } else {
                                retval = packetBuffer.substring(startIdx + 1, endIdx - 1);
                                packetBuffer.delete(0, endIdx + 1);
                                break;
                            }
                        } else {
                            sleep(5);
                        }
                    } else {
                        packetBuffer.delete(0, packetBuffer.length());
                        sleep(5);
                    }
                } else {
                    sleep(5);
                }
            }
        }

        return retval;
    }

    @SuppressWarnings("SameParameterValue")
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("DO NOT WAKE THE SLEEPY BEAST");
            e.printStackTrace();
        }
    }

    public int parsePacket(String pkt) {
        final int TGT_VISIBLE_TOKEN_IDX = 0;
        final int TGT_LINE_X_TOKEN_IDX = 1;

        String[] tokens = pkt.split(",");

        if (tokens.length < PACKET_NUM_EXPECTED_FIELDS) {
            DriverStation.reportError("Got malformed vision packet. Expected 2 tokens, but only found " + tokens.length + ". Packet Contents: " + pkt, false);
            return -1;
        }

        try {
            hasLine = Double.parseDouble(tokens[TGT_VISIBLE_TOKEN_IDX]) == 1;
            lineX = Double.parseDouble(tokens[TGT_LINE_X_TOKEN_IDX]);
        } catch (Exception e) {
            DriverStation.reportError("Unhandled exception while parsing Vision packet: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()), false);
            return -1;
        }

        return 0;
    }

    private void backgroundUpdate() {
        String packet = blockAndGetPacket(2.0);

        if (packet != null) {
            pixyOnline = parsePacket(packet) == 0;
        } else {
            pixyOnline = false;
            DriverStation.reportWarning("Cannot get packet from Pixy Arduino", false);
        }
    }
}
