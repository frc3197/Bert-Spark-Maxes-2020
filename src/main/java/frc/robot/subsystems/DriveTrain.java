
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.AutoConstants;
import frc.robot.Constants;

/**
 * Defines a DriveTrain object. Code inside creates variables for drive motors
 * used.
 */
public class DriveTrain extends SubsystemBase  {
public WPI_TalonFX l1TalonFX = new WPI_TalonFX(Constants.TalonID.kLeft1.id);
  public WPI_TalonFX r1TalonFX = new WPI_TalonFX(Constants.TalonID.kRight1.id);
  public WPI_TalonFX l2TalonFX = new WPI_TalonFX(Constants.TalonID.kLeft2.id);
  public WPI_TalonFX r2TalonFX = new WPI_TalonFX(Constants.TalonID.kRight2.id);

  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1TalonFX, l2TalonFX);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1TalonFX, r2TalonFX);


  public DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
  public RamseteController ramseteController = new RamseteController(AutoConstants.ramseteB, AutoConstants.ramseteZeta);


  public Rotation2d m_rotation2d = new Rotation2d();
  public Pose2d m_pose = new Pose2d();
  public DifferentialDriveOdometry m_odometry;
  // TRACK WIDTH MEASUERED DISTANCE WHEEL CENTER TO WHEEL CENTER IN METERS
  public DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(AutoConstants.trackWidth);
  
  // public ChassisSpeeds chassisSpeeds = ramseteController.calculate(m_pose, AutoConstants.poseRef, AutoConstants.linearVelocityRefMeters, AutoConstants.angularVelocityRefRadiansPerSecond);

  // public PIDController leftPID = new PIDController(AutoConstants.kP, AutoConstants.kI, AutoConstants.kD);
  // public PIDController rightPID = new PIDController(AutoConstants.kP, AutoConstants.kI, AutoConstants.kD);
  
  
   
  //static Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
  public static AHRS gyro = new AHRS(SerialPort.Port.kUSB1);
  /**
   * Constructor for the DriveTrain
   */
  public DriveTrain() {
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    l1TalonFX.setSafetyEnabled(false);
    l2TalonFX.setSafetyEnabled(false);
    r1TalonFX.setSafetyEnabled(false);
    r2TalonFX.setSafetyEnabled(false);

    // l1TalonFX.configOpenloopRamp(.1);
    // l2TalonFX.configOpenloopRamp(.1);
    // r1TalonFX.configOpenloopRamp(.1);
    // r2TalonFX.configOpenloopRamp(.1);
    l1TalonFX.setInverted(true);
    l2TalonFX.setInverted(true);
    r1TalonFX.setInverted(true);
    r2TalonFX.setInverted(true);

    // leftPID.setSetpoint(0);
    // rightPID.setSetpoint(0);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
    var gyroAngle = Rotation2d.fromDegrees(getHeading());
    m_odometry.update(gyroAngle,getDistanceMetersLeft(), getDistanceMetersRight());
    // m_pose = m_odometry.update(gyroAngle,getDistanceMetersLeft(), getDistanceMetersRight());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoder();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  /**
   * Resets the Gyro
   */
  public void resetGyro() {
    gyro.reset();
  }

  
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocityMeters(), getRightEncoderVelocityMeters());
  }

  public double getLeftEncoderVelocityMeters(){
    double velocityU = l1TalonFX.getSelectedSensorVelocity();
    return ((velocityU / Constants.gear_ratio) / 2048) * Units.inchesToMeters(6 * Math.PI) * 10;
  }


  public double getRightEncoderVelocityMeters(){
    double velocityU = -r1TalonFX.getSelectedSensorVelocity();
    return ((velocityU / Constants.gear_ratio) / 2048) * Units.inchesToMeters(6 * Math.PI) * 10;
  }

  public void zeroHeading(){
    gyro.reset();
  }

  public Pose2d getPose(){
    
    return m_odometry.getPoseMeters();
  }

  public double getDistanceMetersLeft(){
    double encoder = l1TalonFX.getSelectedSensorPosition();
    double distance = ((encoder / Constants.gear_ratio) / 2048.0) * 0.478779 ; 
    return distance;
  }

  public double getDistanceMetersRight(){
    double encoder = -r1TalonFX.getSelectedSensorPosition();
    double distance = ((encoder / Constants.gear_ratio) / 2048.0) * 0.478779; 
    return distance;
  }
  /**
   * Calibrates the Gyro
   */

  public double getHeading(){
    return (getGyroAngle() % 360);
  }


  public void calibrateGyro() {
    // gyro.calibrate
  }

  /**
   * Resets the encoder to the 0 position
   */
  public void resetEncoder() {
    l1TalonFX.setSelectedSensorPosition(0);
    l2TalonFX.setSelectedSensorPosition(0);
    r1TalonFX.setSelectedSensorPosition(0);
    r2TalonFX.setSelectedSensorPosition(0);
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
    double distance = ((ticks / Constants.gear_ratio) / 2048) * (6 * Math.PI);
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

  public void arcadeDrive(double speed, double rotation) {

    drive.arcadeDrive(speed, rotation);
  }

  /**
   * Pulls Gyro's detected angle
   * 
   * @return Detected angle
   */
  public double getGyroAngle() {
    return -gyro.getAngle();
  }

  public void tankDriveVolts(double leftVolts,double rightVolts){
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(-rightVolts);
    drive.feed();

  }
}
