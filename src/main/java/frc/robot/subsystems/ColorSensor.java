/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Defines a ColorSensor object.
 */
public class ColorSensor extends SubsystemBase {
    I2C sensor;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorsensor = new ColorSensorV3(i2cPort);

    protected final static int COMMAND_REGISTER_BIT = 0x80;
    protected final static int MULTI_BYTE_BIT = 0x20;

    protected final static int ENABLE_REGISTER = 0x00;
    protected final static int ATIME_REGISTER = 0x01;
    protected final static int PPULSE_REGISTER = 0x0E;

    protected final static int ID_REGISTER = 0x12;
    protected final static int CDATA_REGISTER = 0x14;
    protected final static int RDATA_REGISTER = 0x16;
    protected final static int GDATA_REGISTER = 0x18;
    protected final static int BDATA_REGISTER = 0x1A;
    protected final static int PDATA_REGISTER = 0x1C;

    /**
     * Creates a Color Sensor object.
     */
    public ColorSensor() {
        sensor = new I2C(I2C.Port.kOnboard, 0x52); // port, I2c address (0x39 old)

        sensor.write(COMMAND_REGISTER_BIT, 0b00000011); // power on, color sensor on
    }

    /**
     * Not used
     * @param address
     * @return
     */
    protected int readWordRegister(int address) {
        ByteBuffer buf = ByteBuffer.allocate(2);
        sensor.read(COMMAND_REGISTER_BIT | MULTI_BYTE_BIT | address, 2, buf);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        return buf.getShort(0);
    }

    /**
     * Detects color of target with color sensor.
     * @return Color detected or unknown
     */
    public Color getColor() {
        return m_colorsensor.getColor();
    }

    /**
     * Pulls Color Sensor's current detected red value.
     * @return Detected red value
     */
    public int red() {
        return readWordRegister(RDATA_REGISTER);
    }

    /**
     * Pulls Color Sensor's current detected green value.
     * @return Detected green value
     */
    public int green() {
        return readWordRegister(GDATA_REGISTER);
    }

    /**
     * Pulls Color Sensor's current detected blue value.
     * @return Detected blue value
     */
    public int blue() {
        return readWordRegister(BDATA_REGISTER);
    }

    /**
     * I dunno
     * @return
     */
    public int clear() {
        return readWordRegister(CDATA_REGISTER);
    }

    /**
     * Pulls Color Sensor's proximity value from target it's analyzing.
     * @return Proximity value
     */
    public int proximity() {
        return readWordRegister(PDATA_REGISTER);
    }

    /**
     * This method will be called once per scheduler run
     */
    @Override
    public void periodic() { 
    }
}
