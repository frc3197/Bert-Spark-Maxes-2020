/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
  /**
   * Creates a new Hopper.
   */

  public final WPI_TalonFX hopMotor1 = new WPI_TalonFX(8);
  public final WPI_TalonFX hopMotor2 = new WPI_TalonFX(9);
  public final WPI_TalonFX hopMotor3 = new WPI_TalonFX(10);

  public Hopper() {
    hopMotor1.setSafetyEnabled(false);
    hopMotor2.setSafetyEnabled(false);
    hopMotor3.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void hopperStart(double val) {
    hopMotor1.set(val);
    hopMotor2.set(val);
    hopMotor3.set(val);
  }
}
