/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hopper;
/**
 * Defines a Elevator object. Code inside creates variables for 
 * elevator motor.
 */
public class Elevator extends SubsystemBase {
  Hopper hopper;

  public final WPI_TalonFX hopElevatorMotor = new WPI_TalonFX(Constants.TalonID.kElevator.id);



  /**
   * Creates a new Elevator.
   */
  public Elevator(Hopper hopper) {
    hopElevatorMotor.setSafetyEnabled(false);
    this.hopper = hopper;
   

  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Runs Elevator motor based on a speed value.
   * @param val Speed value. Curently a constant
   */
  public void hopperElevator(double val) {
    hopElevatorMotor.set(val);
  }
    public void hopperAll(double val) {
    hopper.hopperFeeder(val);
    hopElevatorMotor.set(val);
  }
}
