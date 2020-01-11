
package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

/**
 * Importa the libraries for either the CANSparkMax library or the Talon library
 */
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private CANSparkMax l1SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kLeft1.id, MotorType.kBrushless);
  private CANSparkMax r1SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kRight1.id, MotorType.kBrushless);
  private CANSparkMax r2SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kRight2.id, MotorType.kBrushless);
  private CANSparkMax l2SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kLeft2.id, MotorType.kBrushless);

  /**
   * Creates a new Speed Controller Group for the two left,and two right talon
   * motors
   */
  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1SparkMax, l2SparkMax);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1SparkMax, r2SparkMax);

  private CANEncoder l1Encoder = new CANEncoder(l1SparkMax);
  private CANEncoder l2Encoder = new CANEncoder(l2SparkMax);
  private CANEncoder r1Encoder = new CANEncoder(r1SparkMax);
  private CANEncoder r2Encoder = new CANEncoder(r2SparkMax);
  /**
   * Combines the SpeedControllerGroup to create a differential drive.
   */
  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  public Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
  /**
   * Constructor for the DriveTrain
   */
  public DriveTrain() {
    l1SparkMax.setIdleMode(IdleMode.kBrake);
    l2SparkMax.setIdleMode(IdleMode.kBrake);
    r1SparkMax.setIdleMode(IdleMode.kBrake);
    r2SparkMax.setIdleMode(IdleMode.kBrake);
    l1Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    l2Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    r1Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    r2Encoder.setPositionConversionFactor((6 * Math.PI) / 7);

  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  /**
   * Constructor for the tankDrive method
   */
  public void tankDrive(double r, double l) {
    drive.tankDrive(l, r, true);
  }

  public void resetEncoders() {
    l1Encoder.setPosition(0);
    l2Encoder.setPosition(0);
    r1Encoder.setPosition(0);
    r2Encoder.setPosition(0);
  }

  public double getEncoderValue() {
    return l1Encoder.getPosition();
  }

  public double getAngle() {
    return gyro.getAngle();
  }
}
