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

public class Hood extends SubsystemBase {
  public static WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);
  /**
   * Creates a new Hood.
   */
  public Hood() {
hoodMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
public void moveHood(double speed){
  hoodMotor.set(speed);
}
public void resetEncoderPosition(){
hoodMotor.setSelectedSensorPosition(0);
}

public void moveHoodtoAngle(){
}



  public double getEncoderPosition(){
    return hoodMotor.getSelectedSensorPosition();
  }
}
