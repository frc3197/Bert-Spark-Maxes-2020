/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines an Align object.
 */
public class Align extends SequentialCommandGroup {
 
  /**
   * Creates a new Align.
   * @param hood Hood subsystem
   * @param shooter Shooter subsystem
   * @param driveTrain DriveTrain subsystem
   * Runs selected commands seuentially.
   */
  public Align(Hood hood, Shooter shooter, Turret turret) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new TrackLimelightTurn(turret), new LimelightAdjustHood(hood));
  }
}
