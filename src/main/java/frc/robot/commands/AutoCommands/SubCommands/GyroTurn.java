/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines a GyroTurn object. Creates a driveTrain patameter to be used later.
 */
public class GyroTurn extends PIDCommand {
  DriveTrain driveTrain;
 /**
  * Creates a new GyroTurn
  * @param driveTrain DriveTrain subsystem
  * @param angle Setpoint
  * Creates the PID loop and sets constants.
  */
  public GyroTurn(DriveTrain driveTrain, double angle) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID_Constants.kGyro.P, Constants.PID_Constants.kGyro.I, Constants.PID_Constants.kGyro.D),
        // This should return the measurement
        driveTrain::getGyroAngle,
        // This should return the setpoint (can also be a constant)
        angle,
        // This uses the output
        output -> {
          driveTrain.tankDrive(-output, output);        
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.driveTrain = driveTrain;
  }

  /**
   * Called when the command is initially scheduled.
   * Resets the Gyro.
   */
  public void initialize() {
    driveTrain.resetGyro();
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
