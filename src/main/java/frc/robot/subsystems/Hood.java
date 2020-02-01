/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
    hoodMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveHoodTicks(double source ,double speed, double encoderTicks) {
    double initial = getEncoderPosition();
    while (getEncoderPosition() < initial + encoderTicks) {
      System.out.println(getEncoderPosition());
      hoodMotor.set(speed);
    }
  }

  public void moveHood(double speed) {

    hoodMotor.set(speed);
  }

  public void resetHoodEncoder() {
    hoodMotor.setSelectedSensorPosition(0);

  }

  public double getEncoderPosition() {
    int pos = hoodMotor.getSelectedSensorPosition();
    return pos;
    // DO SOMETHING TO THE POSITION TO CALCULATE THE ANGLE
  }

  public void calibrateHoodEncoder() {
    while (forwardLimitSwitch.get() == false) {
      if (forwardLimitSwitch.get() == false) // If the forward limit switch is pressed, we want to keep the values
                                             // between -1 and 0
        hoodMotor.set(-.1);
      else {
        hoodMotor.set(0);
        resetHoodEncoder();

      }
    }
  }

  public boolean getLimitSwitch() {
    return !forwardLimitSwitch.get();
  }

  public static double getDistanceFromTarget() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    ty = Math.toRadians(ty);
    System.out.println("offset : " + ty);
    double limeDistance = 74 / (Math.tan(ty));
    return limeDistance;
  }

  public int getYOffset() {
    return (int) NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

}
