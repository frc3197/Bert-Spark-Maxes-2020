/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Defines a Hood object. Code inside creates variables for the hood motor and limit switches.
 */
public class Hood extends SubsystemBase {
  public WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);

  /**
   * Creates a new Hood.
   */
  public Hood() {
    hoodMotor.setSafetyEnabled(false);
    hoodMotor.setInverted(true);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Moves the hood motor according to a speed value.
   * @param speed Speed value for hood. Currently a constant.
   */
  public void moveHood(double speed){
    hoodMotor.set(speed);
  }

  /**
   * Implements limit switches. Calibrates the encoder with limit switches.
   */
  public void encoderCalibrate(){
  }

  /**
   * Pulls Limelight's Y Offset value.
   * @return Limelight's Y Offset value
   */
  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

  /**
   * Pulls encoder value in ticks.
   * @return Encoder value in ticks
   */
  public void resetEncoderPosition(){
    hoodMotor.setSelectedSensorPosition(0);
  }

  // public void moveToPosition(double target){
  //   double current = hoodMotor.getSelectedSensorPosition();
  //   while(current < target){
  //     hoodMotor.set
  //   }
  // }
  public double getEncoderPosition(){
    SmartDashboard.putNumber("Hood Encoder Position", hoodMotor.getSelectedSensorPosition());
    return hoodMotor.getSelectedSensorPosition();
  }

  public double ticksToAngle(){
    return ((getEncoderPosition() /  2048) * (10/49));
  }

  public static double angleToTicks(double angle)
  {
    return ((angle / (10/49)) * 2048);
  }

  public int getForwardLimitSwitch(){
    return hoodMotor.isFwdLimitSwitchClosed();
  }

  public int getBackwardLimitSwitch(){
    return hoodMotor.isRevLimitSwitchClosed();
  }
}
