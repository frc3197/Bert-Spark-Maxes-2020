/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.PID_Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines a TrackLimelightTurn object.
 */
public class TrackLimelightTurn extends PIDCommand {
  Shooter shooter;
  Turret turret;
  // static double d;

  /**
   * Creates a new TrackLimelightTurn.
   * @param turret Drivetrain subsystem
   * Creates PID loop and sets constants.
   */
  public TrackLimelightTurn(Turret turret) {
    super(
        // The controller that the command will use
        new PIDController(PID_Constants.kTurn.P, PID_Constants.kTurn.I, PID_Constants.kTurn.D),
        // This should return the measurement
        turret::getXOffset,
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          turret.turn(output * .2);
          // Use the output here
        });
    addRequirements(turret);
    this.turret = turret;
    getController().setTolerance(3);
  }

  /**
   * Called once the command ends.
   * Sets tankDrive motors on both sides to zero.
   */
  public void end() {
    turret.turn(0);
  }

  /**
   * Returns true when the command should end.
   * @return Whether the controller is at the correct setpoint.
   */
  @Override
  public boolean isFinished() {
    // return false;
    return getController().atSetpoint();
  }

  // public double usePIDOutput() {
  // return d;
  // }
}
