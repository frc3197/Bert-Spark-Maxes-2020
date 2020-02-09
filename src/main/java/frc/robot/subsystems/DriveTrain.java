
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Defines a DriveTrain object. Code inside creates variables for drive motors
 * used.
 */
public class DriveTrain extends SubsystemBase {
  public WPI_TalonFX l1TalonFX = new WPI_TalonFX(Constants.TalonID.kLeft1.id);
  public WPI_TalonFX r1TalonFX = new WPI_TalonFX(Constants.TalonID.kRight1.id);
  public WPI_TalonFX l2TalonFX = new WPI_TalonFX(Constants.TalonID.kLeft2.id);
  public WPI_TalonFX r2TalonFX = new WPI_TalonFX(Constants.TalonID.kRight2.id);

  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1TalonFX, l2TalonFX);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1TalonFX, r2TalonFX);

  public DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

  /**
   * Constructor for the DriveTrain
   */
  public DriveTrain() {
    l1TalonFX.setSafetyEnabled(false);
    l2TalonFX.setSafetyEnabled(false);
    r1TalonFX.setSafetyEnabled(false);
    r2TalonFX.setSafetyEnabled(false);

    l1TalonFX.configOpenloopRamp(.1);
    l2TalonFX.configOpenloopRamp(.1);
    r1TalonFX.configOpenloopRamp(.1);
    r2TalonFX.configOpenloopRamp(.1);
    l1TalonFX.setInverted(true);
    l1TalonFX.setInverted(true);
    l1TalonFX.setInverted(true);
    l1TalonFX.setInverted(true);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {

  }

  /**
   * Resets the Gyro
   */
  public void resetGyro() {
    gyro.reset();
  }

  /**
   * Calibrates the Gyro
   */
  public void calibrateGyro() {
    gyro.calibrate();
  }

  /**
   * Resets the encoder to the 0 position
   */
  public void resetEncoder() {
    l1TalonFX.setSelectedSensorPosition(0);
  }

  /**
   * Pulls current encoder value
   * 
   * @return Encoder value
   */
  public double getEncoderValue() {
    return l1TalonFX.getSelectedSensorPosition();
  }

  /**
   * Pulls encoder tick value and converts it to inches.
   * 
   * @return Calculated inches
   */
  public double getDistance() {
    double ticks = l1TalonFX.getSelectedSensorPosition();
    double distance = (ticks / 2048) * (6 * Math.PI);
    return distance;
  }

  /**
   * Drives motors based on Y-Axis of each side's joystick.
   * 
   * @param l Left joystick Y-Axis value
   * @param r Right joystick Y-Axis value
   */
  public void tankDrive(double l, double r) {
    // System.out.println((l + r) / 2);
    drive.tankDrive(r, l, true);
  }

  /**
   * Pulls Gyro's detected angle
   * 
   * @return Detected angle
   */
  public double getGyroAngle() {
    return gyro.getAngle();
  }
}
