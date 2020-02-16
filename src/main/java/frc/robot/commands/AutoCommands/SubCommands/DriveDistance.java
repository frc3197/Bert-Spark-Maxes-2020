/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines a DtriveDistance object. Creates driveTrain parameter for later.
 */
public class DriveDistance extends PIDCommand {
  DriveTrain driveTrain;

  /**
   * Creates a new DriveDistance.
   * @param driveTrain DriveTrain subsystem
   * @param distance Calculated setpoint
   * Creates PID loop and sets constants.
   */
  public DriveDistance(DriveTrain driveTrain, double distance) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID_Constants.kDrive.P, Constants.PID_Constants.kDrive.I,
            Constants.PID_Constants.kDrive.D),
        // This should return the measurement
        driveTrain::getDistance,
        // This should return the setpoint (can also be a constant)
        distance,
        // This uses the output
        output -> {
          driveTrain.tankDrive(output, output);
          SmartDashboard.putNumber("Distance from Target", driveTrain.getDistance());
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.driveTrain = driveTrain;
    getController().setTolerance(0);
  }

  /**
   * Called when the command is initially scheduled.
   * Resets the DriveTrain motors' encoders.
   */
  public void initialize() {
    driveTrain.resetEncoder();
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;

  }
}
