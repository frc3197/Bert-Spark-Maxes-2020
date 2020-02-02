/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Hood;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LimelightHood extends PIDCommand {
  /**
   * Creates a new LimelightHood.
   */
  public LimelightHood(Hood hood) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID_Constants.kHood.P, Constants.PID_Constants.kHood.I, Constants.PID_Constants.kHood.D),
        // This should return the measurement
        hood::getYOffset,
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          hood.moveHood(output * .3);
        });
          // Use the output here
        
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
