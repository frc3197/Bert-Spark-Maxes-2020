
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
  public WPI_TalonFX l1SparkMax = new WPI_TalonFX(Constants.TalonID.kLeft1.id);
  public WPI_TalonFX r1SparkMax = new WPI_TalonFX(Constants.TalonID.kRight1.id);
  public WPI_TalonFX l2SparkMax = new WPI_TalonFX(Constants.TalonID.kLeft2.id);
  public WPI_TalonFX r2SparkMax = new WPI_TalonFX(Constants.TalonID.kRight2.id);
  public WPI_TalonFX testTalon = new WPI_TalonFX(Constants.TalonID.kTest1.id);
  /**
   * Creates a new Speed Controller Group for the two left,and two right talon
   * motors
   */
  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1SparkMax, testTalon);
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
    // l1SparkMax.TalonFXControlMode(2);

  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  /**
   * Constructor for the tankDrive method
   */

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }
}