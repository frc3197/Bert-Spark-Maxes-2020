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

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private WPI_TalonFX TalonCPScrub = new WPI_TalonFX(Constants.TalonID.kCPScrub.id);
  private WPI_TalonFX TalonCPArm = new WPI_TalonFX(Constants.TalonID.kCPArm.id);

  public ControlPanel() {
    TalonCPScrub.setSafetyEnabled(false);
    TalonCPArm.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void panelSpin(double val) {
    TalonCPScrub.set(ControlMode.PercentOutput, val);
  }

  public void moveCPArm(double val) {
    TalonCPArm.set(ControlMode.PercentOutput, val);
  }

}
