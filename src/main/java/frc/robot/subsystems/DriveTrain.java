
package frc.robot.subsystems;

/**
 * Importa the libraries for either the CANSparkMax library or the Talon library
 */
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.AutoCommands.Drive;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates all the Talon objects
   */
  // private Talon l1Talon = new Talon(Constants.TalonID.kLeft1.id);
  // private Talon l2Talon = new Talon(Constants.TalonID.kLeft2.id);
  // private Talon r1Talon = new Talon(Constants.TalonID.kRight1.id);
  // private Talon r2Talon = new Talon(Constants.TalonID.kRight2.id);
  /**
   * Creates the IDs/DriveTrain for the NEO Brushless motors
   */
  private Talon l1SparkMax = new Talon(Constants.TalonID.kLeft1.id);
  private Talon r1SparkMax = new Talon(Constants.TalonID.kRight1.id);
  private Talon r2SparkMax = new Talon(Constants.TalonID.kRight2.id);
  private Talon l2SparkMax = new Talon(Constants.TalonID.kLeft2.id);

  /**
   * Creates a new Speed Controller Group for the two left,and two right talon
   * motors
   */
  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1SparkMax, l2SparkMax);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1SparkMax, r2SparkMax);
  /**
   * Combines the SpeedControllerGroup to create a differential drive.
   */
  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  /**
   * Constructor for the DriveTrain
   */
  public DriveTrain() {
    // l1SparkMax.setIdleMode(IdleMode.kBrake);
    // l2SparkMax.setIdleMode(IdleMode.kBrake);
    // r1SparkMax.setIdleMode(IdleMode.kBrake);
    // r2SparkMax.setIdleMode(IdleMode.kBrake);
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
}