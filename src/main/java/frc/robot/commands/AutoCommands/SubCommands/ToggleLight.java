/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Defines a ToggleLight object. Creates a parameter turnOn to be used later.
 */
public class ToggleLight extends CommandBase {
  private boolean turnOn;
 /**
  * Creates a new ToggleLight.
  * @param turnOn Boolean relating to whether the Limelight LEDs are on.
  */
  public ToggleLight(boolean turnOn) {
    this.turnOn = turnOn;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Called when the command is initially scheduled.
   */
  @Override
  public void initialize() {
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * If turnOn is true, then the Limelight turns on.
   * If turnOn is false, then the Limelight turns off.
   */
  @Override
  public void execute() {
    if(turnOn){
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(0);
    }else{
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(1);
    }
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
