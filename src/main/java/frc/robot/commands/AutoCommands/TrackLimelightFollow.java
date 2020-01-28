/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TrackLimelightFollow extends CommandBase {
  TrackLimelightY m_limelightTrackY;
  TrackLimelightX m_limelightTrackX;
  DriveTrain driveTrain;

  /**
   * Creates a new TrackLimelightFollow.
   */
  public TrackLimelightFollow(DriveTrain driveTrain, TrackLimelightX m_limelightTrackX,
      TrackLimelightY m_limelightTrackY) {
    this.driveTrain = driveTrain;
    this.m_limelightTrackX = m_limelightTrackX;
    this.m_limelightTrackY = m_limelightTrackY;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftM = (.8 * m_limelightTrackX.usePIDOutput()) + (.2 * m_limelightTrackY.usePIDOutput());
    double rightM = (-.8 * m_limelightTrackX.usePIDOutput()) + (.2 * m_limelightTrackY.usePIDOutput());
    driveTrain.tankDrive(leftM, rightM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
