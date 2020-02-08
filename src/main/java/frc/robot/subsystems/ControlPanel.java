/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Defines a Control Panel object.
 */
public class ControlPanel extends SubsystemBase {
  
  private WPI_TalonFX TalonCPScrub = new WPI_TalonFX(Constants.TalonID.kCPScrub.id);
  private WPI_TalonFX TalonCPArm = new WPI_TalonFX(Constants.TalonID.kCPArm.id);

  /**
   * Creates a new ControlPanel.
   */
  public ControlPanel() {
    TalonCPScrub.setSafetyEnabled(false);
    TalonCPArm.setSafetyEnabled(false);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  
  }

  /**
   * Runs the motor to spin the Control Panel according to a speed value.
   * @param val Speed Value. Currently a constant.
   */
  public void panelSpin(double val) {
    TalonCPScrub.set(ControlMode.PercentOutput, val);
  }

  /**
   * Moves the Control Panel arm according to a speed value.
   * @param val Speed Value. Currently a constant.
   */
  public void moveCPArm(double val) {
    TalonCPArm.set(ControlMode.PercentOutput, val);
  }

}
