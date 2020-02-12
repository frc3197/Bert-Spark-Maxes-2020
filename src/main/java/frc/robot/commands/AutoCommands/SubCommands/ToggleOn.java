/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ToggleOn extends InstantCommand {
  boolean turnOn;
  public ToggleOn(boolean turnOn) {
    this.turnOn = turnOn;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(turnOn){
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(0);
    }else{
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(1);
    }
  }
}
