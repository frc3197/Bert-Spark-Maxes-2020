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

/**
 * Defines a Hopper object. Code inside creates variables for 
 * feeder motor, elevator motor, and limit switch.
 */
public class Hopper extends SubsystemBase {
  

  public final WPI_TalonFX hopElevatorMotor = new WPI_TalonFX(Constants.TalonID.kElevator.id);
  public final WPI_TalonFX hopFeederMotor = new WPI_TalonFX(Constants.TalonID.kHopper.id);
  DigitalInput hopperLS = new DigitalInput(3);

  /**
   * Creates a new Hopper.
   */
  public Hopper() {
    hopElevatorMotor.setSafetyEnabled(false);
    hopFeederMotor.setSafetyEnabled(false);
    hopFeederMotor.setInverted(true);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Pulls state of Limit Switch.
   * @return State of Limit Swtich
   */
  public boolean getLimit() {
    return hopperLS.get();
  }

  /**
   * Runs Feeder motor based on a speed value.
   * @param val Speed value. Currently a constant
   */
  public void hopperFeeder(double val) {
    hopFeederMotor.set(val);
  }

  /**
   * Runs Elevator motor based on a speed value.
   * @param val Speed value. Curently a constant
   */
  public void hopperElevator(double val) {
    hopElevatorMotor.set(val);
  }
}
