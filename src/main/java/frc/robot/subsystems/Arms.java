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

/**
 * Defines subsystem for Arms to be used in code.
 */
public class Arms extends SubsystemBase {
  /**
   * Creates a new Arms.
   */
  public final WPI_TalonFX armsMotor1 = new WPI_TalonFX(11);
  public final WPI_TalonFX armsMotor2 = new WPI_TalonFX(12);

  /**
   * Creates two limit switches.
   */
  DigitalInput forwardLS = new DigitalInput(1);
  DigitalInput backwardLS = new DigitalInput(2);

  public Arms() {

    armsMotor1.setSafetyEnabled(false);
    armsMotor2.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets motor speed for the arms.
   * @param output Value for speed of arms. Currently a constant.
   */
  public void moveArms(double output) {
    armsMotor1.set(output);
    armsMotor2.set(output);
  }

  /**
   * Pulls state of the top limit switch.
   * @return State of the top limit switch
   */
  public boolean getTopLimit(){
    return forwardLS.get();
  }

  /**
   * Pulls state of the bottom limit switch.
   * @return State of the bottom limit switch
   */
  public boolean getBottomLimit(){
    return backwardLS.get();
  }
  // public boolean getLimit(){
  //   if(forwardLS1.get() || forwardLS2.get()){
  //     return true;
  //   }else{
  //     return false;
  //   }
  // }
}
