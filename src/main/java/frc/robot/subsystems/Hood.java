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
  DigitalInput forwardLimitSwitch = new DigitalInput(0);

    

  /**
   * Creates a new Hood.
   */
  public final WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);

  public Hood() {
  hoodMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveHood(double speed) {
    hoodMotor.set(speed);
  }
  public void calibrateHoodEncoder(){
    if (forwardLimitSwitch.get()) // If the forward limit switch is pressed, we want to keep the values between -1 and 0
        hoodMotor.set(-.1);

  }

  public static double getDistanceFromTarget() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    ty = Math.toRadians(ty);
    System.out.println("offset : " + ty);
    double limeDistance = 74 / (Math.tan(ty));
    return limeDistance;
  }

  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

}
