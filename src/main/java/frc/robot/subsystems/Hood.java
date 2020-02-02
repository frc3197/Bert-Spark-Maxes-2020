/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Hood extends SubsystemBase {
  public static WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);
  DigitalInput hoodLS = new DigitalInput(4);
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

  public void encoderCalibrate(){
    while(!hoodLS.get()){
      hoodMotor.set(-0.2);
    }
    hoodMotor.set(0);
    hoodMotor.setSelectedSensorPosition(0);
  }

  public void moveHoodtoAngle(){
    //TODO: Test physics, implement, etc.
  }

  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

  public double getEncoderPosition(){
    return hoodMotor.getSelectedSensorPosition();
  }
}
