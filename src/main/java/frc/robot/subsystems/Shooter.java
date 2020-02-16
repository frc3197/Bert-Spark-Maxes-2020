/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Defines a Shooter object. Code inside creates a variable for the Shooter motor.
 */
public class Shooter extends SubsystemBase {

  private WPI_TalonFX TalonShooter1 = new WPI_TalonFX(Constants.TalonID.kShooter1.id);

  /**
   * Creates a new Shooter. Code inside configures encoder, minimum and maximum outputs, and PID Loop.
   */
  public Shooter() {
  
    TalonShooter1.setInverted(true);
    TalonShooter1.setSafetyEnabled(false);

    TalonShooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);
    TalonShooter1.configNominalOutputForward(0, Constants.kTimeoutMs);
    TalonShooter1.configNominalOutputReverse(0, Constants.kTimeoutMs);
    
    TalonShooter1.configPeakOutputForward(1, Constants.kTimeoutMs);
    TalonShooter1.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    TalonShooter1.config_kF(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.F, Constants.kTimeoutMs);
    TalonShooter1.config_kP(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.P, Constants.kTimeoutMs);
    TalonShooter1.config_kI(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.I, Constants.kTimeoutMs);
    TalonShooter1.config_kD(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.D, Constants.kTimeoutMs);

  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  
  }

  /**
   * Runs Shooter motor based on RPM
   * @param val RPM value
   */
  public void shooterVelocity(double val) {
    TalonShooter1.set(ControlMode.Velocity, val);
  }

  /**
   * Runs Shooter motor based on speed value
   * @param val Speed value
   */
  public void setShooter(double val) {
    TalonShooter1.set(val);
  }

  public boolean getMotor(){
    double val = TalonShooter1.get();
    if(val>.2) 
    return true;
    else return false;
  }

  /**
   * Pulls Limelight's X Offset value and prints it.
   * @return Limelight's X Offset value
   */
  public double getXOffset() {
    // System.out.println(NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0));
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0);
  }

  /**
   * Pulls Limelight's Y Offset value.
   * @return Limelight's Y-Offset value
   */
  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

  /**
   * Pulls encoder value in ticks.
   * @return Encoder value in ticks
   */
  public double getEncoderValue() {
    return TalonShooter1.getSelectedSensorPosition();
  }

  public double getVelocity(){
    double units = TalonShooter1.getSelectedSensorVelocity();
    return ((units/2048) * 60000);
  }
  /**
   * Resets the encoder position to zero.
   */
  public void resetEncoder() {
    TalonShooter1.setSelectedSensorPosition(0);
  }

}
