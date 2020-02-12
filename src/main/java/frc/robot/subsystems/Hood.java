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
import frc.robot.RobotContainer;

/**
 * Defines a Hood object. Code inside creates variables for the hood motor and limit switches.
 */
public class Hood extends SubsystemBase {
  public static WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);

  /**
   * Creates a new Hood.
   */
  public Hood() {
    hoodMotor.setSafetyEnabled(false);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Moves the hood motor according to a speed value.
   * @param speed Speed value for hood. Currently a constant.
   */
  public void moveHood(double speed){
    hoodMotor.set(speed);
  }

  /**
   * Implements limit switches. Calibrates the encoder with limit switches.
   */
  public void encoderCalibrate(){

    int forwardLimit = hoodMotor.isFwdLimitSwitchClosed();
    int reverseLimit = hoodMotor.isRevLimitSwitchClosed();
    int on = 1;
    int off = 0;
    if(reverseLimit == on && forwardLimit == on){
      moveHood(0);
    }
    else if(reverseLimit == on){
      moveHood(0);
    }
    
    else if(forwardLimit == on){

      while(reverseLimit == off){
        moveHood(-0.7);
      }

    }
    else{
      while(reverseLimit == off){
        moveHood(-0.7);
      }
    }
    hoodMotor.set(0);
    hoodMotor.setSelectedSensorPosition(0);
  }

  /**
   * Moves Hood angle based on calculated distance from target using Limelight values.
   */
  public void moveHoodtoAngle(){
    /*
    d = distance from the Power Port
    theta = angle of the hood from the back horizontal
    thetaL = angle of launch (approximately). Perpendicular to theta
    currentTicks = the current encoder position
    ticks = the ticks that the encoder should be at to be at the correct angle. (Calculated using FLM below)
      theta degrees(1 rotation/0.2 degrees)(2048 ticks/1 rotation) = 10240 * theta
    Command runs the hood forward until ticks = currentTicks (or when the difference between them is 0)
    */
    double d = RobotContainer.getDistanceFromTarget();
    double theta = Math.atan(80.25/d);
    double thetaL = 90 - theta;
    double currentTicks = getEncoderPosition();
    double idealTicks = 10240 * thetaL;

    while(idealTicks - currentTicks > 0){ //POTENTIAL PROBLEM: ticks is based on thetaL and currentTicks is based on theta. May be wrong.
      moveHood(0.2);
      currentTicks = getEncoderPosition();
    }
    //TODO: Test physics, implement, etc.
  }

  /**
   * Pulls Limelight's Y Offset value.
   * @return Limelight's Y Offset value
   */
  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

  /**
   * Pulls encoder value in ticks.
   * @return Encoder value in ticks
   */
  public void resetEncoderPosition(){
    hoodMotor.setSelectedSensorPosition(0);
  }

  public double getEncoderPosition(){
    return hoodMotor.getSelectedSensorPosition();
  }

  /**
   * Pulls state of forward limit switch.
   * @return State of forward limit switch
   */
  // public boolean getForwardLimitSwitch(){
  //   return hoodLSFront.get();
  // }

  /**
   * Pulls state of backward limit switch.
   * @return State of backward limit switch
   */
  // public boolean getBackwardLimitSwitch(){
  //   return hoodLSBack.get();
  // }
}
