
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void calibrateGyro() {
    gyro.calibrate();
  }

  public void resetEncoder() {
    l1TalonFX.setSelectedSensorPosition(0);
  }

  public double getEncoderValue() {
    return l1TalonFX.getSelectedSensorPosition();
  }

  public double getDistance() {
    double ticks = l1TalonFX.getSelectedSensorPosition();
    double distance = (ticks / 2048) * (6 * Math.PI);
    return distance;
  }

  public void tankDrive(double l, double r) {
    // System.out.println((l + r) / 2);
    drive.tankDrive(r, l, true);
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }
}
