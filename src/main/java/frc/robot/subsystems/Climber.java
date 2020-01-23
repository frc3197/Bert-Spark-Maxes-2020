/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates new motors.
   */

   public WPI_TalonFX climberTalon = new WPI_TalonFX(Constants.TalonID.kHood1.id);


  public Climber() {
   climberTalon.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


public boolean moveClimber(double val)
{
  boolean moving = true;
SmartDashboard.putBoolean("Climber Is Moving",moving);
  climberTalon.set(val);
  return moving; 


}









}
