/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hopper extends SubsystemBase {
  /**
   * Creates a new Hopper.
   */

  public final WPI_TalonFX hopElevatorMotor = new WPI_TalonFX(Constants.TalonID.kElevator.id);
  public final WPI_TalonFX hopFeederMotor = new WPI_TalonFX(Constants.TalonID.kHopper.id);
  DigitalInput hopperLS = new DigitalInput(3);

  public Hopper() {
    hopElevatorMotor.setSafetyEnabled(false);
    hopFeederMotor.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean getLimit() {
    return hopperLS.get();
  }

  public void hopperFeeder(double val) {
    hopFeederMotor.set(val);
  }

  public void hopperElevator(double val) {
    hopElevatorMotor.set(val);
  }
}
