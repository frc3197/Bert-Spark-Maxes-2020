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
 * Defines an Intake object. Code inside creates a variable for the Intake motor.
 */
public class Intake extends SubsystemBase {
  
  public final WPI_TalonFX intakeMotor = new WPI_TalonFX(Constants.TalonID.kIntake.id);

  /**
   * Creates a new Intake.
   */
  public Intake() {
    intakeMotor.setSafetyEnabled(false);

  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Runs the Intake motor based on a speed value.
   * @param val Speed value. Currently a constant.
   */

  public void reverseTakeIn(double val){
    intakeMotor.set(val * -1);
  }
  public void takeIn(double val) {
    intakeMotor.set(ControlMode.PercentOutput, val);
  }
}


  