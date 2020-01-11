/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveBackward extends PIDCommand {
  DriveTrain drivetrain;
  double distance;

  /**
   * Creates a new DriveForward.
   */
  public DriveBackward(double distance, DriveTrain drivetrain) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PIDConstants.kForward.P, Constants.PIDConstants.kForward.I,
            Constants.PIDConstants.kForward.D),
        // This should return the measurement
        drivetrain::getEncoderValue,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> drivetrain.tankDrive(output * 0.1, output * 0.1),

        drivetrain);
    this.drivetrain = drivetrain;
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  public void initialize() {
    drivetrain.reset(false);
  }

  public void end(boolean interrupted) {
    drivetrain.tankDrive(0, 0);
    drivetrain.reset(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(distance - drivetrain.getEncoderValue()) <= Constants.Deadzones.kEncoder.deadzone) {
      return true;
    } else {
      return false;
    }
  }
}
