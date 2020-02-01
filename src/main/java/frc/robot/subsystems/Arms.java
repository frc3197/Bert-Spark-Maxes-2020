/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {
  /**
   * Creates a new Arms.
   */
  public final WPI_TalonFX armsMotor1 = new WPI_TalonFX(11);
  public final WPI_TalonFX armsMotor2 = new WPI_TalonFX(12);

  DigitalInput forwardLimitSwitchBottom = new DigitalInput(1);
  DigitalInput forwardLimitSwitchTop = new DigitalInput(2);

  public Arms() {

    armsMotor1.setSafetyEnabled(false);
    armsMotor2.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveArms(double output) {
    armsMotor1.set(output);
    armsMotor2.set(output);
  }

  public boolean getBottomLimit() {
    return forwardLimitSwitchBottom.get();
  }

  public boolean getTopLimit() {
    return forwardLimitSwitchTop.get();
  }
}
