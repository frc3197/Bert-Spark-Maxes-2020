/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class DriveBackward extends CommandBase {
  private final double distance;

  /**
   * Creates a new DriveBackward.
   */
  public DriveBackward(DriveTrain driveTrain, double distance) {
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double encoderValue = RobotContainer.drivetrain.getEncoderValue();
    while (distance - encoderValue > 0) {
      SmartDashboard.putNumber("Encoder Value", RobotContainer.drivetrain.getEncoderValue());
      encoderValue = RobotContainer.drivetrain.getEncoderValue();
      RobotContainer.drivetrain.tankDrive(0.4, 0.4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.tankDrive(0, 0);
    SmartDashboard.putBoolean("DriveBackward Completed", true);
    RobotContainer.drivetrain.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
