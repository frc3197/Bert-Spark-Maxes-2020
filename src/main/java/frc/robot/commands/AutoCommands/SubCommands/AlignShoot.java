/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AutoCommands.LimelightTracking.Align;
import frc.robot.commands.AutoCommands.LimelightTracking.LimelightHood;
import frc.robot.commands.AutoCommands.LimelightTracking.TrackLimelightTurn;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AlignShoot extends ParallelCommandGroup {
  /**
   * Creates a new AlignShoot.
   */
  public AlignShoot(Shooter shooter, Hood hood, Turret turret) {
    super(new ShootAuto(shooter),new TrackLimelightTurn(turret), new LimelightHood(hood));
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
  }
}
