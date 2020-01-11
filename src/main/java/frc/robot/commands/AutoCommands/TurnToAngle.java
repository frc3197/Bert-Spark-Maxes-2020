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
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  DriveTrain drivetrain;
  /**
   * Creates a new TurnToAngle.
   * 
   * @param targetAngleDegrees sets the target angle
   * @param drivetrain         sets the dependent driveTrain
   */
  public TurnToAngle(double targetAngleDegrees, DriveTrain drivetrain) {
    super(
        new PIDController(Constants.PIDConstants.kTurn.P, Constants.PIDConstants.kTurn.I,
            Constants.PIDConstants.kTurn.D),
        drivetrain::getAngle, targetAngleDegrees, output -> drivetrain.tankDrive(output * 0.07, -output * 0.07),
        drivetrain);
    this.drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  public void initialize() {
    drivetrain.reset(true);
  }

  public void end(boolean interrupted) {
    drivetrain.tankDrive(0, 0);
    drivetrain.reset(false);
  }

  // Returns true when the command should end.
  /**
   * Returns true when the PIDController reaches the setpoint, being the target
   * angle.
   * 
   * @return controller setpoint.
   */
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
