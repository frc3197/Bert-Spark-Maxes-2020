/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Defines a Turret object. Code inside creates a variable for the Turret motor.
 */
public class Turret extends SubsystemBase {
 
  private final WPI_TalonFX turretMotor = new WPI_TalonFX(Constants.TalonID.kTurret.id);

   /**
   * Creates a new Turret.
   */
  public Turret() {
    turretMotor.setSafetyEnabled(false);
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
  }

  /**
   * Runs the Turret motor based on a speed value.
   * @param val Speed value
   */
  public double getXOffset() {
    // System.out.println(NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0));
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0);
  }



  public void turn(double val){
    turretMotor.set(val);
  }
}
