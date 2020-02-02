/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class LimelightHood extends CommandBase {
  Hood hood;
  /**
   * Creates a new LimelightHood.
   */
  public LimelightHood(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   hood.encoderCalibrate();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hood.moveHoodtoAngle();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
