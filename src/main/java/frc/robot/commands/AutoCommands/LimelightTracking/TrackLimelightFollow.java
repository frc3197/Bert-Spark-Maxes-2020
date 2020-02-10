/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines a TrackLimelightFollow object.
 */
public class TrackLimelightFollow extends SequentialCommandGroup {

 /**
  * Creates a new TrackLimelightFollow.
  * @param shooter Shooter subsystem
  * @param driveTrain DriveTrain subsystem
  * Runs selected commands sequentially
  */
  public TrackLimelightFollow(Shooter shooter, DriveTrain driveTrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      // new TrackLimelightTurn(shooter, driveTrain),
       new TrackLimelightMaintainDistance(shooter, driveTrain));
  }
}
